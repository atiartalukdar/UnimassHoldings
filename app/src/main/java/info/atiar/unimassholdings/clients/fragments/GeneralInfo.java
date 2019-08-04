package info.atiar.unimassholdings.clients.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import bp.ObjectBox;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;
import objectBox.ClientGeneralInfoBox_;
import objectBox.ClientOtherInfoBox;

/**
 * Created by Atiar Talukdar on 7/11/2019.
 */

public class GeneralInfo extends Fragment {

    Box<ClientGeneralInfoBox> clientGeneralInfoBoxBox;

    public GeneralInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        clientGeneralInfoBoxBox = ObjectBox.get().boxFor(ClientGeneralInfoBox.class);
        ClientGeneralInfoBox data = ((ClientDetails) getActivity()).getMember();

        View v = inflater.inflate(R.layout.fragment_we_are_working_on_it, container, false);
        EditText _email = v.findViewById(R.id.gInfoEmail);
        EditText _profession = v.findViewById(R.id.gInfoProfession);
        EditText _designation = v.findViewById(R.id.gInfoDesignation);
        final EditText _business_address = v.findViewById(R.id.gInfoBizAddress);

        EditText _opening_date = v.findViewById(R.id.gInfoOpening_date);
        EditText _ref_source = v.findViewById(R.id.gInfoRef_source);
        EditText _ref_source_name = v.findViewById(R.id.gInfoRef_source_name);
        EditText _ref_source_mobile = v.findViewById(R.id.gInfoRef_source_mobile);

        Button _saveOrEdit = v.findViewById(R.id.editOrSaveButton);

        try {
            if (data != null) {
                _email.setText(data.getEmail());
                _profession.setText(data.getProfession());
                _designation.setText(data.getDesignation());
                _business_address.setText(data.getBusinessAddress());
                _opening_date.setText(data.getOpeningDate());
                _ref_source.setText(data.getRefSource());
                _ref_source_name.setText(data.getRefSourceName());
                _ref_source_mobile.setText(data.getRefSourceMobile());
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return v;
    }
}
