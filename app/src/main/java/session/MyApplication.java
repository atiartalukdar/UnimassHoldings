package session;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import bp.ObjectBox;

public class MyApplication extends Application {
    private static Context context;
    private final String tag = MyApplication.class.getSimpleName() + "Atiar= ";

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ObjectBox.init(this);
    }

    public static Context getContext (){return context; }
    public static synchronized MyApplication getInstance(){return mInstance;}



}