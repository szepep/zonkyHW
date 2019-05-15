
package com.szepep.zonky.hw.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "policyPeriodFrom",
        "policyPeriodTo"
})
public class InsuranceHistory {

    @JsonProperty("policyPeriodFrom")
    private String policyPeriodFrom;
    @JsonProperty("policyPeriodTo")
    private String policyPeriodTo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("policyPeriodFrom")
    public String getPolicyPeriodFrom() {
        return policyPeriodFrom;
    }

    @JsonProperty("policyPeriodFrom")
    public void setPolicyPeriodFrom(String policyPeriodFrom) {
        this.policyPeriodFrom = policyPeriodFrom;
    }

    @JsonProperty("policyPeriodTo")
    public String getPolicyPeriodTo() {
        return policyPeriodTo;
    }

    @JsonProperty("policyPeriodTo")
    public void setPolicyPeriodTo(String policyPeriodTo) {
        this.policyPeriodTo = policyPeriodTo;
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
