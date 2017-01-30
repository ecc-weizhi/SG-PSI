package com.example.weizhi.sg_psi.busevent;

import android.support.annotation.Nullable;

import com.example.weizhi.sg_psi.data.RegionInfo;
import com.example.weizhi.sg_psi.network.response.PsiJson;

import java.util.List;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class GetPsiEvent {
    public static final int SUCCESS = 1;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_UNKNOWN = 3;

    public final int status;
    public final List<RegionInfo> regionInfoList;

    public GetPsiEvent(int status, @Nullable List<RegionInfo> regionInfoList){
        if(status == SUCCESS && regionInfoList == null){
            throw new NullPointerException("Success event with null regionInfoList");
        }

        this.status = status;
        this.regionInfoList = regionInfoList;
    }
}
