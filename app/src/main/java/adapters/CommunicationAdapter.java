package adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bp.ObjectBox;
import bp.Utils;
import info.atiar.unimassholdings.R;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.ClientGeneralInfoBox_;
import objectBox.SpecificCommRecordBox;


public class CommunicationAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private List<SpecificCommRecordBox> leadList;
    String api="";
    boolean isVerified = false;
    private final String TAG = getClass().getSimpleName() + " Atiar= ";

    public CommunicationAdapter(Activity activity, List<SpecificCommRecordBox> leadList) {
        this.activity = activity;
        this.leadList = leadList;
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
        final SpecificCommRecordBox data = leadList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_communication_list_item, null);

        ClientGeneralInfoBox i = clientBox.query().equal(ClientGeneralInfoBox_.clientID,Integer.parseInt(data.getGeneralInfosId())).build().findFirst();
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
        TextView _progress = convertView.findViewById(R.id.custom_schedule_progress);

        TextView _lastActionWithDate = convertView.findViewById(R.id.custom_schedule_address);
        TextView _nextActionWithDate = convertView.findViewById(R.id.custom_schedule_last_action_with_date);
        TextView _details = convertView.findViewById(R.id.custom_schedule_next_action_with_date);
        TextView _specialNote = convertView.findViewById(R.id.custom_schedule_special_note);
        TextView _specialNoteByAdmin = convertView.findViewById(R.id.custom_schedule_special_note_by_admin);


        _name.setText(data.getLName());
        _phone.setText(mobileNumber);
        _clientID.setText("Record ID: " + data.getRecordID());
        _agent.setText("By: "+data.getByWhom());
        _progress.setText(clientProgress+ "%");
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

            }
        });
        return convertView;
    }

    //To update the searchView items in TransportList Activity
    public void update(List<SpecificCommRecordBox> resuls){
        leadList = new ArrayList<>();
        leadList.addAll(resuls);
        notifyDataSetChanged();
    }

}