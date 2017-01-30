package com.example.weizhi.sg_psi.util;

import android.support.annotation.NonNull;

import com.example.weizhi.sg_psi.data.RegionInfo;
import com.example.weizhi.sg_psi.network.response.ItemsJson;
import com.example.weizhi.sg_psi.network.response.PsiJson;
import com.example.weizhi.sg_psi.network.response.ReadingsJson;
import com.example.weizhi.sg_psi.network.response.RegionMetadataJson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class PsiJsonParser {
    private static final String EAST = "east";
    private static final String CENTRAL = "central";
    private static final String SOUTH = "south";
    private static final String NORTH = "north";
    private static final String WEST = "west";
    private static final String NATIONAL = "national";

    public static List<RegionInfo> parse(@NonNull PsiJson psiJson){
        RegionInfo east = null;
        RegionInfo central = null;
        RegionInfo south = null;
        RegionInfo north = null;
        RegionInfo west = null;
        RegionInfo national = null;

        List<RegionMetadataJson> regions = psiJson.regionMetadaList;
        List<ItemsJson> itemList = psiJson.itemsJsonList;

        for(RegionMetadataJson region : regions){
            switch(region.name){
                case EAST:
                    east = new RegionInfo(region.name,
                            region.labelLocation.latitude,
                            region.labelLocation.longitude);
                    break;

                case CENTRAL:
                    central = new RegionInfo(region.name,
                            region.labelLocation.latitude,
                            region.labelLocation.longitude);
                    break;

                case SOUTH:
                    south = new RegionInfo(region.name,
                            region.labelLocation.latitude,
                            region.labelLocation.longitude);
                    break;

                case NORTH:
                    north = new RegionInfo(region.name,
                            region.labelLocation.latitude,
                            region.labelLocation.longitude);
                    break;

                case WEST:
                    west = new RegionInfo(region.name,
                            region.labelLocation.latitude,
                            region.labelLocation.longitude);
                    break;

                case NATIONAL:
                    national = new RegionInfo(region.name,
                            region.labelLocation.latitude,
                            region.labelLocation.longitude);
                    break;
            }
        }

        for(ItemsJson itemsJson : itemList){
            ReadingsJson readings = itemsJson.readings;

            east.setPsiTwentyFourHourly(readings.psiTwentfyFourHourly.east);
            central.setPsiTwentyFourHourly(readings.psiTwentfyFourHourly.central);
            south.setPsiTwentyFourHourly(readings.psiTwentfyFourHourly.south);
            north.setPsiTwentyFourHourly(readings.psiTwentfyFourHourly.north);
            west.setPsiTwentyFourHourly(readings.psiTwentfyFourHourly.west);
            national.setPsiTwentyFourHourly(readings.psiTwentfyFourHourly.national);

            east.setOzoneEightHourMax(readings.o3EightHourMaxJson.east);
            central.setOzoneEightHourMax(readings.o3EightHourMaxJson.central);
            south.setOzoneEightHourMax(readings.o3EightHourMaxJson.south);
            north.setOzoneEightHourMax(readings.o3EightHourMaxJson.north);
            west.setOzoneEightHourMax(readings.o3EightHourMaxJson.west);
            national.setOzoneEightHourMax(readings.o3EightHourMaxJson.national);

            east.setPm10TwentyFourHourly(readings.pm10TwentyFourHourly.east);
            central.setPm10TwentyFourHourly(readings.pm10TwentyFourHourly.central);
            south.setPm10TwentyFourHourly(readings.pm10TwentyFourHourly.south);
            north.setPm10TwentyFourHourly(readings.pm10TwentyFourHourly.north);
            west.setPm10TwentyFourHourly(readings.pm10TwentyFourHourly.west);
            national.setPm10TwentyFourHourly(readings.pm10TwentyFourHourly.national);

            east.setPm25TwentyFourHourly(readings.pm25TwentfyFourHourly.east);
            central.setPm25TwentyFourHourly(readings.pm25TwentfyFourHourly.central);
            south.setPm25TwentyFourHourly(readings.pm25TwentfyFourHourly.south);
            north.setPm25TwentyFourHourly(readings.pm25TwentfyFourHourly.north);
            west.setPm25TwentyFourHourly(readings.pm25TwentfyFourHourly.west);
            national.setPm25TwentyFourHourly(readings.pm25TwentfyFourHourly.national);

            east.setCarbonMonoxideEightHourMax(readings.coEightHourMax.east);
            central.setCarbonMonoxideEightHourMax(readings.coEightHourMax.central);
            south.setCarbonMonoxideEightHourMax(readings.coEightHourMax.south);
            north.setCarbonMonoxideEightHourMax(readings.coEightHourMax.north);
            west.setCarbonMonoxideEightHourMax(readings.coEightHourMax.west);
            national.setCarbonMonoxideEightHourMax(readings.coEightHourMax.national);

            east.setSulphurDioxideTwentyFourHourly(readings.so2TwentyFourHourly.east);
            central.setSulphurDioxideTwentyFourHourly(readings.so2TwentyFourHourly.central);
            south.setSulphurDioxideTwentyFourHourly(readings.so2TwentyFourHourly.south);
            north.setSulphurDioxideTwentyFourHourly(readings.so2TwentyFourHourly.north);
            west.setSulphurDioxideTwentyFourHourly(readings.so2TwentyFourHourly.west);
            national.setSulphurDioxideTwentyFourHourly(readings.so2TwentyFourHourly.national);

            east.setNitrogenDioxideOneHourMax(readings.no2OneHourMaxJson.east);
            central.setSulphurDioxideTwentyFourHourly(readings.no2OneHourMaxJson.central);
            south.setSulphurDioxideTwentyFourHourly(readings.no2OneHourMaxJson.south);
            north.setSulphurDioxideTwentyFourHourly(readings.no2OneHourMaxJson.north);
            west.setSulphurDioxideTwentyFourHourly(readings.no2OneHourMaxJson.west);
            national.setSulphurDioxideTwentyFourHourly(readings.no2OneHourMaxJson.national);
        }

        List<RegionInfo> result = new ArrayList<>();
        result.add(east);
        result.add(central);
        result.add(south);
        result.add(north);
        result.add(west);
        result.add(national);

        return result;
    }
}
