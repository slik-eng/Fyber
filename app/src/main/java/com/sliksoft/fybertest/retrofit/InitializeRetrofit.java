package com.sliksoft.fybertest.retrofit;

import com.sliksoft.fybertest.helpers.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public class InitializeRetrofit {
    private static Retrofit sRetrofit;

    /**
     * Initialization For Retrofit
     */
    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.MAIN_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
