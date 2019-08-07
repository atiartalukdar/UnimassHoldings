package info.atiar.unimassholdings.clients;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bp.ObjectBox;
import bp.Utils;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.fragments.CommunicationDetails;
import info.atiar.unimassholdings.clients.fragments.GeneralInfo;
import info.atiar.unimassholdings.clients.fragments.OtherInformations;
import info.atiar.unimassholdings.clients.fragments.Requirements;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.ClientOtherInfoBox;
import objectBox.ClientRequiredInfoBox;
import objectBox.SpecificCommRecordBox;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;


public class ClientDetails extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName() + " Atiar= ";

    TextView _name;
    TextView _phone;
    TextView _clientID;
    TextView _agent;
    TextView _address;
    TextView _progress;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ClientGeneralInfoBox member;

    public ClientGeneralInfoBox getMember() {
        return member;
    }

    Retrofit retrofit;
    APIInterface apiInterface;
    Box<SpecificCommRecordBox> commBox;
    Box<ClientOtherInfoBox> otherInfoBox;
    Box<ClientRequiredInfoBox> requiredInfoBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
        commBox = ObjectBox.get().boxFor(SpecificCommRecordBox.class);
        otherInfoBox = ObjectBox.get().boxFor(ClientOtherInfoBox.class);
        requiredInfoBox = ObjectBox.get().boxFor(ClientRequiredInfoBox.class);


        _name       = findViewById(R.id.custom_client_name);
        _phone      = findViewById(R.id.custom_client_mobile);
        _clientID   = findViewById(R.id.custom_client_id);
        _agent      = findViewById(R.id.custom_client_agent);
        _address    = findViewById(R.id.custom_client_address);
        _progress   = findViewById(R.id.custom_client_progress);


        try {
            //retriving lead member's details from intent to start pre screening
            member = (ClientGeneralInfoBox) getIntent().getSerializableExtra("memberDetails");
            Log.e(TAG, "Client ID: " + member.getClientID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setUpInitialUI();

        loadClientProfile();
        loadCommunicationDataFromServer();

        _phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openDialPad(ClientDetails.this,_phone.getText().toString());
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CommunicationDetails(), "Communication");
        adapter.addFragment(new GeneralInfo(), "G. Info");
        adapter.addFragment(new Requirements(), "Requirements");
        adapter.addFragment(new OtherInformations(), "Others Info");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    private void setUpInitialUI() {
        try {
            _name.setTextColor(Color.BLACK);
                    _phone.setTextColor(Color.BLACK);
            _clientID.setTextColor(Color.BLACK);
                    _agent.setTextColor(Color.BLACK);
            _address.setTextColor(Color.BLACK);
                    _progress.setTextColor(Color.GREEN);

            _name.setText(member.getLandownerName());
            _phone.setText(member.getContactNo());
            _clientID.setText("Client ID: " + member.getClientID());
            _agent.setText("Agent: "+member.getAgentId());
            _address.setText(member.getLandownerAddress());
            _progress.setText(member.getProgressStatus() + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCommunicationDataFromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(ClientDetails.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Communication Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getClientCommunicationRecords(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), getMember().getClientID()+"");

        call.enqueue(new Callback<CommunicationDM>() {
            @Override
            public void onResponse(Call<CommunicationDM> call, Response<CommunicationDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()) {

                    commBox.removeAll();
                    List<CommunicationDM.SpecificCommRecord> clientLists = response.body().getSpecificCommRecord();
                    for (CommunicationDM.SpecificCommRecord data : clientLists) {
                        SpecificCommRecordBox i = new SpecificCommRecordBox(data);
                        if (!commBox.getAll().contains(i)){
                            commBox.put(i);
                        }
                    }


                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(ClientDetails.this, "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });


    }

    private void loadClientProfile() {
        final ProgressDialog progressDialog = new ProgressDialog(ClientDetails.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Profile Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getClientProfile(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), getMember().getClientID()+"");

        call.enqueue(new Callback<ClientProfileDM>() {
            @Override
            public void onResponse(Call<ClientProfileDM> call, Response<ClientProfileDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    ClientProfileDM profile = response.body();

                    try{

                        ClientOtherInfoBox a = new ClientOtherInfoBox(profile.getOtherInfo());
                        ClientRequiredInfoBox b = new ClientRequiredInfoBox(profile.getReqInfo());

                        if (!otherInfoBox.getAll().contains(a)){
                            otherInfoBox.put(a);
                        }
                        if (!requiredInfoBox.getAll().contains(b)){
                            requiredInfoBox.put(b);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Log.e(TAG, profile.toString());

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(ClientDetails.this, "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });


    }


}