package info.atiar.unimassholdings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bp.ObjectBox;
import bp.TimeUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.atiar.unimassholdings.clients.AddNewClientGeneralInfoActivity;
import info.atiar.unimassholdings.clients.ClientsList;
import info.atiar.unimassholdings.dataModel.ClientBox;
import info.atiar.unimassholdings.dataModel.ScheduleDM;
import info.atiar.unimassholdings.schedule.ScheduleLists;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.ClientGeneralInfoBox_;
import objectBox.ScheduleBox;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

public class HomePage extends AppCompatActivity {
    private final String TAG = getClass().getName() + " Atiar - ";

    @BindView(R.id.allClients)
    TextView _allClients;
    @BindView(R.id.scheduleList)
    TextView _scheduleLists;
    @BindView(R.id.client0_tv)
    TextView _client0;
    @BindView(R.id.client20_tv)
    TextView _client20;
    @BindView(R.id.client40_tv)
    TextView _client40;
    @BindView(R.id.client80_tv)
    TextView _client80;
    @BindView(R.id.client90_tv)
    TextView _client90;

    @BindView(R.id.schedule_today)
    TextView _today;
    @BindView(R.id.schedule_tomorrow)
    TextView _tomorrow;
    @BindView(R.id.schedule_week)
    TextView _week;
    @BindView(R.id.schedule_month)
    TextView _month;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout _pullToRefresh;

    int client0, client20, client40, client80, client90;
    int today, tomorrow, week, month;

    Retrofit retrofit;
    APIInterface apiInterface;

    Box<ClientGeneralInfoBox> clientInfoBox;
    Box<ScheduleBox> scheduleBox;
    @BindView(R.id.addNewClient)
    ImageButton addNewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
        clientInfoBox = ObjectBox.get().boxFor(ClientGeneralInfoBox.class);
        scheduleBox = ObjectBox.get().boxFor(ScheduleBox.class);

        initialize();





       /* _addNewClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, AddNewClients.class));
            }
        });*/

        allOnclickListener();
        getAllClients();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.app_bar_menu_signout:
                Session.logout();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void getAllClients() {
        final ProgressDialog progressDialog = new ProgressDialog(HomePage.this);
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
                    clientInfoBox.removeAll();
                    initialize();
                    List<ClientBox.GeneralInfo> clientLists = response.body().getGeneralInfo();
                    for (ClientBox.GeneralInfo data : clientLists) {
                        ClientGeneralInfoBox i = new ClientGeneralInfoBox(data);
                        clientInfoBox.put(i);

                        switch (data.getProgressStatus().trim()) {
                            case "0":
                                client0++;
                                break;
                            case "20":
                                client20++;
                                break;
                            case "40":
                                client40++;
                                break;
                            case "80":
                                client80++;
                                break;
                            case "90":
                                client90++;
                                break;
                        }

                    }
                    //Log.e(TAG, g.toString());
                    _client0.setText("0% client: " + client0);
                    _client20.setText("20% client: " + client20);
                    _client40.setText("40% client: " + client40);
                    _client80.setText("80% client: " + client80);
                    _client90.setText("90% client: " + client90);
                }

