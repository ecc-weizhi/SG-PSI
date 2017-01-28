package com.example.weizhi.sg_psi.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsJson {
    @JsonProperty("update_timestamp") public String updateTimeStamp;
    @JsonProperty("timestamp") public String timeStamp;
    @JsonProperty("readings") public ReadingsJson readings;
}
