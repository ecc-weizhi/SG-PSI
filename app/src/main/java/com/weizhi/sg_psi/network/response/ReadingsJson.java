package com.weizhi.sg_psi.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadingsJson {
    @JsonProperty("o3_sub_index") public O3SubIndexJson o3SubIndex;
    @JsonProperty("o3_eight_hour_max") public O3EightHourMaxJson o3EightHourMaxJson;
    @JsonProperty("pm10_sub_index") public Pm10SubIndexJson pm10SubIndex;
    @JsonProperty("pm10_twenty_four_hourly") public Pm10TwentyFourHourlyJson pm10TwentyFourHourly;
    @JsonProperty("pm25_sub_index") public Pm25SubIndexJson pm25SubIndex;
    @JsonProperty("pm25_twenty_four_hourly") public Pm25TwentfyFourHourlyJson pm25TwentfyFourHourly;
    @JsonProperty("co_sub_index") public CoSubIndexJson coSubIndex;
    @JsonProperty("co_eight_hour_max") public CoEightHourMaxJson coEightHourMax;
    @JsonProperty("so2_sub_index") public So2SubIndexJson so2SubIndex;
    @JsonProperty("so2_twenty_four_hourly") public So2TwentyFourHourlyJson so2TwentyFourHourly;
    @JsonProperty("no2_one_hour_max") public No2OneHourMaxJson no2OneHourMaxJson;
    @JsonProperty("psi_three_hourly") public PsiThreeHourlyJson psiThreeHourly;
    @JsonProperty("psi_twenty_four_hourly") public PsiTwentfyFourHourlyJson psiTwentfyFourHourly;
}
