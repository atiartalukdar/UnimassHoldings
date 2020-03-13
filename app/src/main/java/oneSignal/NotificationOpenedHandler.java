package oneSignal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

//More details: https://documentation.onesignal.com/docs/android-native-sdk#section--notificationopenedhandler-
public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    Retrofit retrofit;
    APIInterface apiInterface;
    Context context;
    public NotificationOpenedHandler(Context context) {
        this.context = context;
    }

    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);

        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String customKey;

        try {
            Log.e("OSNotificationPayload", "result.notification.payload.toJSONObject().toString(): " + result.notification.payload.additionalData.getString("clientID").toString());
            loadClientProfile(result.notification.payload.additionalData.getString("clientID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

        // The following can be used to open an Activity of your choice.
        // Replace - getApplicationContext() - with any Android Context.
        // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(intent);

        // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
        //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
    }

    private void loadClientProfile(String clientID) {


        Call call = apiInterface.getClientProfile(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), clientID);

        call.enqueue(new Callback<ClientProfileDM>() {
            @Override
            public void onResponse(Call<ClientProfileDM> call, Response<ClientProfileDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */

                if (response.isSuccessful()) {

                    Intent intent = new Intent(context, ClientDetails.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.putExtra("memberDetails",data);
                    intent.putExtra("memberProfile", response.body());
                    context.startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }

        });


    }

}
