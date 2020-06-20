package adapters;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

import bp.ObjectBox;
import bp.Utils;
import cn.pedant.SweetAlert.SweetAlertDialog;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.NotificationBox;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;


public class NotificationListAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private List<NotificationBox> notificationList;

    private final String TAG = getClass().getSimpleName() + " Atiar= ";
    Box<NotificationBox> testBox = ObjectBox.get().boxFor(NotificationBox.class);

    Retrofit retrofit;
    APIInterface apiInterface;

    public NotificationListAdapter(Activity activity, List<NotificationBox> notificationList) {
        this.activity = activity;
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int location) {
        return notificationList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = activity.getApplicationContext();

        // getting lead data for the row
        final NotificationBox data = notificationList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_notification_list_item, null);


        CardView         _item      = convertView.findViewById(R.id.custom_notification_item);
        TextView        _id         = convertView.findViewById(R.id.custom_notification_id);
        TextView        _date       = convertView.findViewById(R.id.custom_notification_date);
        TextView        _note       = convertView.findViewById(R.id.custom_notification_note);


        _id.setText(data.getTitle()+"");
        _date.setText(data.getDate()+"");
        _note.setText(data.getMessage());

        if (data.getIsRead().equals("0")){
            _item.setCardBackgroundColor(activity.getResources().getColor(R.color.background));
        }else{
            _item.setCardBackgroundColor(activity.getResources().getColor(R.color.colorWhite));
        }



        //Item button
        _item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadClientProfile(position, data.getClientID()+"");
            }
        });
        return convertView;

    }

    //To update the searchView items in TransportList Activity
    public void update(List<NotificationBox> resuls){
         notificationList = new ArrayList<>();
        notificationList.addAll(resuls);
        notifyDataSetChanged();
    }

    private void loadClientProfile(int pos, String clientID) {

        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
        NotificationBox notificationBox = testBox.get(pos);

        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);

        Call call = apiInterface.getClientProfile(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), clientID);

        call.enqueue(new Callback<ClientProfileDM>() {
            @Override
            public void onResponse(Call<ClientProfileDM> call, Response<ClientProfileDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                pDialog.cancel();
                if (response.isSuccessful()) {
                    Log.e(TAG, "position - "+pos);
                    notificationBox.setIsRead("1");
                    testBox.put(notificationBox);

                    Intent intent = new Intent(context, ClientDetails.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.putExtra("memberDetails",data);
                    intent.putExtra("memberProfile", response.body());
                    context.startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                pDialog.cancel();
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }

        });


    }


}