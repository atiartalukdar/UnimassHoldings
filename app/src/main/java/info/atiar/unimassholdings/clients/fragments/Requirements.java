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

public class Requirements extends Fragment {

    @BindView(R.id.reqArea)            EditText _reqArea;
    @BindView(R.id.reqLandSize)        EditText _reqLandSize;
    @BindView(R.id.reqMouza)           EditText _reqMouza;
    @BindView(R.id.reqDagNo)           EditText _reqDagNo;
    @BindView(R.id.reqRoadExisting)    EditText _reqRoadExisting;
    @BindView(R.id.reqRoadProposed)    EditText _reqRoadProposed;
    @BindView(R.id.reqSigningMoney)    EditText _reqSigningMoney;
    @BindView(R.id.reqRatio)           EditText _reqRatio;
    @BindView(R.id.reqWidth)           EditText _reqWidth;
    @BindView(R.id.reqLength)          EditText _reqLength;
    @BindView(R.id.reqFace)            EditText _reqFace;
    @BindView(R.id.reqUnit)            EditText _reqUnit;
    @BindView(R.id.editOrSaveButton)    Button _editOrSaveButton;
    Unbinder unbinder;

    ClientProfileDM.ReqInfo reqInfo;

    public Requirements() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reqInfo = ((ClientDetails) getActivity()).getMemberProfile().getReqInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requirements, container, false);
        unbinder = ButterKnife.bind(this, view);


        if (reqInfo != null){
            _reqArea.setText(reqInfo.getArea() != null ? reqInfo.getArea() : "");
            _reqLandSize.setText(reqInfo.getLandsize() != null ? reqInfo.getLandsize() : "");
            _reqMouza.setText(reqInfo.getMouza() != null ? reqInfo.getMouza() : "");
            _reqDagNo.setText(reqInfo.getDagNo() != null ? reqInfo.getDagNo() : "");
            _reqRoadExisting.setText(reqInfo.getRoadExisting() != null ? reqInfo.getRoadExisting() : "");
            _reqRoadProposed.setText(reqInfo.getRoadProposed() != null ? reqInfo.getRoadProposed() : "");

            _reqSigningMoney.setText(reqInfo.getSigningMoney() != null ? reqInfo.getSigningMoney() : "");
            _reqRatio.setText(reqInfo.getRatio() != null ? reqInfo.getRatio() : "");
            _reqWidth.setText(reqInfo.getWidth() != null ? reqInfo.getWidth() : "");
            _reqLength.setText(reqInfo.getLength() != null ? reqInfo.getLength() : "");
            _reqFace.setText(reqInfo.getFace() != null ? reqInfo.getFace() : "");
            _reqUnit.setText(reqInfo.getUnit() != null ? reqInfo.getUnit() : "");
        }else {
            _reqArea.setText("");
            _reqLandSize.setText("");
            _reqMouza.setText("");
            _reqDagNo.setText("");
            _reqRoadExisting.setText("");
            _reqRoadProposed.setText("");
            _reqSigningMoney.setText("");
            _reqRatio.setText("");
            _reqWidth.setText("");
            _reqLength.setText("");
            _reqFace.setText("");
            _reqUnit.setText("");
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
                _reqArea.setEnabled(true);
                _reqLandSize.setEnabled(true);
                _reqMouza.setEnabled(true);
                _reqDagNo.setEnabled(true);
                _reqRoadExisting.setEnabled(true);
                _reqRoadProposed.setEnabled(true);
                _reqSigningMoney.setEnabled(true);
                _reqRatio.setEnabled(true);
                _reqWidth.setEnabled(true);
                _reqLength.setEnabled(true);
                _reqFace.setEnabled(true);
                _reqUnit.setEnabled(true);

                _editOrSaveButton.setText("Save");
            }else {
                _reqArea.setEnabled(false);
                _reqLandSize.setEnabled(false);
                _reqMouza.setEnabled(false);
                _reqDagNo.setEnabled(false);
                _reqRoadExisting.setEnabled(false);
                _reqRoadProposed.setEnabled(false);
                _reqSigningMoney.setEnabled(false);
                _reqRatio.setEnabled(false);
                _reqWidth.setEnabled(false);
                _reqLength.setEnabled(false);
                _reqFace.setEnabled(false);
                _reqUnit.setEnabled(false);

                _editOrSaveButton.setText("Edit");

            }

        }else {
            Utils.showDialog(getActivity(),"You don't have permission",3);

        }
    }
}
