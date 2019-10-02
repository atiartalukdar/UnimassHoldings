package retrofit;

import info.atiar.unimassholdings.dataModel.AddClientGeneralInfoDM;
import info.atiar.unimassholdings.dataModel.ClientBox;
import info.atiar.unimassholdings.dataModel.ClientProfileDM;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import info.atiar.unimassholdings.dataModel.LoginData;
import info.atiar.unimassholdings.dataModel.MessageDM;
import info.atiar.unimassholdings.dataModel.ScheduleDM;
import retrofit2.Call;
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
    Call<ClientProfileDM> getClientProfile(@Field("email") String email,
                                           @Field("password") String password,
                                           @Field("role") String role,
                                           @Field("id") String id);


    @POST("add_remarks")
    @FormUrlEncoded
    Call<String> addRemarks(@Field("email") String email,
                                           @Field("password") String password,
                                           @Field("role") String role,
                                           @Field("remarks") String remarks,
                                           @Field("record_id") int record_id);


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


    @POST("comm_records_add")
    @FormUrlEncoded
    Call<MessageDM> addClientCommunicationRecords(@Field("email") String email,
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


    @POST("add_gen_info")
    @FormUrlEncoded
    Call<Object> addClientGeneralInfo(@Field("email") String email,
                                      @Field("password") String password,
                                      @Field("role") String role,
                                      @Field("opening_date") String opening_date,
                                      @Field("prefix") String prefix,
                                      @Field("landowner_name") String landowner_name,
                                      @Field("landowner_address") String landowner_address,
                                      @Field("contact_no") String contact_no,
                                      @Field("lo_email") String lo_email,
                                      @Field("profession") String profession,
                                      @Field("designation") String designation,
                                      @Field("business_address") String business_address,
                                      @Field("ref_source") String ref_source,
                                      @Field("ref_phone") String ref_phone);

    @POST("add_gen_info")
    @FormUrlEncoded
    Call<String> updateClientGeneralInfo(@Field("email") String email,
                                      @Field("password") String password,
                                      @Field("role") String role,
                                      @Field("landowner_id") String landowner_id,
                                      @Field("opening_date") String opening_date,
                                      @Field("prefix") String prefix,
                                      @Field("landowner_name") String landowner_name,
                                      @Field("landowner_address") String landowner_address,
                                      @Field("contact_no") String contact_no,
                                      @Field("lo_email") String lo_email,
                                      @Field("profession") String profession,
                                      @Field("designation") String designation,
                                      @Field("business_address") String business_address,
                                      @Field("ref_source") String ref_source,
                                      @Field("ref_phone") String ref_phone);


    @POST("add_req_info")
    @FormUrlEncoded
    Call<String> addClientRequirement(@Field("email") String email,
                                      @Field("password") String password,
                                      @Field("role") String role,
                                      @Field("opening_date") String opening_date,
                                      @Field("ref_source") String ref_source,
                                      @Field("landowner_name") String landowner_name,
                                      @Field("landowner_address") String landowner_address,
                                      @Field("contact_no") String contact_no,
                                      @Field("lo_email") String lo_email,
                                      @Field("profession") String profession,
                                      @Field("designation") String designation,
                                      @Field("business_address") String business_address);


}