package info.atiar.unimassholdings.clients;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import bp.TimeUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.atiar.unimassholdings.HomePage;
import info.atiar.unimassholdings.R;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

public class AddNewClientGeneralInfoActivity extends AppCompatActivity {
    private final String TAG = getClass().getName() + " Atiar - ";

    @BindView(R.id.landOwnerName)
    EditText _landOwnerName;
    @BindView(R.id.address)
    EditText _address;
    @BindView(R.id.mobile)
    EditText _mobile;
    @BindView(R.id.email)
    EditText _email;
    @BindView(R.id.profession)
    EditText _profession;
    @BindView(R.id.designation)
    EditText _designation;
    @BindView(R.id.businessAddress)
    EditText _businessAddress;
    @BindView(R.id.reference)
    EditText _reference;
    @BindView(R.id.btnAddLandOwner)
    AppCompatButton _btnAddLandOwner;


    Retrofit retrofit;
    APIInterface apiInterface;
    @BindView(R.id.referenceMobile)
    EditText _referenceMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client_general_info);
        ButterKnife.bind(this);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
    }

    @OnClick(R.id.btnAddLandOwner)
    public void onViewClicked() {
        if (validator()){
            addLandOwnerGeneralInfo(_landOwnerName.getText().toString(), _address.getText().toString(),
                    _mobile.getText().toString(), _email.getText().toString(), _profession.getText().toString(),
                    _designation.getText().toString(), _businessAddress.getText().toString(), _reference.getText().toString(),
                    _referenceMobile.getText().toString());
        }

    }

    private void addLandOwnerGeneralInfo(String landOwnerName, String address, String mobile, String email, String profession,
                                         String designation, String businessAddress, String reference, String refMobile) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding New Client...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.addClientGeneralInfo(
                Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), TimeUtils.getToday(), "Mr.",
                landOwnerName, address, mobile, email, profession, designation, businessAddress, reference, refMobile);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.e(TAG, response.body().toString());
                    Toast.makeText(AddNewClientGeneralInfoActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AddNewClientGeneralInfoActivity.this, HomePage.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(AddNewClientGeneralInfoActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                Log.e(TAG, call.toString());
                Log.e(TAG, t.getMessage());
                progressDialog.dismiss();
            }

        });

    }

    private boolean validator(){
        boolean validator = false;

        if (validate(_landOwnerName)){
            validator =  true;
        }
        if (validate(_address)){
            validator =  true;
        }
        if (validate(_mobile)){
            validator =  true;
        }
        if (validate(_email)){
            validator =  true;
        }
        if (validate(_profession)){
            validator =  true;
        }
        if (validate(_designation)){
            validator =  true;
        }
        if (validate(_businessAddress)){
            validator =  true;
        }
        if (validate(_reference)){
            validator =  true;
        }
        if (validate(_referenceMobile)){
            validator =  true;
        }

        return validator;

    }

    private boolean validate(EditText editText){
        if (editText.getText().toString() == null || editText.getText().toString().equals("")){
            editText.setError("Required");
            return false;
        }

        return true;
    }
}