                getAllSchedule();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                loadLocalData();
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });

    }

    private void getAllSchedule() {
        final ProgressDialog progressDialog = new ProgressDialog(HomePage.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Schedule Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getSchedule(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), APIInterface.ALL);

        call.enqueue(new Callback<ScheduleDM>() {
            @Override
            public void onResponse(Call<ScheduleDM> call, Response<ScheduleDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    if (!response.body().getScheduleType().equals("Currently No Schedule")) {
                        scheduleBox.removeAll();

                        List<ScheduleDM.Schedule> scheduleLists = response.body().getSchedules();

                        for (ScheduleDM.Schedule data : scheduleLists) {
                            ScheduleBox i = new ScheduleBox(data);
                            scheduleBox.put(i);
                            try {
                                int dif = TimeUtils.getDifferenceInDays(data.getDate());
                                Log.e(TAG, dif + "");

                                if (dif == 0) {
                                    today++;
                                }
                                if (dif == 1) {
                                    tomorrow++;
                                }
                                if (dif <= 7) {
                                    week++;
                                }
                                if (dif <= 30) {
                                    month++;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    //Log.e(TAG, g.toString());
                    _today.setText("Today: " + today);
                    _tomorrow.setText("Tomorrow: " + tomorrow);
                    _week.setText("Week: " + week);
                    _month.setText("Month: " + month);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });

    }

    private void initialize() {
        client0 = 0;
        client20 = 0;
        client40 = 0;
        client80 = 0;
        client90 = 0;

        today = 0;
        tomorrow = 0;
        week = 0;
        month = 0;
    }

    private void loadLocalData() {
        try {
            client0 = clientInfoBox.query().equal(ClientGeneralInfoBox_.progressStatus, "0").build().find().size();
            client20 = clientInfoBox.query().equal(ClientGeneralInfoBox_.progressStatus, "20").build().find().size();
            client40 = clientInfoBox.query().equal(ClientGeneralInfoBox_.progressStatus, "40").build().find().size();
            client80 = clientInfoBox.query().equal(ClientGeneralInfoBox_.progressStatus, "80").build().find().size();
            client90 = clientInfoBox.query().equal(ClientGeneralInfoBox_.progressStatus, "90").build().find().size();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //Log.e(TAG, g.toString());
            _client0.setText("0% client: " + client0);
            _client20.setText("20% client: " + client20);
            _client40.setText("40% client: " + client40);
            _client80.setText("80% client: " + client80);
            _client90.setText("90% client: " + client90);
        }

        for (ScheduleBox s : scheduleBox.getAll()) {
            try {
                int dif = TimeUtils.getDifferenceInDays(s.getDate());
                Log.e(TAG, dif + "");

                if (dif == 0) {
                    today++;
                }
                if (dif == 1) {
                    tomorrow++;
                }
                if (dif <= 7) {
                    week++;
                }
                if (dif <= 30) {
                    month++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //Log.e(TAG, g.toString());
                _today.setText("Today: " + today);
                _tomorrow.setText("Tomorrow: " + tomorrow);
                _week.setText("Week: " + week);
                _month.setText("Month: " + month);
            }
        }
    }

    private void allOnclickListener() {

        _pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllClients();
                _pullToRefresh.setRefreshing(false);

            }
        });


        _allClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ClientsList.class);
                intent.putExtra("client", "all");
                startActivity(intent);
            }
        });

        _client0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ClientsList.class);
                intent.putExtra("client", "0");
                startActivity(intent);
            }
        });

        _client20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ClientsList.class);
                intent.putExtra("client", "20");
                startActivity(intent);
            }
        });

        _client40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ClientsList.class);
                intent.putExtra("client", "40");
                startActivity(intent);
            }
        });

        _client80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ClientsList.class);
                intent.putExtra("client", "80");
                startActivity(intent);
            }
        });

        _client90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ClientsList.class);
                intent.putExtra("client", "90");
                startActivity(intent);
            }
        });


        _today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScheduleLists.class);
                intent.putExtra("scheduleRange", APIInterface.TODAY);
                startActivity(intent);
            }
        });

        _tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScheduleLists.class);
                intent.putExtra("scheduleRange", APIInterface.TOMORROW);
                startActivity(intent);
            }
        });

        _week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScheduleLists.class);
                intent.putExtra("scheduleRange", APIInterface.WEEK);
                startActivity(intent);
            }
        });

        _month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScheduleLists.class);
                intent.putExtra("scheduleRange", APIInterface.MONTH);
                startActivity(intent);
            }
        });


        _scheduleLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScheduleLists.class);
                intent.putExtra("scheduleRange", APIInterface.ALL);
                startActivity(intent);
            }
        });


    }

    @OnClick(R.id.addNewClient)
    public void onViewClicked() {
        startActivity(new Intent(this, AddNewClientGeneralInfoActivity.class));
        finish();
    }
}
