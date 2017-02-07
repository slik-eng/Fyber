package com.sliksoft.fybertest.views.fragments;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sliksoft.fybertest.R;
import com.sliksoft.fybertest.eventbus.Events;
import com.sliksoft.fybertest.eventbus.GlobalBus;
import com.sliksoft.fybertest.helpers.GeneralHelper;
import com.sliksoft.fybertest.retrofit.MainResponseController;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment {
    @BindView(R.id.appid_edit_text)
    EditText mAppIdEditText;
    @BindView(R.id.uid_edit_text)
    EditText mUserIdEditText;
    @BindView(R.id.locale_spinner)
    Spinner mLocaleSpinner;
    @BindView(R.id.os_version_text_view)
    TextView mOsVersionTextView;
    @BindView(R.id.timestamp_text_view)
    TextView mTimestampTextView;
    @BindView(R.id.google_ad_id_text_view)
    TextView mGoogleAdIdTextView;
    @BindView(R.id.google_ad_id_limited_tracking_enabled_check_box)
    CheckBox mGoogleAdIdLimitedTrackingEnabledCheckBox;
    @BindView(R.id.ip_edit_text)
    EditText mIpEditText;
    @BindView(R.id.ip_check_box)
    CheckBox mIpCheckBox;
    @BindView(R.id.pub0_edit_text)
    EditText mPub0EditText;
    @BindView(R.id.pub0_check_box)
    CheckBox mPub0CheckBox;
    @BindView(R.id.page_edit_text)
    EditText mPageEditText;
    @BindView(R.id.page_check_box)
    CheckBox mPageCheckBox;
    @BindView(R.id.offer_types_edit_text)
    EditText mOfferTypesEditText;
    @BindView(R.id.offer_types_check_box)
    CheckBox mOfferTypesCheckBox;
    @BindView(R.id.ps_time_text_view)
    TextView mPsTimeTextView;
    @BindView(R.id.ps_time_check_box)
    CheckBox mPsTimeCheckBox;
    @BindView(R.id.device_text_view)
    TextView mDeviceTextView;
    @BindView(R.id.device_check_box)
    CheckBox mDeviceCheckBox;
    @BindView(R.id.request_button)
    Button mRequestButton;
    private static String sGoogleAdId;
    private static boolean sLimitAdTrackingEnabled = false;
    private ProgressDialog mProgressDialog;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String googleAdId, boolean limitAdTrackingEnabled) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        sGoogleAdId = googleAdId;
        sLimitAdTrackingEnabled = limitAdTrackingEnabled;
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    /**
     * Set the default values for the ui
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOsVersionTextView.setText(GeneralHelper.getOsVersion());
        mTimestampTextView.setText(String.valueOf(GeneralHelper.getTimestamp()));
        setUpLocaleDropDown();
        mGoogleAdIdTextView.setText(sGoogleAdId);
        mGoogleAdIdLimitedTrackingEnabledCheckBox.setChecked(sLimitAdTrackingEnabled);
        mDeviceTextView.setText(GeneralHelper.isTablet(getContext()) ? "tablet" : "phone");
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        super.onStop();
    }

    @OnClick(R.id.request_button)
    void onRequestClicked() {
        collectInputs();
    }

    /**
     * Prepare all inputs and send to api
     */
    private void collectInputs() {
        String format = "json";
        Integer appId = Integer.parseInt(mAppIdEditText.getText().toString());
        String uid = mUserIdEditText.getText().toString();
        String locale = mLocaleSpinner.getSelectedItem().toString();
        String osVersion = GeneralHelper.getOsVersion();
        Long timestamp = GeneralHelper.getTimestamp();
        String googleAdId = sGoogleAdId;
        Boolean googleAdIdLimitedTrackingEnabled = mGoogleAdIdLimitedTrackingEnabledCheckBox.isChecked() ? true : false;
        String ip = mIpCheckBox.isChecked() ? mIpEditText.getText().toString() : null;
        String pub0 = mPub0CheckBox.isChecked() ? mPub0EditText.getText().toString() : null;
        Integer page = mPageCheckBox.isChecked() ? Integer.parseInt(mPageEditText.getText().toString()) : null;
        String offerTypes = mOfferTypesCheckBox.isChecked() ? mOfferTypesEditText.getText().toString() : null;
        Long psTime = mPsTimeCheckBox.isChecked() ? Long.parseLong(mPsTimeTextView.getText().toString()) : null;
        String device = mDeviceCheckBox.isChecked() ? mDeviceTextView.getText().toString() : null;
        mProgressDialog = GeneralHelper.setProgressDialog(getActivity(), false, getString(R.string.processing));
        new MainResponseController(getActivity(), format, appId, uid, locale, osVersion, timestamp, googleAdId, googleAdIdLimitedTrackingEnabled, ip, pub0, page, offerTypes, psTime, device);
    }

    /**
     * Initialization for the locale dropdown list
     */
    private void setUpLocaleDropDown() {
        ArrayAdapter<String> localeAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, GeneralHelper.getListOfLocale());
        mLocaleSpinner.setAdapter(localeAdapter);

        Locale currentLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            currentLocale = getActivity().getResources().getConfiguration().getLocales().get(0);
        } else {
            currentLocale = getActivity().getResources().getConfiguration().locale;
        }
        String selectedLanguage = currentLocale.getLanguage();

        if (!selectedLanguage.isEmpty()) {
            mLocaleSpinner.setSelection(localeAdapter.getPosition(selectedLanguage));
        }
    }

    /**
     * Get the value on success and pass it to ShowResultFragment
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGetOfferSuccess(Events.OnGetOfferSuccess onGetOfferSuccess) {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame_layout, ShowResultFragment.newInstance(onGetOfferSuccess.getMainResponse())).addToBackStack("ShowResultFragment").commit();
    }

    /**
     * Get the value on error and pass it to ErrorFragment
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGetOfferFailed(Events.OnGetOfferFailed onGetOfferFailed) {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame_layout, ErrorFragment.newInstance(onGetOfferFailed.toString())).addToBackStack("ErrorFragment").commit();
    }

}
