package info.atiar.unimassholdings.clients.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import javax.annotation.Nullable;

import bp.ObjectBox;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import io.objectbox.Box;
import objectBox.ClientGeneralInfoBox;

/**
 * Created by Atiar Talukdar on 7/11/2019.
 */

public class GeneralInfo extends Fragment {

    @Nullable
    ClientProfileDM profileDM;
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
        View v = inflater.inflate(R.layout.fragment_general_info, container, false);
        profileDM = ((ClientDetails) getActivity()).getMemberProfile();

        EditText _email = v.findViewById(R.id.gInfoEmail);
        EditText _profession = v.findViewById(R.id.gInfoProfession);
        EditText _designation = v.findViewById(R.id.gInfoDesignation);
        final EditText _business_address = v.findViewById(R.id.gInfoBizAddress);

        EditText _opening_date = v.findViewById(R.id.gInfoOpening_date);
        EditText _ref_source = v.findViewById(R.id.gInfoRef_source);
        EditText _ref_source_name = v.findViewById(R.id.gInfoRef_source_name);
        EditText _ref_source_mobile = v.findViewById(R.id.gInfoRef_source_mobile);

        Button _saveOrEdit = v.findViewById(R.id.editOrSaveButton);

        ClientProfileDM.GeneralInfo generalInfo = profileDM.getGeneralInfo();
        try {
            if (generalInfo != null) {
                _email.setText(generalInfo.getEmail());
                _profession.setText(generalInfo.getProfession());
                _designation.setText(generalInfo.getDesignation());
                _business_address.setText(generalInfo.getBusinessAddress());
                _opening_date.setText(generalInfo.getOpeningDate());
                _ref_source.setText(generalInfo.getRefSource());
                _ref_source_name.setText(generalInfo.getRefSourceName());
                _ref_source_mobile.setText(generalInfo.getRefSourceMobile());
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return v;
    }
}
