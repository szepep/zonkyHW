
package com.szepep.zonky.hw.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Simplified DTO for Loans containing only the properties used by current codebase.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Loan {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("datePublished")
    private String datePublished;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("datePublished")
    public String getDatePublished() {
        return datePublished;
    }

    @JsonProperty("datePublished")
    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
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
