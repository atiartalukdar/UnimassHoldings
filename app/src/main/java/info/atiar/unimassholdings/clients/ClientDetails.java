package info.atiar.unimassholdings.clients;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.fragments.CommunicationDetails;
import info.atiar.unimassholdings.clients.fragments.GeneralInfo;
import info.atiar.unimassholdings.clients.fragments.OtherInformations;
import info.atiar.unimassholdings.clients.fragments.Requirements;
import info.atiar.unimassholdings.dataModel.ClientBox;
import objectBox.InitialClientInfoBox;


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
    private InitialClientInfoBox member;

    public InitialClientInfoBox getMember() {
        return member;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        _name       = findViewById(R.id.custom_client_name);
        _phone      = findViewById(R.id.custom_client_mobile);
        _clientID   = findViewById(R.id.custom_client_id);
        _agent      = findViewById(R.id.custom_client_agent);
        _address    = findViewById(R.id.custom_client_address);
        _progress   = findViewById(R.id.custom_client_progress);


        try {
            //retriving lead member's details from intent to start pre screening
            member = (InitialClientInfoBox) getIntent().getSerializableExtra("memberDetails");
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

}