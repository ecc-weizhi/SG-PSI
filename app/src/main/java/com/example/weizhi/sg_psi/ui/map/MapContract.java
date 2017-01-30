package com.example.weizhi.sg_psi.ui.map;

import android.support.annotation.NonNull;

import com.example.weizhi.sg_psi.data.RegionInfo;

import java.util.List;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public interface MapContract {
    interface View{
        void showLoading(boolean isLoading);
        void showPsiInfo(@NonNull List<RegionInfo> regionInfoList);
        void showPollutantInfo(@NonNull List<RegionInfo> regionInfoList);
        void showNetworkError();
        void showUnknownError();
    }

    interface ActionListener{
        void onStart();
        void onMapReady();
        void onRefreshClick();
        void onPsiSelect();
        void onPollutantSelect();
    }
}
