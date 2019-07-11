package retrofit;

import info.atiar.unimassholdings.dataModel.ClientBox;
import info.atiar.unimassholdings.dataModel.ClientProfileBox;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import info.atiar.unimassholdings.dataModel.LoginData;
import info.atiar.unimassholdings.dataModel.ScheduleDM;
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


    @POST("client_list")
    @FormUrlEncoded
    Call<ClientBox> getAllClients(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("role") String role);


    @POST("client_profile")
    @FormUrlEncoded
    Call<ClientProfileBox> getClientProfile(@Field("email") String email,
                                            @Field("password") String password,
                                            @Field("role") String role,
                                            @Field("id") String id);


    @POST("comm_records_specific")
    @FormUrlEncoded
    Call<CommunicationDM> getClientCommunicationRecords(@Field("email") String email,
                                                        @Field("password") String password,
                                                        @Field("role") String role,
                                                        @Field("id") String id);


    public static String TODAY = "today";
    public static String TOMORROW = "tomorrow";
    public static String WEEK = "week";
    public static String MONTH = "month";
    public static String ALL = "all";

    @POST("get_specific_schedules")
    @FormUrlEncoded
    Call<ScheduleDM> getSchedule(@Field("email") String email,
                                 @Field("password") String password,
                                 @Field("role") String role,
                                 @Field("range") String range);


    @POST("comm_records_specific")
    @FormUrlEncoded
    Call<ClientBox> addClientCommunicationRecords(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("role") String role,
                                  @Field("id") String id,
                                  @Field("l_name") String l_name,
                                  @Field("interaction_type") String interaction_type,
                                  @Field("interaction_date") String interaction_date,
                                  @Field("details") String details,
                                  @Field("special_note") String special_note,
                                  @Field("action_type") String action_type,
                                  @Field("by_whom") String by_whom,
                                  @Field("date") String date,
                                  @Field("location") String location);


}