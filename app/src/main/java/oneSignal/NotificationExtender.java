package oneSignal;

import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import bp.ObjectBox;
import io.objectbox.Box;
import objectBox.NotificationBox;
import session.Session;

public class NotificationExtender extends NotificationExtenderService {
    private final String tag = getClass().getSimpleName() + "Atiar = ";
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
        NotificationBox notificationBox = new NotificationBox();
        Box<NotificationBox> notificationBoxBox = null;

        String title = receivedResult.payload.title;
        String body = receivedResult.payload.body;
        Log.e(tag, "Onesignal title = "+ title);
        Log.e(tag, "\nOnesignal body = "+ body);

        JSONObject data = receivedResult.payload.additionalData;
        String sentByName  = null, sentByID = null, sentByUserRole = null, clientID = null, recordID = null;
        if (data != null) {
            try {
                sentByName = data.getString("sentByName");
                sentByID = data.getString("sentByID");
                sentByUserRole = data.getString("sentByUserRole");
                clientID = data.getString("clientID");
                recordID = data.getString("recordID");

                notificationBox.setClientID(clientID);
                notificationBox.setSentByName(sentByName);
                notificationBox.setSentByID(sentByID);
                notificationBox.setSentByUserRole(sentByUserRole);
                notificationBox.setRecordID(recordID);
                notificationBox.setIsRead("0");
                notificationBox.setTitle(title);
                notificationBox.setMessage(body);
                Log.e(tag, "OneSignal Notification Data: " + notificationBox.toString());

                if (!sentByID.equals(Session.getSeassionData().getId().toString())) {
                    notificationBoxBox = ObjectBox.get().boxFor(NotificationBox.class);
                    notificationBoxBox.put(notificationBox);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.e(tag, "ObjectBox size: " + notificationBoxBox.getAll().size() +"");
           // notificationBoxBox.removeAll();

        }
        return false;
    }
}
