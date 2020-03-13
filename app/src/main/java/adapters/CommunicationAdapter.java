package adapters;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bp.ObjectBox;
import bp.Utils;
import cn.pedant.SweetAlert.SweetAlertDialog;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.clients.ClientsList;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.ClientGeneralInfoBox_;
import objectBox.SpecificCommRecordBox;
import retrofit.APIInterface;
import retrofit.APIManager;
import retrofit.RequestListener;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;


public class CommunicationAdapter extends BaseAdapter {
    private final String TAG = getClass().getSimpleName() + " Atiar= ";
    APIManager _apiManager = new APIManager();
    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private List<CommunicationDM.SpecificCommRecord> leadList;

    Retrofit retrofit;
    APIInterface apiInterface;

    public CommunicationAdapter(Activity activity, List<CommunicationDM.SpecificCommRecord> leadList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        context = activity.getApplicationContext();
        Box<ClientGeneralInfoBox> clientBox = ObjectBox.get().boxFor(ClientGeneralInfoBox.class);;

        // getting lead data for the row
        final CommunicationDM.SpecificCommRecord data = leadList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_communication_list_item, null);

        LinearLayout _item = convertView.findViewById(R.id.custom_schedule_item);
        TextView _name = convertView.findViewById(R.id.custom_schedule_name);
        final TextView _phone = convertView.findViewById(R.id.custom_schedule_mobile);
        TextView _clientID = convertView.findViewById(R.id.custom_schedule_id);
        TextView _agent = convertView.findViewById(R.id.custom_schedule_agent);
        TextView _progress = convertView.findViewById(R.id.custom_schedule_progress);

        TextView _lastActionWithDate = convertView.findViewById(R.id.custom_schedule_address);
        TextView _nextActionWithDate = convertView.findViewById(R.id.custom_schedule_last_action_with_date);
        TextView _details = convertView.findViewById(R.id.custom_schedule_next_action_with_date);
        TextView _specialNote = convertView.findViewById(R.id.custom_schedule_special_note);
        TextView _specialNoteByAdmin = convertView.findViewById(R.id.custom_schedule_special_note_by_admin);

        _name.setText(data.getLName());
        _phone.setText(((ClientDetails) activity).getMemberProfile().getGeneralInfo().getContactNo());
        _clientID.setText("Record ID: " + data.getId());
        _agent.setText("By: "+data.getByWhom());
        _progress.setText(((ClientDetails) activity).getMemberProfile().getGeneralInfo().getProgressStatus() + "%");
        _lastActionWithDate.setText("Meeting Place : " + data.getLocation());
        _nextActionWithDate.setText("Next : "+data.getActionType() + "("+data.getDate()+")");
        _details.setText("Details: "+data.getDetails() );
        _specialNote.setText("Special Note: "+data.getSpecialNote() );
        _specialNoteByAdmin.setText("Remarks: "+data.getRemarks() );

        //Phone number clicked event
        _phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openDialPad(activity,_phone.getText().toString());
            }
        });

        //Prescreening button
        _item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Session.getUserRole().equals("admin")){
                    showAddRemarksPopup(data.getId(),position);
                }else {
                    Utils.showDialog(activity,"You don't have this permission !!!", SweetAlertDialog.WARNING_TYPE);
                }
            }
        });
        return convertView;
    }

    //To update the searchView items in TransportList Activity
    public void update(List<CommunicationDM.SpecificCommRecord> resuls){
        leadList = new ArrayList<>();
        leadList.addAll(resuls);
        notifyDataSetChanged();
    }

    private void showAddRemarksPopup(final int recodID, final int position){
        final EditText taskEditText = new EditText(activity);
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("Record id " + recodID)
                .setMessage("Add Remarks")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        dialog.dismiss();
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Updating Remarks...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        final String remarks = String.valueOf(taskEditText.getText());

                        leadList.get(position).setRemarks(remarks);
                        notifyDataSetChanged();
                        _apiManager.addCommunicationRemarks(remarks, recodID, new RequestListener<String>() {
                            @Override
                            public void onSuccess(String response) {
                                progressDialog.dismiss();
                                Toast.makeText(activity, response.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable t) {
                                progressDialog.dismiss();
                                notifyDataSetChanged();
                            }
                        });
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}