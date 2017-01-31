package com.weizhi.sg_psi.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionMetadataJson {
    @JsonProperty("name") public String name;
    @JsonProperty("label_location") public LabelLocationJson labelLocation;
}
