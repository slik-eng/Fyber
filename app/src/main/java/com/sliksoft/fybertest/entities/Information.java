package com.sliksoft.fybertest.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "app_name",
        "appid",
        "virtual_currency",
        "virtual_currency_sale_enabled",
        "country",
        "language",
        "support_url"
})
public class Information {

    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("appid")
    private Integer appid;
    @JsonProperty("virtual_currency")
    private String virtualCurrency;
    @JsonProperty("virtual_currency_sale_enabled")
    private Boolean virtualCurrencySaleEnabled;
    @JsonProperty("country")
    private String country;
    @JsonProperty("language")
    private String language;
    @JsonProperty("support_url")
    private String supportUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("app_name")
    public String getAppName() {
        return appName;
    }

    @JsonProperty("app_name")
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @JsonProperty("appid")
    public Integer getAppid() {
        return appid;
    }

    @JsonProperty("appid")
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    @JsonProperty("virtual_currency")
    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    @JsonProperty("virtual_currency")
    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    @JsonProperty("virtual_currency_sale_enabled")
    public Boolean getVirtualCurrencySaleEnabled() {
        return virtualCurrencySaleEnabled;
    }

    @JsonProperty("virtual_currency_sale_enabled")
    public void setVirtualCurrencySaleEnabled(Boolean virtualCurrencySaleEnabled) {
        this.virtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("support_url")
    public String getSupportUrl() {
        return supportUrl;
    }

    @JsonProperty("support_url")
    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

