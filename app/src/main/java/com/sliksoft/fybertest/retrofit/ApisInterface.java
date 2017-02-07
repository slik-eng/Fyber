package com.sliksoft.fybertest.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public interface ApisInterface {
    @GET("/feed/v1/offers.json")
    Call<ResponseBody> getListOfOffers(@Query("format") String format,
                                       @Query("appid") Integer appid,
                                       @Query("uid") String uid,
                                       @Query("locale") String locale,
                                       @Query("os_version") String os_version,
                                       @Query("timestamp") Long timestamp,
                                       @Query("hashkey") String hashkey,
                                       @Query("google_ad_id") String google_ad_id,
                                       @Query("google_ad_id_limited_tracking_enabled") Boolean google_ad_id_limited_tracking_enabled,
                                       @Query("ip") String ip,
                                       @Query("pub0") String pub0,
                                       @Query("page") Integer page,
                                       @Query("offer_types") String offer_types,
                                       @Query("ps_time") Long ps_time,
                                       @Query("device") String device);
}
