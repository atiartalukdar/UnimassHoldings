package adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bp.Utils;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import objectBox.ClientGeneralInfoBox;


public class ClientListAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private List<ClientGeneralInfoBox> leadList;
    String api="";
    boolean isVerified = false;
    private final String TAG = getClass().getSimpleName() + " Atiar= ";

    public ClientListAdapter(Activity activity, List<ClientGeneralInfoBox> leadList) {
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

        // getting lead data for the row
        final ClientGeneralInfoBox data = leadList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_client_list_item, null);


        LinearLayout    _item      = convertView.findViewById(R.id.custom_client_item);
        TextView        _name          = convertView.findViewById(R.id.custom_client_name);
        final TextView  _phone   = convertView.findViewById(R.id.custom_client_mobile);
        TextView        _clientID      = convertView.findViewById(R.id.custom_client_id);
        TextView        _agent         = convertView.findViewById(R.id.custom_client_agent);
        TextView        _address       = convertView.findViewById(R.id.custom_client_address);
        TextView        _progress      = convertView.findViewById(R.id.custom_client_progress);


        _name.setText(data.getLandownerName());
        _phone.setText(data.getContactNo());
        _clientID.setText("Client ID: " + data.getClientID());
        _agent.setText("Agent: "+data.getAgentId());
        _address.setText("Address: " + data.getLandownerAddress());
        _progress.setText(data.getProgressStatus() + "%");



        //Item button
        _item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClientDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("memberDetails",data);
                context.startActivity(intent);
            }
        });
        //Phone number clicked event
        _phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openDialPad(activity,data.getContactNo());
            }
        });
        return convertView;

    }

    //To update the searchView items in TransportList Activity
    public void update(List<ClientGeneralInfoBox> resuls){
        leadList = new ArrayList<>();
        leadList.addAll(resuls);
        notifyDataSetChanged();
    }
}