package info.atiar.unimassholdings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.atiar.unimassholdings.addNewClients.AddNewClients;
import info.atiar.unimassholdings.clients.ClientsList;
import info.atiar.unimassholdings.schedule.ScheduleLists;

public class HomePage extends AppCompatActivity {
    @BindView(R.id.allClients)      TextView  _allClients;
    @BindView(R.id.scheduleList)    TextView  _scheduleLists;
    @BindView(R.id.addNewClients)    LinearLayout _addNewClients;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        _allClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, ClientsList.class));
            }
        });


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
    }
}
