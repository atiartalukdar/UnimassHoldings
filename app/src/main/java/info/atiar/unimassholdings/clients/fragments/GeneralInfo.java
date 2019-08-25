package info.atiar.unimassholdings.clients.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.annotation.Nullable;

import bp.TimeUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import info.atiar.unimassholdings.HomePage;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.AddNewClientGeneralInfoActivity;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

/**
 * Created by Atiar Talukdar on 7/11/2019.
 */

public class GeneralInfo extends Fragment {
    private final String TAG = getClass().getName() + " Atiar - ";
    Retrofit retrofit;
    APIInterface apiInterface;

    @Nullable
    ClientProfileDM profileDM;
    @BindView(R.id.editOrSaveButton)            Button _editOrSaveButton;
    @BindView(R.id.gInfoEmail)                  EditText _gInfoEmail;
    @BindView(R.id.gInfoProfession)             EditText _gInfoProfession;
    @BindView(R.id.gInfoDesignation)            EditText _gInfoDesignation;
    @BindView(R.id.gInfoBizAddress)             EditText _gInfoBizAddress;
    @BindView(R.id.gInfoOpening_date)           EditText _gInfoOpeningDate;
    @BindView(R.id.gInfoRef_source)             EditText _gInfoRefSource;
    @BindView(R.id.gInfoRef_source_name)        EditText _gInfoRefSourceName;
    @BindView(R.id.gInfoRef_source_mobile)      EditText _gInfoRefSourceMobile;
    Unbinder unbinder;

    public GeneralInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_info, container, false);
        unbinder = ButterKnife.bind(this, v);
        profileDM = ((ClientDetails) getActivity()).getMemberProfile();

        ClientProfileDM.GeneralInfo generalInfo = profileDM.getGeneralInfo();
        try {
            if (generalInfo != null) {
                _gInfoEmail.setText(generalInfo.getEmail());
                _gInfoProfession.setText(generalInfo.getProfession());
                _gInfoDesignation.setText(generalInfo.getDesignation());
                _gInfoBizAddress.setText(generalInfo.getBusinessAddress());
                _gInfoOpeningDate.setText(generalInfo.getOpeningDate());
                _gInfoRefSource.setText(generalInfo.getRefSource());
                _gInfoRefSourceName.setText(generalInfo.getRefSourceName());
                _gInfoRefSourceMobile.setText(generalInfo.getRefSourceMobile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.editOrSaveButton)
    public void onViewClicked() {
        if (_editOrSaveButton.getText().toString().toLowerCase().trim().equals("edit")) {
            _gInfoEmail.setEnabled(true);
            _gInfoProfession.setEnabled(true);
            _gInfoDesignation.setEnabled(true);
            _gInfoBizAddress.setEnabled(true);
            _gInfoOpeningDate.setEnabled(true);
            _gInfoRefSource.setEnabled(true);
            _gInfoRefSourceName.setEnabled(true);
            _gInfoRefSourceMobile.setEnabled(true);

            updateGeneralInfo(profileDM.getGeneralInfo().getId()+"",profileDM.getGeneralInfo().getLandownerName(),
                    profileDM.getGeneralInfo().getLandownerAddress(),profileDM.getGeneralInfo().getRefSourceMobile(),
                    _gInfoEmail.getText().toString(),_gInfoProfession.getText().toString(),_gInfoDesignation.getText().toString(),
                    _gInfoBizAddress.getText().toString(),_gInfoRefSourceName.getText().toString(),_gInfoRefSourceMobile.getText().toString());
        } else {
            _gInfoEmail.setEnabled(false);
            _gInfoProfession.setEnabled(false);
            _gInfoDesignation.setEnabled(false);
            _gInfoBizAddress.setEnabled(false);
            _gInfoOpeningDate.setEnabled(false);
            _gInfoRefSource.setEnabled(false);
            _gInfoRefSourceName.setEnabled(false);
            _gInfoRefSourceMobile.setEnabled(false);

            _editOrSaveButton.setText("Edit");

        }
    }

    private void updateGeneralInfo(String cliendID, String landOwnerName, String address, String mobile, String email, String profession,
                                         String designation, String businessAddress, String reference, String refMobile) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating General Info...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.updateClientGeneralInfo(
                Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), cliendID, TimeUtils.getToday(), "Mr.",
                landOwnerName, address, mobile, email, profession, designation, businessAddress, reference, refMobile);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.e(TAG, response.body().toString());
                    Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_SHORT).show();

                    _editOrSaveButton.setText("Save");

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage());
                progressDialog.dismiss();
            }

        });

    }
}
