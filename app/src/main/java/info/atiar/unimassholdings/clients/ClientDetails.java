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

import javax.annotation.Nullable;

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

    @Nullable
    private ClientProfileDM memberProfile;  //contains member profile
    private List<CommunicationDM.SpecificCommRecord> communicationList = new ArrayList<>(); //contains specific communication records
    private ClientGeneralInfoBox individualMember;  //clients basic info retrieved from previous activity.

    public ClientProfileDM getMemberProfile() {
        return memberProfile;
    }
    public List<CommunicationDM.SpecificCommRecord> getCommunicationDetails() {
        return communicationList;
    }

    Retrofit retrofit;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);


        _name       = findViewById(R.id.custom_client_name);
        _phone      = findViewById(R.id.custom_client_mobile);
        _clientID   = findViewById(R.id.custom_client_id);
        _agent      = findViewById(R.id.custom_client_agent);
        _address    = findViewById(R.id.custom_client_address);
        _progress   = findViewById(R.id.custom_client_progress);


        try {
            //retriving client profile from individual
            individualMember = (ClientGeneralInfoBox) getIntent().getSerializableExtra("memberDetails");
            memberProfile = (ClientProfileDM) getIntent().getSerializableExtra("memberProfile");
            Log.e(TAG, "Client ID: " + individualMember.getClientID());
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

            _name.setText(individualMember.getLandownerName());
            _phone.setText(individualMember.getContactNo());
            _clientID.setText("Client ID: " + individualMember.getClientID());
            _agent.setText("Agent: "+individualMember.getAgentId());
            _address.setText(individualMember.getLandownerAddress());
            _progress.setText(individualMember.getProgressStatus() + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}