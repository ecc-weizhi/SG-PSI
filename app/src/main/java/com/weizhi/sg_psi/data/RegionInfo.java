package com.weizhi.sg_psi.data;

import android.support.annotation.NonNull;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class RegionInfo {
    public final String name;
    public final double lat;
    public final double lng;

    // psi
    private int psiTwentyFourHourly;

    // pollutant
    private int ozoneEightHourMax;
    private int pm10TwentyFourHourly;
    private int pm25TwentyFourHourly;
    private double carbonMonoxideEightHourMax;
    private int sulphurDioxideTwentyFourHourly;
    private int nitrogenDioxideOneHourMax;

    public RegionInfo(@NonNull String name, double lat, double lng){
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public int getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    public void setPsiTwentyFourHourly(int psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    public int getOzoneEightHourMax() {
        return ozoneEightHourMax;
    }

    public void setOzoneEightHourMax(int ozoneEightHourMax) {
        this.ozoneEightHourMax = ozoneEightHourMax;
    }

    public int getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    public void setPm10TwentyFourHourly(int pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
    }

    public int getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    public void setPm25TwentyFourHourly(int pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
    }

    public double getCarbonMonoxideEightHourMax() {
        return carbonMonoxideEightHourMax;
    }

    public void setCarbonMonoxideEightHourMax(double carbonMonoxideEightHourMax) {
        this.carbonMonoxideEightHourMax = carbonMonoxideEightHourMax;
    }

    public int getSulphurDioxideTwentyFourHourly() {
        return sulphurDioxideTwentyFourHourly;
    }

    public void setSulphurDioxideTwentyFourHourly(int sulphurDioxideTwentyFourHourly) {
        this.sulphurDioxideTwentyFourHourly = sulphurDioxideTwentyFourHourly;
    }

    public int getNitrogenDioxideOneHourMax() {
        return nitrogenDioxideOneHourMax;
    }

    public void setNitrogenDioxideOneHourMax(int nitrogenDioxideOneHourMax) {
        this.nitrogenDioxideOneHourMax = nitrogenDioxideOneHourMax;
    }
}
