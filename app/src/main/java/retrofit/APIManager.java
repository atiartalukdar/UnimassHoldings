package retrofit;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import bp.Utils;
import dataModel.OneSignalDM;
import dataModel.OneSignalResponseDM;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import session.MyApplication;
import session.Session;

public class APIManager {
    private static final String BASE_URL = "http://photodesignexpert.com/unimassfiles/api/";
    private static final String BASE_URL_OneSignal = "https://onesignal.com/api/v1/";
    private final APIInterface api;
    private final APIInterface apiOneSignal;
    private Context _context;

    public APIManager(){

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        _context = MyApplication.getContext();
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retrofit retrofitOneSignal = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL_OneSignal)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(APIInterface.class);
        apiOneSignal = retrofitOneSignal.create(APIInterface.class);
    }

    public void addCommunicationRemarks(String remarks, int recordID, RequestListener<String> listener) {
        api.addRemarks(Session.getSeassionData().getEmail(),Session.getPassword(),Session.getUserRole(), remarks,recordID ).enqueue(new APICallback<>(_context,listener));
    }

    public void sendNotification(OneSignalDM oneSignalDM, RequestListener<OneSignalResponseDM> listener) {
        apiOneSignal.sendNotification(Utils.getHeaders(),oneSignalDM);
    }


}
