package info.atiar.unimassholdings.clients.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.ClientListAdapter;
import adapters.CommunicationAdapter;
import bp.ObjectBox;
import butterknife.BindView;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.clients.ClientsList;
import info.atiar.unimassholdings.dataModel.ClientBox;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import io.objectbox.Box;
import objectBox.InitialClientInfoBox;
import objectBox.SpecificCommRecordBox;
import objectBox.SpecificCommRecordBox_;
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

    Retrofit retrofit;
    APIInterface apiInterface;
    CommunicationAdapter adapter;

    Box<SpecificCommRecordBox> commBox;
    List<SpecificCommRecordBox> commList = new ArrayList<>();

    public CommunicationDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = RetrofitClientInstance.getRetrofitInstance();
        apiInterface = retrofit.create(APIInterface.class);
        commBox = ObjectBox.get().boxFor(SpecificCommRecordBox.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_info, container, false);
        ListView _commListView = v.findViewById(R.id.communicationList);

        try{
            commList = commBox.query().equal(SpecificCommRecordBox_.generalInfosId,((ClientDetails) getActivity()).getMember().getClientID()+"").build().find();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            adapter = new CommunicationAdapter(getActivity(), commList);
            _commListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }



        loadListDataFromServer();

        return v;



    }

    private void loadListDataFromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Communication Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call call = apiInterface.getClientCommunicationRecords(Session.getSeassionData().getEmail(), Session.getPassword(), Session.getUserRole(), ((ClientDetails) getActivity()).getMember().getClientID()+"");

        call.enqueue(new Callback<CommunicationDM>() {
            @Override
            public void onResponse(Call<CommunicationDM> call, Response<CommunicationDM> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    commList.clear();

                    List<CommunicationDM.SpecificCommRecord> clientLists = response.body().getSpecificCommRecord();
                    for (CommunicationDM.SpecificCommRecord data : clientLists) {
                        SpecificCommRecordBox i = new SpecificCommRecordBox(data);
                        if (!commBox.getAll().contains(i)){
                            commBox.put(i);
                        }
                        commList.add(i);
                    }
                    adapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                adapter.notifyDataSetChanged();
            }

        });


    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
