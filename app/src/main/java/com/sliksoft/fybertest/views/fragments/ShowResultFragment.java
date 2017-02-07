package com.sliksoft.fybertest.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sliksoft.fybertest.R;
import com.sliksoft.fybertest.adapters.OfferAdapter;
import com.sliksoft.fybertest.entities.MainResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowResultFragment extends Fragment {
    @BindView(R.id.results_recycle_view)
    RecyclerView mResultsRecycleView;
    private static MainResponse sMainResponse;
    private OfferAdapter mOfferAdapter;

    public ShowResultFragment() {
        // Required empty public constructor
    }

    public static ShowResultFragment newInstance(MainResponse mainResponse) {
        ShowResultFragment fragment = new ShowResultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        sMainResponse = mainResponse;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (sMainResponse != null || sMainResponse.getOffers() != null || sMainResponse.getOffers().size() == 0)
            setUpRecycleViewContent();
    }

    /**
     * Setup recycle view content
     */
    private void setUpRecycleViewContent() {
        mResultsRecycleView.setHasFixedSize(true);
        mResultsRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOfferAdapter = new OfferAdapter(getActivity(), sMainResponse.getOffers());
        mResultsRecycleView.setAdapter(mOfferAdapter);
    }

}
