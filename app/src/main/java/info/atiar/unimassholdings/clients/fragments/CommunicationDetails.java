package info.atiar.unimassholdings.clients.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.CommunicationAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
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
}
