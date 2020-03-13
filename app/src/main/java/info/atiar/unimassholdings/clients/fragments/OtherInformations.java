package info.atiar.unimassholdings.clients.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import bp.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import session.Session;

/**
 * Created by Atiar Talukdar on 7/11/2019.
 */

public class OtherInformations extends Fragment {

    @BindView(R.id.otherSpouseName)         EditText _otherSpouseName;
    @BindView(R.id.otherSpouseProf)         EditText _otherSpouseProf;
    @BindView(R.id.otherSpousePhone)        EditText _otherSpousePhone;
    @BindView(R.id.otherChildren)           EditText _otherChildren;
    @BindView(R.id.otherDecisionMaker)      EditText _otherDecisionMaker;
    @BindView(R.id.otherLandownerEducation) EditText _otherLandownerEducation;
    @BindView(R.id.otherHometown)           EditText _otherHometown;
    @BindView(R.id.otherAgePersonality)     EditText _otherAgePersonality;
    @BindView(R.id.otherKeyFactor)          EditText _otherKeyFactor;
    @BindView(R.id.otherMarketKnowledge)    EditText _otherMarketKnowledge;

    @BindView(R.id.otherManagementEvaluation)
    EditText _otherManagementEvaluation;
    @BindView(R.id.editOrSaveButton)
    Button _editOrSaveButton;
    Unbinder unbinder;

    ClientProfileDM.OtherInfo otherInfo;

    public OtherInformations() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        otherInfo = ((ClientDetails) getActivity()).getMemberProfile().getOtherInfo();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other_info, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (otherInfo != null){
            _otherSpouseName.setText(otherInfo.getSpouseName() != null ? otherInfo.getSpouseName() : "");
            _otherSpouseProf.setText(otherInfo.getSpouseProf() != null ? otherInfo.getSpouseProf() : "");
            _otherSpousePhone.setText(otherInfo.getSpousePhone() != null ? otherInfo.getSpousePhone() : "");
            _otherChildren.setText(otherInfo.getChildren() != null ? otherInfo.getChildren() : "");
            _otherDecisionMaker.setText(otherInfo.getDecisionMaker() != null ? otherInfo.getDecisionMaker() : "");
            _otherLandownerEducation.setText(otherInfo.getLandownerEducation()!= null ? otherInfo.getLandownerEducation() : "");
            _otherHometown.setText(otherInfo.getHometown() != null ? otherInfo.getHometown() : "");
            _otherAgePersonality.setText(otherInfo.getAgePersonality() != null ? otherInfo.getAgePersonality() : "");
            _otherKeyFactor.setText(otherInfo.getKeyFactor() != null ? otherInfo.getKeyFactor() : "");
            _otherMarketKnowledge.setText(otherInfo.getMarketKnowledge() != null ? otherInfo.getMarketKnowledge() : "");
        }else {
            _otherSpouseName.setText("");
            _otherSpouseProf.setText("");
            _otherSpousePhone.setText("");
            _otherChildren.setText("");
            _otherDecisionMaker.setText("");
            _otherLandownerEducation.setText("");
            _otherHometown.setText("");
            _otherAgePersonality.setText("");
            _otherKeyFactor.setText("");
            _otherMarketKnowledge.setText("");
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.editOrSaveButton)
    public void onViewClicked() {

        if (Session.getUserRole().toLowerCase().contains("admin")){
            if (_editOrSaveButton.getText().toString().toLowerCase().trim().equals("edit")){
                _otherSpouseName.setEnabled(true);
                _otherSpouseProf.setEnabled(true);
                _otherSpousePhone.setEnabled(true);
                _otherChildren.setEnabled(true);
                _otherDecisionMaker.setEnabled(true);
                _otherLandownerEducation.setEnabled(true);
                _otherHometown.setEnabled(true);
                _otherAgePersonality.setEnabled(true);
                _otherKeyFactor.setEnabled(true);
                _otherMarketKnowledge.setEnabled(true);

                _editOrSaveButton.setText("Save");
            }else {
                _otherSpouseName.setEnabled(false);
                _otherSpouseProf.setEnabled(false);
                _otherSpousePhone.setEnabled(false);
                _otherChildren.setEnabled(false);
                _otherDecisionMaker.setEnabled(false);
                _otherLandownerEducation.setEnabled(false);
                _otherHometown.setEnabled(false);
                _otherAgePersonality.setEnabled(false);
                _otherKeyFactor.setEnabled(false);
                _otherMarketKnowledge.setEnabled(false);

                _editOrSaveButton.setText("Edit");

            }
        }else {
            Utils.showDialog(getActivity(),"You don't have permission",3);

        }
    }
}
