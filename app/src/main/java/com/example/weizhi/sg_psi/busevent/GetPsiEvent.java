package com.example.weizhi.sg_psi.busevent;

import android.support.annotation.Nullable;

import com.example.weizhi.sg_psi.network.response.PsiJson;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class GetPsiEvent {
    public static final int SUCCESS = 1;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_UNKNOWN = 3;

    public final int status;
    public final PsiJson psiJson;

    public GetPsiEvent(int status, @Nullable PsiJson psiJson){
        if(status == SUCCESS && psiJson == null){
            throw new NullPointerException("Success event with null psiJson");
        }

        this.status = status;
        this.psiJson = psiJson;
    }
}
