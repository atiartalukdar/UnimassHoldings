package info.atiar.unimassholdings.clients.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.atiar.unimassholdings.R;

/**
 * Created by Atiar Talukdar on 7/11/2019.
 */

public class OtherInformations extends Fragment {

    public OtherInformations() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_communication_details, container, false);
    }
}
