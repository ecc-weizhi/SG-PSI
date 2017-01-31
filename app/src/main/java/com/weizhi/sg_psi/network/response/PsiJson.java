package com.weizhi.sg_psi.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PsiJson {
    @JsonProperty("api_info") public ApiInfoJson apiInfo;
    @JsonProperty("region_metadata") public List<RegionMetadataJson> regionMetadaList;
    @JsonProperty("items") public List<ItemsJson> itemsJsonList;
}
