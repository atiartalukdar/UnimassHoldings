package adapters;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bp.ObjectBox;
import bp.Utils;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.ClientGeneralInfoBox_;
import objectBox.ScheduleBox;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;


public class ScheduleAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private List<ScheduleBox> leadList;
    String api="";
    boolean isVerified = false;
    private final String TAG = getClass().getSimpleName() + " Atiar= ";


    Retrofit retrofit;
    APIInterface apiInterface;

    public ScheduleAdapter(Activity activity, List<ScheduleBox> leadList) {
        this.activity = activity;
        this.leadList = leadList;


        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
    }

    @Override
    public int getCount() {
        return leadList.size();
    }

    @Override
    public Object getItem(int location) {
        return leadList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = activity.getApplicationContext();
        Box<ClientGeneralInfoBox> clientBox = ObjectBox.get().boxFor(ClientGeneralInfoBox.class);;

        // getting lead data for the row
        final ScheduleBox data = leadList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_schedule_list_item, null);

        final ClientGeneralInfoBox i = clientBox.query().equal(ClientGeneralInfoBox_.clientID,Integer.parseInt(data.getGeneralInfosId())).build().findFirst();
        String mobileNumber = "", clientAddress = "", clientProgress = "";
        if (i!=null){
            mobileNumber = i.getContactNo();
            clientAddress  = i.getLandownerAddress();
            clientProgress = i.getProgressStatus();
        }



        LinearLayout _item = convertView.findViewById(R.id.custom_schedule_item);
        TextView _name = convertView.findViewById(R.id.custom_schedule_name);
        final TextView _phone = convertView.findViewById(R.id.custom_schedule_mobile);
        TextView _clientID = convertView.findViewById(R.id.custom_schedule_id);
        TextView _agent = convertView.findViewById(R.id.custom_schedule_agent);
        TextView _address = convertView.findViewById(R.id.custom_schedule_address);
        TextView _progress = convertView.findViewById(R.id.custom_schedule_progress);
        TextView _lastActionWithDate = convertView.findViewById(R.id.custom_schedule_last_action_with_date);
        TextView _nextActionWithDate = convertView.findViewById(R.id.custom_schedule_next_action_with_date);
        TextView _specialNote = convertView.findViewById(R.id.custom_schedule_special_note);
        TextView _specialNoteByAdmin = convertView.findViewById(R.id.custom_schedule_special_note_by_admin);


        _name.setText(data.getLName());
        _phone.setText(mobileNumber);
        _clientID.setText("Client ID: " + data.getGeneralInfosId());
        _agent.setText("Agent: "+data.getByWhom());
        _address.setText(clientAddress);
        _progress.setText(clientProgress+ "%");
        _lastActionWithDate.setText("Last Action: "+data.getLastactionType() + "("+data.getLastactionDate()+")");
        _nextActionWithDate.setText("Next Action: "+data.getActionType() + "("+data.getDate()+")");
        _specialNote.setText("Special Note: "+data.getSpecialNote() );
        _specialNoteByAdmin.setText("Remarks: "+data.getRemarks() );


        //Item button
        _item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadClientProfile(data.getGeneralInfosId()+"", i);
            }
        });

        //Phone number clicked event
        _phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openDialPad(activity,_phone.getText().toString());
            }
        });



        return convertView;

    }

    //To update the searchView items in TransportList Activity
    public void update(List<ScheduleBox> resuls){
        leadList = new ArrayList<>();
        leadList.addAll(resuls);
        notifyDataSetChanged();
    }

    private void loadClientProfile(String clientID, final ClientGeneralInfoBox data) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Profile Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getClientProfile(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), clientID);

        call.enqueue(new Callback<ClientProfileDM>() {
            @Override
            public void onResponse(Call<ClientProfileDM> call, Response<ClientProfileDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    Intent intent = new Intent(context, ClientDetails.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("memberDetails",data);
                    intent.putExtra("memberProfile",response.body());
                    context.startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });


    }

}