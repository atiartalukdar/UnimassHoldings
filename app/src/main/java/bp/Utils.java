package bp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import session.MyApplication;

/**
 * Created by Atiar Talukdar on 7/11/2019.
 */
public class Utils {

    public static void openDialPad(Activity activity, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        activity.startActivity(intent);
    }
}
