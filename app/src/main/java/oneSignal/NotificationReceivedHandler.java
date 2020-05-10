package oneSignal;

import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import bp.ObjectBox;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.NotificationBox;
import session.Session;

//More details: https://documentation.onesignal.com/docs/android-native-sdk#section--notificationreceivedhandler-
public class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {

    @Override
    public void notificationReceived(OSNotification notification) {
        //Box<NotificationBox> notificationBoxBox = ObjectBox.get().boxFor(NotificationBox.class);
        try {
            if (notification.payload.additionalData.getString("sentByID").equals(Session.getSeassionData().getId().toString())){
                OneSignal.setInFocusDisplaying(0);
                OneSignal.cancelNotification(notification.androidNotificationId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject data = notification.payload.additionalData;
        String sentByName  = null, sentByID = null, sentByUserRole = null, clientID = null, recordID = null;
        NotificationBox notificationBox = null;

        if (data != null) {
            try {
                sentByName = data.getString("sentByName");
                sentByID = data.getString("sentByID");
                sentByUserRole = data.getString("sentByUserRole");
                clientID = data.getString("clientID");
                recordID = data.getString("recordID");
                notificationBox = new NotificationBox(sentByName,sentByID,sentByUserRole,clientID,recordID, notification.payload.title, notification.payload.body);
                //notificationBoxBox.put(notificationBox);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("OneSignalExample", "customkey set with value: " + notificationBox.toString());
            Log.i("OneSignalExample", "customkey set with value: " + Session.getSeassionData().getId().toString());
            //Log.i("OneSignalExample", "NotificationBox size: " + notificationBoxBox.getAll().size());

        }
    }
}