package session;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.TimeUtils;

import com.google.gson.Gson;

import info.atiar.unimassholdings.dataModel.LoginData;


/**
 * Created by Atiar on 5/23/18.
 */

public class Session {

    private static final String PREFS_NAME = "preferenceName";
    private static final String key_login_password = "loginPassword";
    private static final String key_user_role = "userRole";
    private static final String key_login_data = "sessionLoginData";
    private static final String key_login_isLoggedIn = "sessionLoginIsLoggedIn";

    /*****************************//* Strat shared preferences *******************************/

    public static boolean setPreference(String key, String value) {
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "None");
    }

    public static boolean setPreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getPreferenceInt(Context context, String key) {
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, 0);
    }


    /*****************************//* End shared preferences *//******************************/



    //============================  ##Session Data ##  ===========================//

    // public methods
    public static boolean createSeassion(String password, String role, LoginData.GetData loginData){
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(loginData);
        editor.putString(key_login_data, json);
        editor.putString(key_login_password,password);
        editor.putString(key_user_role,role);
        editor.putString(key_login_isLoggedIn, "true");
        return editor.commit();
    }


    public static boolean isLoggedIn(){
        boolean isL = false;
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String isLogin =  settings.getString(key_login_isLoggedIn, "false");

        if (isLogin.equals("true")) {
            isL = true;
        }else {
            isL = false;
        }

        return isL;
    }

    public static LoginData getSeassionData(){
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = settings.getString(key_login_data, "");
        LoginData obj = gson.fromJson(json, LoginData.class);
        return obj;
    }

    public static String getPassword(){
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String password = settings.getString(key_login_password, "");
        return password;
    }

    public static String getUserRole(){
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String role = settings.getString(key_user_role, "");
        return role;
    }

    public static void clearSession(){
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }

    public static boolean logout(){
        SharedPreferences settings = MyApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key_login_isLoggedIn, "false");
        return editor.commit();
    }



    //============================  ##Session Data ##  ===========================//



}