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
        "lowres",
        "hires"
})
public class Thumbnail {

    @JsonProperty("lowres")
    private String lowres;
    @JsonProperty("hires")
    private String hires;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lowres")
    public String getLowres() {
        return lowres;
    }

    @JsonProperty("lowres")
    public void setLowres(String lowres) {
        this.lowres = lowres;
    }

    @JsonProperty("hires")
    public String getHires() {
        return hires;
    }

    @JsonProperty("hires")
    public void setHires(String hires) {
        this.hires = hires;
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
