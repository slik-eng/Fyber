package com.sliksoft.fybertest.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "offer_id",
        "teaser",
        "required_actions",
        "link",
        "offer_types",
        "payout",
        "time_to_payout",
        "thumbnail",
        "store_id"
})
public class Offer {

    @JsonProperty("title")
    private String title;
    @JsonProperty("offer_id")
    private Integer offerId;
    @JsonProperty("teaser")
    private String teaser;
    @JsonProperty("required_actions")
    private String requiredActions;
    @JsonProperty("link")
    private String link;
    @JsonProperty("offer_types")
    private List<OfferType> offerTypes = null;
    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;
    @JsonProperty("payout")
    private Integer payout;
    @JsonProperty("time_to_payout")
    private TimeToPayout timeToPayout;
    @JsonProperty("store_id")
    private String storeId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("offer_id")
    public Integer getOfferId() {
        return offerId;
    }

    @JsonProperty("offer_id")
    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    @JsonProperty("teaser")
    public String getTeaser() {
        return teaser;
    }

    @JsonProperty("teaser")
    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    @JsonProperty("required_actions")
    public String getRequiredActions() {
        return requiredActions;
    }

    @JsonProperty("required_actions")
    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("offer_types")
    public List<OfferType> getOfferTypes() {
        return offerTypes;
    }

    @JsonProperty("offer_types")
    public void setOfferTypes(List<OfferType> offerTypes) {
        this.offerTypes = offerTypes;
    }

    @JsonProperty("thumbnail")
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("payout")
    public Integer getPayout() {
        return payout;
    }

    @JsonProperty("payout")
    public void setPayout(Integer payout) {
        this.payout = payout;
    }

    @JsonProperty("time_to_payout")
    public TimeToPayout getTimeToPayout() {
        return timeToPayout;
    }

    @JsonProperty("time_to_payout")
    public void setTimeToPayout(TimeToPayout timeToPayout) {
        this.timeToPayout = timeToPayout;
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



