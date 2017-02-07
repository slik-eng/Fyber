package com.sliksoft.fybertest.eventbus;

import com.sliksoft.fybertest.entities.MainResponse;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public abstract class Events {

    public static class GoogleAdvertising {
        private String googleAdvertisingId;

        public String getGoogleAdvertisingId() {
            return googleAdvertisingId;
        }

        public void setGoogleAdvertisingId(String googleAdvertisingId) {
            this.googleAdvertisingId = googleAdvertisingId;
        }
    }

    public static class OnGetOfferSuccess {
        private MainResponse mainResponse;

        public MainResponse getMainResponse() {
            return mainResponse;
        }

        public OnGetOfferSuccess(MainResponse mainResponse) {
            this.mainResponse = mainResponse;
        }
    }

    public static class OnGetOfferFailed {
        private int errorCode;
        private String errorMessage;

        public OnGetOfferFailed(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String toString() {
            return this.errorCode + "\n" + this.errorMessage;
        }
    }
}
