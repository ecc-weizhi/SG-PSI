package com.example.weizhi.sg_psi.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class So2TwentyFourHourlyJson {
    @JsonProperty("east") public int east;
    @JsonProperty("central") public int central;
    @JsonProperty("south") public int south;
    @JsonProperty("north") public int north;
    @JsonProperty("west") public int west;
    @JsonProperty("national") public int national;
}
