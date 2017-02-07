package com.sliksoft.fybertest.retrofit;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sliksoft.fybertest.R;
import com.sliksoft.fybertest.entities.ErrorResponse;
import com.sliksoft.fybertest.entities.MainResponse;
import com.sliksoft.fybertest.entities.ParamsMapping;
import com.sliksoft.fybertest.eventbus.Events;
import com.sliksoft.fybertest.eventbus.GlobalBus;
import com.sliksoft.fybertest.helpers.Constants;
import com.sliksoft.fybertest.helpers.GeneralHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public class MainResponseController {
    private Retrofit mRetrofit;
    private ApisInterface mApisInterface;
    private String mFormat;
    private Integer mAppid;
    private String mUid;
    private String mLocale;
    private String mOsVersion;
    private Long mTimestamp;
    private String mHashKey;
    private String mGoogleAdId;
    private Boolean mGoogleAdIdLimitedTrackingEnabled;
    private String mIp;
    private String mPub0;
    private Integer mPage;
    private String mOfferTypes;
    private Long mPsTime;
    private String mDevice;
    private ArrayList<ParamsMapping> mParams;
    private Context mContext;

    /**
     * Constructor
     */
    public MainResponseController(Context context, String format, Integer appId, String uid, String locale, String osVersion, Long timestamp, String googleAdId, Boolean googleAdIdLimitedTrackingEnabled, String ip, String pub0, Integer page, String offerTypes, Long psTime, String device) {
        mRetrofit = InitializeRetrofit.getRetrofitInstance();
        mApisInterface = mRetrofit.create(ApisInterface.class);
        this.mFormat = format;
        this.mAppid = appId;
        this.mUid = uid;
        this.mLocale = locale;
        this.mOsVersion = osVersion;
        this.mTimestamp = timestamp;
        this.mGoogleAdId = googleAdId;
        this.mGoogleAdIdLimitedTrackingEnabled = googleAdIdLimitedTrackingEnabled;
        this.mIp = ip;
        this.mPub0 = pub0;
        this.mPage = page;
        this.mOfferTypes = offerTypes;
        this.mPsTime = psTime;
        this.mDevice = device;
        this.mHashKey = "";
        this.mParams = new ArrayList<>();
        this.mContext = context;
        createHashString();
        performMainResponseCalling();
    }

    /**
     * Create the hash for your request to send to api
     */
    private void createHashString() {
        mParams.add(new ParamsMapping(mContext.getString(R.string.format), mFormat));
        mParams.add(new ParamsMapping(mContext.getString(R.string.appid), mAppid));
        mParams.add(new ParamsMapping(mContext.getString(R.string.uid), mUid));
        mParams.add(new ParamsMapping(mContext.getString(R.string.locale), mLocale));
        mParams.add(new ParamsMapping(mContext.getString(R.string.os_version), mOsVersion));
        mParams.add(new ParamsMapping(mContext.getString(R.string.timestamp), mTimestamp));
        mParams.add(new ParamsMapping(mContext.getString(R.string.google_ad_id), mGoogleAdId));
        mParams.add(new ParamsMapping(mContext.getString(R.string.google_ad_id_limited_tracking_enabled), mGoogleAdIdLimitedTrackingEnabled));
        if (mIp != null)
            mParams.add(new ParamsMapping(mContext.getString(R.string.ip), mIp));
        if (mPub0 != null)
            mParams.add(new ParamsMapping(mContext.getString(R.string.pub0), mPub0));
        if (mPage != null)
            mParams.add(new ParamsMapping(mContext.getString(R.string.page), mPage));
        if (mOfferTypes != null)
            mParams.add(new ParamsMapping(mContext.getString(R.string.offer_types), mOfferTypes));
        if (mPsTime != null)
            mParams.add(new ParamsMapping(mContext.getString(R.string.ps_time), mPsTime));
        if (mDevice != null)
            mParams.add(new ParamsMapping(mContext.getString(R.string.device), mDevice));
        Collections.sort(mParams, new Comparator<ParamsMapping>() {
            public int compare(ParamsMapping paramsMaping1, ParamsMapping paramsMaping2) {
                return paramsMaping1.getParamName().compareTo(paramsMaping2.getParamName());
            }
        });
        for (ParamsMapping paramsMapping: mParams) {
            mHashKey += paramsMapping.getParamName() + mContext.getString(R.string.equal) + paramsMapping.getParamValue() + mContext.getString(R.string.and);
        }
        mHashKey += Constants.TOKEN;
        mHashKey = GeneralHelper.calcSha1(mHashKey);
    }

    /**
     * Perform the api calling
     */
    private void performMainResponseCalling() {
        Call<ResponseBody> call;
        call = mApisInterface.getListOfOffers(mFormat, mAppid, mUid, mLocale, mOsVersion, mTimestamp, mHashKey, mGoogleAdId, mGoogleAdIdLimitedTrackingEnabled, mIp, mPub0, mPage, mOfferTypes, mPsTime, mDevice);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ObjectMapper mapper = new ObjectMapper();
                if (response.code() == 200) {
                    try {
                        MainResponse mainResponse = mapper.readValue(response.body().string(), MainResponse.class);
                        if (isSignedCorrectly(mainResponse, response.headers().get(Constants.SIGNATURE_TEXT))) {
                            Log.e("valid", mainResponse.getMessage());
                            if (mainResponse.getOffers() == null || mainResponse.getOffers().size() == 0)
                                GlobalBus.getBus().post(new Events.OnGetOfferFailed(response.code(), response.message()));
                            else
                                GlobalBus.getBus().post(new Events.OnGetOfferSuccess(mainResponse));
                        } else {
                            Log.e("notValid", mainResponse.getMessage());
                            GlobalBus.getBus().post(new Events.OnGetOfferFailed(response.code(), mContext.getString(R.string.not_valid)));
                        }
                    } catch (IOException e) {
                        GlobalBus.getBus().post(new Events.OnGetOfferFailed(response.code(), response.message()));
                    }
                } else {
                    try {
                        ErrorResponse errorResponse = mapper.readValue(response.errorBody().string(), ErrorResponse.class);
                        Log.e("notValid", response.message() + " " + response.code());
                        GlobalBus.getBus().post(new Events.OnGetOfferFailed(response.code(), errorResponse.getCode() + "\n" + errorResponse.getMessage()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("error", call.toString());
                GlobalBus.getBus().post(new Events.OnGetOfferFailed(t.hashCode(), t.getMessage()));
            }
        });
    }

    /**
     * Check is the response is signed correctly
     */
    private boolean isSignedCorrectly(MainResponse mainResponse, String signature) {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(mainResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String encryptedResponse = result + Constants.TOKEN;
        encryptedResponse = GeneralHelper.calcSha1(encryptedResponse);
        return encryptedResponse.equals(signature);
    }
}
