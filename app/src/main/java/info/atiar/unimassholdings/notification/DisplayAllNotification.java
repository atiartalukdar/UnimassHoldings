package info.atiar.unimassholdings.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    @BindView(R.id.leadList)    ListView _notificationList;
    NotificationListAdapter adapter;

    Box<NotificationBox> notificationBoxBox;
    List<NotificationBox> notificationList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_notification);
        ButterKnife.bind(this);
        notificationBoxBox = ObjectBox.get().boxFor(NotificationBox.class);
        notificationList = notificationBoxBox.query().order(NotificationBox_.recordID, QueryBuilder.DESCENDING).build().find();

        adapter = new NotificationListAdapter(this, notificationList);
        _notificationList.setAdapter(adapter);

        int unreadNotification=0;
        for (NotificationBox notificationBox :  notificationList){
            if (notificationBox.getIsRead().equals("0")){
                unreadNotification++;
            }
        }
        getSupportActionBar().setTitle("Unread Notification (" + unreadNotification+ ")");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    }
}
