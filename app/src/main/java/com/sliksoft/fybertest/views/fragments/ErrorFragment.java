package com.sliksoft.fybertest.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sliksoft.fybertest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorFragment extends Fragment {
    @BindView(R.id.error_text_view)
    TextView mErrorTextView;
    private static String sErrorText;

    public ErrorFragment() {
        // Required empty public constructor
    }

    public static ErrorFragment newInstance(String errorText) {
        ErrorFragment fragment = new ErrorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        sErrorText = errorText;
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
        View view = inflater.inflate(R.layout.fragment_error, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mErrorTextView.setText(sErrorText);
    }
}
