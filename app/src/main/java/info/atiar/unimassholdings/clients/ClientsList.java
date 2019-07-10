package info.atiar.unimassholdings.clients;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.ClientListAdapter;
import bp.ObjectBox;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.atiar.unimassholdings.HomePage;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.dataModel.ClientBox;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;
import objectBox.InitialClientInfoBox;
import objectBox.InitialClientInfoBox_;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

public class ClientsList extends AppCompatActivity {
    private final String TAG = getClass().getName() + " Atiar - ";

    @BindView(R.id.leadList)
    ListView _clientList;

    Retrofit retrofit;
    APIInterface apiInterface;
    ClientListAdapter adapter;
    Box<InitialClientInfoBox> clientBox;
    List<InitialClientInfoBox> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
        clientBox = ObjectBox.get().boxFor(InitialClientInfoBox.class);

        String dataToLoad = getIntent().getStringExtra("client");
        switch (dataToLoad) {
            case "0":
                clientList = clientBox.query().equal(InitialClientInfoBox_.progressStatus, "0").build().find();
                break;
            case "20":
                clientList = clientBox.query().equal(InitialClientInfoBox_.progressStatus, "20").build().find();
                break;
            case "40":
                clientList = clientBox.query().equal(InitialClientInfoBox_.progressStatus, "40").build().find();
                break;
            case "80":
                clientList = clientBox.query().equal(InitialClientInfoBox_.progressStatus, "80").build().find();
                break;
            case "90":
                clientList = clientBox.query().equal(InitialClientInfoBox_.progressStatus, "90").build().find();
                break;

            case "all":
                clientList = clientBox.query().order(InitialClientInfoBox_.progressStatus, QueryBuilder.DESCENDING).build().find();
                break;


        }

        adapter = new ClientListAdapter(this, clientList);
        _clientList.setAdapter(adapter);

        getSupportActionBar().setTitle("Total Clients (" + clientBox.getAll().size() + ")");


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
                List<InitialClientInfoBox> tripResults = new ArrayList<InitialClientInfoBox>();
                for (InitialClientInfoBox x : clientBox.getAll()) {

                    try {
                        if (x.getLandownerAddress().equals(null) || x.getLandownerAddress().equals("") || x.getLandownerAddress() == null) {
                            if (x.getContactNo().contains(newText.toLowerCase()) || x.getLandownerName().contains(newText.toLowerCase()))
                                tripResults.add(x);
                        } else {
                            if (x.getLandownerAddress().toLowerCase().contains(newText.toLowerCase()) || x.getContactNo().contains(newText.toLowerCase()) || x.getLandownerName().contains(newText.toLowerCase()))
                                tripResults.add(x);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                //adapterTransport.update(tripResults);
                ((ClientListAdapter) _clientList.getAdapter()).update(tripResults);

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
                loadListDataFromServer();
                adapter.notifyDataSetChanged();
                break;
            case R.id.app_bar_new_lead:
                //startActivity(new Intent(ClientsList.this, AddNewLead.class));
                //finish();
                Toast.makeText(this, "Feature is comming soon, Please patients !!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void loadListDataFromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(ClientsList.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Client's Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getAllClients(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole());

        call.enqueue(new Callback<ClientBox>() {
            @Override
            public void onResponse(Call<ClientBox> call, Response<ClientBox> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    clientBox.removeAll();

                    List<ClientBox.GeneralInfo> clientLists = response.body().getGeneralInfo();
                    for (ClientBox.GeneralInfo data : clientLists) {
                        InitialClientInfoBox i = new InitialClientInfoBox(data);
                        clientBox.put(i);
                    }

                    getSupportActionBar().setTitle("Total Clients (" + clientBox.getAll().size() + ")");

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
