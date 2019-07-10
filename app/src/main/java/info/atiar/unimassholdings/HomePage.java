package info.atiar.unimassholdings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bp.ObjectBox;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.atiar.unimassholdings.addNewClients.AddNewClients;
import info.atiar.unimassholdings.clients.ClientsList;
import info.atiar.unimassholdings.dataModel.ClientBox;
import info.atiar.unimassholdings.dataModel.LoginData;
import info.atiar.unimassholdings.schedule.ScheduleLists;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import objectBox.InitialClientInfoBox;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

public class HomePage extends AppCompatActivity {
    @BindView(R.id.allClients)      TextView  _allClients;
    @BindView(R.id.scheduleList)    TextView  _scheduleLists;
    @BindView(R.id.addNewClients)    LinearLayout _addNewClients;
    @BindView(R.id.client0_tv)    TextView  _client0;
    @BindView(R.id.client20_tv)    TextView  _client20;
    @BindView(R.id.client40_tv)    TextView  _client40;
    @BindView(R.id.client80_tv)    TextView  _client80;
    @BindView(R.id.client90_tv)    TextView  _client90;
    int client0,client20,client40,client80,client90;

    Retrofit retrofit;
    APIInterface apiInterface;

    Box<InitialClientInfoBox> clientInfoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
        initialize();
        clientInfoBox = ObjectBox.get().boxFor(InitialClientInfoBox.class);




        _scheduleLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, ScheduleLists.class));
            }
        });


        _addNewClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, AddNewClients.class));
            }
        });

        allOnclickListener();
        getAllClients();
    }

    private void getAllClients() {
        final ProgressDialog progressDialog = new ProgressDialog(HomePage.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Client's Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getAllClients(Session.getSeassionData().getEmail(),Session.getPassword(),Session.getUserRole());

        call.enqueue(new Callback<ClientBox>() {
            @Override
            public void onResponse(Call<ClientBox> call, Response<ClientBox> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()){
                    clientInfoBox.removeAll();
                    List<ClientBox.GeneralInfo> clientLists = response.body().getGeneralInfo();
                    for (ClientBox.GeneralInfo data:clientLists) {
                        InitialClientInfoBox i = new InitialClientInfoBox(data);
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
                    _client0.setText("0% client: "+client0);
                    _client20.setText("20% client: "+client20);
                    _client40.setText("40% client: "+client40);
                    _client80.setText("80% client: "+client80);
                    _client90.setText("90% client: "+client90);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });

    }

    private void initialize(){
        client0 = 0;
        client20 = 0;
        client40 = 0;
        client80 = 0;
        client90 = 0;
    }

    private void allOnclickListener(){

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



    }
}
