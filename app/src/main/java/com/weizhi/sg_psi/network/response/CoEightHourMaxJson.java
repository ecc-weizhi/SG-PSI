package com.weizhi.sg_psi.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoEightHourMaxJson {
    @JsonProperty("east") public double east;
    @JsonProperty("central") public double central;
    @JsonProperty("south") public double south;
    @JsonProperty("north") public double north;
    @JsonProperty("west") public double west;
    @JsonProperty("national") public double national;
}
