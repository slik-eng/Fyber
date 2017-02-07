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
        "offer_type_id",
        "readable"
})
public class OfferType {

    @JsonProperty("offer_type_id")
    private Integer offerTypeId;
    @JsonProperty("readable")
    private String readable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("offer_type_id")
    public Integer getOfferTypeId() {
        return offerTypeId;
    }

    @JsonProperty("offer_type_id")
    public void setOfferTypeId(Integer offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    @JsonProperty("readable")
    public String getReadable() {
        return readable;
    }

    @JsonProperty("readable")
    public void setReadable(String readable) {
        this.readable = readable;
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