package info.atiar.unimassholdings;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.atiar.unimassholdings.dataModel.LoginData;
import retrofit.APIInterface;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import session.Session;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getName() + " Atiar - ";

    @BindView(R.id.email)    EditText _email;
    @BindView(R.id.password)    EditText _password;


    Retrofit retrofit;
    APIInterface apiInterface;

    String userRole = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);

    }

    public void signin(View view) {

        if (_email.getText().toString().trim().equals("sshalehin@gmail.com")||_email.getText().toString().trim().equals("swajan.talukdar@gmail.com")){
            userRole = "admin";
        }else {
            userRole = "agent";
        }

        if (_email.getText().toString() != null || !_email.getText().toString().isEmpty() ||_password.getText().toString() != null || !_password.getText().toString().isEmpty()){
            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait a while...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            Call call = apiInterface.login(_email.getText().toString(),_password.getText().toString(),userRole);

            call.enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                    /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                     */
                    progressDialog.dismiss();

                    if (response.isSuccessful()){
                        LoginData.GetData g = response.body().getUserdata();
                        Session.createSeassion(_password.getText().toString(),userRole, g);
                        Log.e(TAG, g.toString());

                        startActivity(new Intent(MainActivity.this,HomePage.class));
                        finish();
                    }

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            });
        }else {
            Toast.makeText(this, "Please fill up correctly", Toast.LENGTH_SHORT).show();

        }

    }
}
