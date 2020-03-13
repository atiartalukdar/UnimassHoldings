package oneSignal;

import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

//More details: https://documentation.onesignal.com/docs/android-native-sdk#section--notificationreceivedhandler-
public class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String customKey = null;

        if (data != null) {
            try {
                customKey = data.getString("sentByName");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("OneSignalExample", "customkey set with value: " + customKey);

        }
    }
}