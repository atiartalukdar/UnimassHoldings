package session;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.onesignal.OneSignal;

import bp.ObjectBox;
import oneSignal.NotificationOpenedHandler;
import oneSignal.NotificationReceivedHandler;

public class MyApplication extends Application {
    private static Context context;
    private final String tag = MyApplication.class.getSimpleName() + "Atiar= ";

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ObjectBox.init(this);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(false)
                .autoPromptLocation(true)
                .setNotificationOpenedHandler(new NotificationOpenedHandler())
                .setNotificationReceivedHandler(new NotificationReceivedHandler())
                .init();
    }

    public static Context getContext (){return context; }
    public static synchronized MyApplication getInstance(){return mInstance;}

}