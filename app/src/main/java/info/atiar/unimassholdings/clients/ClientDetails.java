package info.atiar.unimassholdings.clients;

import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import bp.Utils;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.fragments.CommunicationDetails;
import info.atiar.unimassholdings.clients.fragments.GeneralInfo;
import info.atiar.unimassholdings.clients.fragments.OtherInformations;
import info.atiar.unimassholdings.clients.fragments.Requirements;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import objectBox.ClientGeneralInfoBox;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Retrofit;


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
            memberProfile = (ClientProfileDM) getIntent().getSerializableExtra("memberProfile");
            //individualMember = (ClientGeneralInfoBox) getIntent().getSerializableExtra("memberDetails");
           // Log.e(TAG, "Client ID: " + individualMember.getClientID());
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

            _name.setText(memberProfile.getGeneralInfo().getLandownerName());
            _phone.setText(memberProfile.getGeneralInfo().getContactNo());
            _clientID.setText("Client ID: " + memberProfile.getGeneralInfo().getId());
            _agent.setText("Agent: "+memberProfile.getGeneralInfo().getAgentId());
            _address.setText(memberProfile.getGeneralInfo().getLandownerAddress());
            _progress.setText(memberProfile.getGeneralInfo().getProgressStatus() + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}