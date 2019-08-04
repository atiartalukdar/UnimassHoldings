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
import bp.ObjectBox;
import info.atiar.unimassholdings.R;
import info.atiar.unimassholdings.clients.ClientDetails;
import info.atiar.unimassholdings.dataModel.CommunicationDM;
import io.objectbox.Box;
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

    CommunicationAdapter adapter;

    Box<SpecificCommRecordBox> commBox;
    List<SpecificCommRecordBox> commList = new ArrayList<>();

    public CommunicationDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commBox = ObjectBox.get().boxFor(SpecificCommRecordBox.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_info, container, false);
        final ListView _commListView = v.findViewById(R.id.communicationList);
        final SwipeRefreshLayout _pullToRefresh = v.findViewById(R.id.pullToRefresh);

        try{
            commList = commBox.query().equal(SpecificCommRecordBox_.generalInfosId,((ClientDetails) getActivity()).getMember().getClientID()+"").build().find();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            adapter = new CommunicationAdapter(getActivity(), commList);
            _commListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


        _pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try{
                    commList = commBox.query().equal(SpecificCommRecordBox_.generalInfosId,((ClientDetails) getActivity()).getMember().getClientID()+"").build().find();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    adapter = new CommunicationAdapter(getActivity(), commList);
                    _commListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                _pullToRefresh.setRefreshing(false);
            }
        });
        return v;



    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
