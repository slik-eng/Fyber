package com.sliksoft.fybertest.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public class GeneralHelper {

    /**
     * Set the locale list
     */
    public static List<String> getListOfLocale() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        Set<String> localeLanguages = new HashSet<>();
        for (Locale locale : availableLocales) {
            localeLanguages.add(locale.getLanguage());
        }
        List<String> languages = new ArrayList<>();
        languages.addAll(localeLanguages);
        Collections.sort(languages);
        return languages;
    }

    /**
     * Set the current version
     */
    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Set the current timestamp
     */
    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * Check is the current device is tablet or not
     */
    public static boolean isTablet(Context mContext) {
        return (mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * Calculate the sha1 for the passed string
     */
    public static String calcSha1(String hashKey) {
        return new String(Hex.encodeHex(DigestUtils.sha1(hashKey)));
    }

    /**
     * Create custom dialog with your message
     */
    public static ProgressDialog setProgressDialog(Context context, boolean isCancelable, String msg) {
        try {
            ProgressDialog mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(msg);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(isCancelable);
            mProgressDialog.show();
            return mProgressDialog;
        } catch (Exception e) {
            return null;
        }
    }
}
