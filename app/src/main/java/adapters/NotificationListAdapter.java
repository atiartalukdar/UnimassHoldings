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
import androidx.recyclerview.widget.RecyclerView;

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


public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private List<NotificationBox> notificationList;

    private final String TAG = getClass().getSimpleName() + " Atiar= ";
    Box<NotificationBox> testBox = ObjectBox.get().boxFor(NotificationBox.class);

    Retrofit retrofit;
    APIInterface apiInterface;

    public NotificationListAdapter(Activity activity, List<NotificationBox> notificationList) {
        this.activity = activity;
        this.notificationList = notificationList;
        context = activity.getApplicationContext();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_notification_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // getting lead data for the row
        final NotificationBox data = notificationList.get(position);

        holder._id.setText(data.getTitle()+"");
        holder._date.setText(data.getDate()+"");
        holder._note.setText(data.getMessage()+"");

        if (data.getIsRead().equals("0")){
            holder._item.setCardBackgroundColor(activity.getResources().getColor(R.color.background));
        }else{
            holder._item.setCardBackgroundColor(activity.getResources().getColor(R.color.colorWhite));
        }


        //Item button
        holder._item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadClientProfile(data, data.getClientID()+"");
            }
        });

    }


    @Override
    public int getItemCount() {
        if (notificationList == null) {
            return 0;
        }

        return notificationList.size();
    }

    //To update the searchView items in TransportList Activity
    public void update(List<NotificationBox> resuls){
         notificationList = new ArrayList<>();
        notificationList.addAll(resuls);
        notifyDataSetChanged();
    }

    private void loadClientProfile(NotificationBox  notificationBox, String clientID) {

        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

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
                    notificationBox.setIsRead("1");
                    testBox.put(notificationBox);

                    Intent intent = new Intent(activity, ClientDetails.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.putExtra("memberDetails",data);
                    intent.putExtra("memberProfile", response.body());
                    activity.startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                pDialog.cancel();
                Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();
            }

        });


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView         _item     ;
        TextView        _id        ;
        TextView        _date      ;
        TextView        _note      ;

        LinearLayout _leadListItem;

        public MyViewHolder(View convertView) {
            super(convertView);
              _item  = convertView.findViewById(R.id.custom_notification_item);
             _id     = convertView.findViewById(R.id.custom_notification_id);
             _date   = convertView.findViewById(R.id.custom_notification_date);
             _note   = convertView.findViewById(R.id.custom_notification_note);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}