package info.atiar.unimassholdings.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.NotificationListAdapter;
import adapters.ScheduleAdapter;
import bp.ObjectBox;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.atiar.unimassholdings.R;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;
import objectBox.NotificationBox;
import objectBox.NotificationBox_;
import objectBox.ScheduleBox;
import objectBox.ScheduleBox_;

public class DisplayAllNotification extends AppCompatActivity {
    @BindView(R.id.recylerView)    RecyclerView _recylerView;
    @BindView(R.id.pullToRefresh)    SwipeRefreshLayout _pullToRefresh;

    NotificationListAdapter adapter;
    int unreadNotification=0;

    Box<NotificationBox> notificationBoxBox;
    List<NotificationBox> notificationList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_notification);
        ButterKnife.bind(this);
        notificationBoxBox = ObjectBox.get().boxFor(NotificationBox.class);
        initializeList();

        //getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.e("notifications - ", unreadNotification + " ");


        _pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initializeList();
                getSupportActionBar().setTitle("Unread Notification (" + unreadNotification+ ")");
                //getSupportActionBar().setTitle("Notifications");
                _pullToRefresh.setRefreshing(false);
            }
        });


    }

    private void initializeList() {
        unreadNotification = 0;
        //notificationList = notificationBoxBox.query().order(NotificationBox_.recordID, QueryBuilder.DESCENDING).build().find();
        notificationList = notificationBoxBox.getAll();
        adapter = new NotificationListAdapter(DisplayAllNotification.this, notificationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        _recylerView.setLayoutManager(mLayoutManager);
        _recylerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setHasStableIds(true);
        _recylerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for (NotificationBox notificationBox :  notificationList){
            if (notificationBox.getIsRead().equals("0")){
                unreadNotification++;
            }
        }
        getSupportActionBar().setTitle("Unread Notification (" + unreadNotification+ ")");
        //getSupportActionBar().setTitle("Unread Notification (" + unreadNotification+ ")");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        initializeList();
    }


}
