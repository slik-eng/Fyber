package com.sliksoft.fybertest.entities;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public class ParamsMapping {
    private String mParamName;
    private Object mParamValue;

    public ParamsMapping(String mParamName, Object mParamValue) {
        this.mParamName = mParamName;
        this.mParamValue = mParamValue;
    }

    public String getParamValue() {
        return mParamValue.toString();
    }

    public String getParamName() {
        return mParamName;
    }



}
