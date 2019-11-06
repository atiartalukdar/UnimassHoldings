package info.atiar.unimassholdings.schedule;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.ScheduleAdapter;
import bp.ObjectBox;
import bp.TimeUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.dataModel.ScheduleDM;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;
import objectBox.ScheduleBox;
import objectBox.ScheduleBox_;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

public class ScheduleLists extends AppCompatActivity {
    private final String TAG = getClass().getName() + " Atiar - ";

    @BindView(R.id.leadList)    ListView _scheduleList;

    Retrofit retrofit;
    APIInterface apiInterface;
    ScheduleAdapter adapter;
    Box<ScheduleBox> scheduleBox;
    List<ScheduleBox> scheduleList = new ArrayList<>();
    List<ScheduleBox> tempScheduleList = new ArrayList<>();

    String range = "all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
        scheduleBox = ObjectBox.get().boxFor(ScheduleBox.class);

        tempScheduleList = scheduleBox.query().order(ScheduleBox_.date, QueryBuilder.DESCENDING).build().find();

        String dataToLoad = getIntent().getStringExtra("scheduleRange");

        try {
            switch (dataToLoad) {
                case APIInterface.ALL:
                    range = APIInterface.ALL;
                    scheduleList = tempScheduleList;
                    break;

                case APIInterface.TODAY:
                    range = APIInterface.TODAY;
                    for (ScheduleBox s : tempScheduleList){
                        if (TimeUtils.getDifferenceInDays(s.getDate())==0){
                            scheduleList.add(s);
                        }
                    }
                    break;
                case APIInterface.TOMORROW:
                    range = APIInterface.TOMORROW;
                    for (ScheduleBox s : tempScheduleList){
                        if (TimeUtils.getDifferenceInDays(s.getDate())==1){
                            scheduleList.add(s);
                        }
                    }
                    break;
                case APIInterface.WEEK:
                    range = APIInterface.WEEK;
                    for (ScheduleBox s : tempScheduleList){
                        if (TimeUtils.getDifferenceInDays(s.getDate())<=7){
                            scheduleList.add(s);
                        }
                    }
                    break;
                case APIInterface.MONTH:
                    range = APIInterface.MONTH;
                    for (ScheduleBox s : tempScheduleList){
                        if (TimeUtils.getDifferenceInDays(s.getDate())<=30){
                            scheduleList.add(s);
                        }
                    }
                    break;


            }
        }catch (Exception e){
            e.printStackTrace();
        }


        adapter = new ScheduleAdapter(this, scheduleList);
        _scheduleList.setAdapter(adapter);

        getSupportActionBar().setTitle("Total (" + scheduleList.size() + ")");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.client_list_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_menu_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<ScheduleBox> tripResults = new ArrayList<ScheduleBox>();
                for (ScheduleBox x : scheduleList) {

                    try {
                            if (x.getByWhom().toLowerCase().contains(newText.toLowerCase()) ||
                                    x.getLName().toLowerCase().trim().contains(newText.toLowerCase()))
                                tripResults.add(x);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                //adapterTransport.update(tripResults);
                ((ScheduleAdapter) _scheduleList.getAdapter()).update(tripResults);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.app_bar_menu_reload:
                loadListDataFromServer(range);
                adapter.notifyDataSetChanged();
                break;
            case R.id.app_bar_new_lead:
                //startActivity(new Intent(ClientsList.this, AddNewLead.class));
                //finish();
                Toast.makeText(this, "Feature is coming soon, Please patients !!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void loadListDataFromServer(String range) {
        final ProgressDialog progressDialog = new ProgressDialog(ScheduleLists.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Schedule's Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getSchedule(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(),range);

        call.enqueue(new Callback<ScheduleDM>() {
            @Override
            public void onResponse(Call<ScheduleDM> call, Response<ScheduleDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()) {

                    if (!response.body().getScheduleType().equals("Currently No Schedule")){
                        scheduleBox.removeAll();

                        List<ScheduleDM.Schedule> scheduleLists = response.body().getSchedules();

                        for (ScheduleDM.Schedule data : scheduleLists) {
                            ScheduleBox i = new ScheduleBox(data);
                            scheduleBox.put(i);
                        }

                        getSupportActionBar().setTitle("Total (" + scheduleList.size() + ")");
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });


    }

}
