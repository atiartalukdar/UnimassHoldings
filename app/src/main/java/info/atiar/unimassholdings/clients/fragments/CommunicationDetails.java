package info.atiar.unimassholdings.clients.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapters.CommunicationAdapter;
import bp.TimeUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import info.atiar.unimassholdings.dataModel.MessageDM;
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

public class CommunicationDetails extends Fragment {
    private final String TAG = getClass().getName() + " Atiar - ";

    private static CommunicationAdapter adapter;
    List<CommunicationDM.SpecificCommRecord> commList = new ArrayList<>();


    Retrofit retrofit;
    APIInterface apiInterface;

    @BindView(R.id.communicationList)
    ListView _communicationList;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout _pullToRefresh;
    Unbinder unbinder;
    @BindView(R.id.fab)
    FloatingActionButton addNewCommunication;

    public CommunicationDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);

        loadCommunicationDataFromServer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_communication_details, container, false);
        unbinder = ButterKnife.bind(this, v);

        adapter = new CommunicationAdapter(getActivity(), commList);
        _communicationList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        _pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCommunicationDataFromServer();
                adapter.notifyDataSetChanged();
                _pullToRefresh.setRefreshing(false);
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    private void loadCommunicationDataFromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Communication Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getClientCommunicationRecords(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), ((ClientDetails) getActivity()).getMemberProfile().getGeneralInfo().getId() + "");

        call.enqueue(new Callback<CommunicationDM>() {
            @Override
            public void onResponse(Call<CommunicationDM> call, Response<CommunicationDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    try {
                        commList.clear();
                        commList.addAll(response.body().getSpecificCommRecord());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        addNewCommunicationData();
    }

    private void addNewCommunicationRecord(String clientName, String interactionType, String interaction_date,String details,String special_note, String action_type,String by_whom,String date,String location ) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating Communication Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.addClientCommunicationRecords(
                Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), ((ClientDetails) getActivity()).getMemberProfile().getGeneralInfo().getId() + "",
                clientName, interactionType, interaction_date, details,special_note, action_type, by_whom,date,location);

        call.enqueue(new Callback<MessageDM>() {
            @Override
            public void onResponse(Call<MessageDM> call, Response<MessageDM> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.e(TAG, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                Log.e(TAG, call.toString());
                Log.e(TAG, t.getMessage());
                progressDialog.dismiss();
            }

        });

    }

    //====================== below code are responsible for add new communication popup =====================//
    private View popupInputDialogView = null;
    private Spinner _interactionTypeSpinner = null;
    private EditText _details = null;
    private EditText _specialNote = null;
    private Spinner _nextActionTypeSpinner = null;
    private TextView _nextActionDate = null;
    private EditText _nextMeetingLocation = null;

    private Button _saveFamilyMember = null;
    private Button _cancelInput = null;

    ArrayList<String> actionTypeArray = new ArrayList<>();
    ArrayAdapter<String> actionTypeAdapter;

    /* Initialize popup dialog view and ui controls in the popup dialog. */
    private void initPopupViewControls(){
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.custom_popup_add_new_communication_record, null);

        // Get user input edittext and button ui controls in the popup dialog.
        _interactionTypeSpinner     =  popupInputDialogView.findViewById(R.id.inteructionTypeSpinner);
        _details                    =  popupInputDialogView.findViewById(R.id.details);
        _specialNote                =  popupInputDialogView.findViewById(R.id.specialNote);
        _nextActionTypeSpinner      =  popupInputDialogView.findViewById(R.id.nextActionTypeSpinner);
        _nextActionDate             =  popupInputDialogView.findViewById(R.id.nextActionDate);
        _nextMeetingLocation        =  popupInputDialogView.findViewById(R.id.nextMeetingLocation);

        _saveFamilyMember = popupInputDialogView.findViewById(R.id.button_save_user_data);
        _cancelInput = popupInputDialogView.findViewById(R.id.button_cancel_user_data);


    }

    private void initializeSpinner(){
        actionTypeArray.clear();
        actionTypeArray.add("Phone");
        actionTypeArray.add("Meeting");
        actionTypeArray.add("Visiting");

        actionTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, actionTypeArray);
        _interactionTypeSpinner.setAdapter(actionTypeAdapter);
        _nextActionTypeSpinner.setAdapter(actionTypeAdapter);

    }

    private void addNewCommunicationData(){
        // Create a AlertDialog Builder.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        // Set title, icon, can not cancel properties.
        alertDialogBuilder.setTitle("Add new Record");
        alertDialogBuilder.setIcon(R.drawable.logo);
        alertDialogBuilder.setCancelable(false);

        // Init popup dialog view and it's ui controls.
        initPopupViewControls();

        //loading spinner with data
        initializeSpinner();
        // Set the inflated layout view object to the AlertDialog builder.
        alertDialogBuilder.setView(popupInputDialogView);

        dateOfBirthPicker();

        // Create AlertDialog and show.
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // When user click the save user data button in the popup dialog.
        _saveFamilyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get user data from popup dialog editeext.
                String interactionType = _interactionTypeSpinner.getSelectedItem() + "";
                String details = _details.getText().toString();
                String specialNote = _specialNote.getText().toString();
                String nextActionType = _nextActionTypeSpinner.getSelectedItem() + "";
                String nextActionDate = _nextActionDate.getText().toString();
                String nextMeetingLocation = _nextMeetingLocation.getText().toString();

                CommunicationDM.SpecificCommRecord specificCommRecord = new CommunicationDM.SpecificCommRecord();
                specificCommRecord.setInteractionType(interactionType);
                specificCommRecord.setInteractionDate(TimeUtils.getToday());
                specificCommRecord.setAgentId(Session.getSeassionData().getId() + "");
                specificCommRecord.setDetails(details);
                specificCommRecord.setSpecialNote(specialNote);
                specificCommRecord.setActionType(nextActionType);
                specificCommRecord.setDate(nextActionDate);
                specificCommRecord.setLocation(nextMeetingLocation);
                specificCommRecord.setLName(Session.getSeassionData().getName());

                //TODO: Need to write code for add data to server
                commList.add(specificCommRecord);


                addNewCommunicationRecord(((ClientDetails) getActivity()).getMemberProfile().getGeneralInfo().getLandownerName(),interactionType,
                        TimeUtils.getToday(),details,specialNote,nextActionType,Session.getSeassionData().getName(),nextActionDate,nextMeetingLocation);

                adapter.notifyDataSetChanged();
                alertDialog.cancel();
            }
        });

        _cancelInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }

    private void dateOfBirthPicker() {

        //Date Of birth Picker
        _nextActionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        long dtDob = chosenDate.toMillis(true);

                        String format = new SimpleDateFormat("yyyy-MM-dd").format(dtDob);
                        _nextActionDate.setText(format);

                    }
                }, mYear, mMonth, mDay);
                //dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });
    }
}
