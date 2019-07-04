package retrofit;

import info.atiar.unimassholdings.dataModel.LoginData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface APIInterface {

    @POST("login")
    @FormUrlEncoded
    Call<LoginData> login(@Field("email") String email,
                               @Field("password") String password,
                               @Field("role") String role);


}