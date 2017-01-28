package com.example.weizhi.sg_psi.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.weizhi.sg_psi.network.response.PsiJson;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public interface WebService {
    @NonNull
    NetworkResponse<PsiJson> getPsi(@Nullable String dateTime, @Nullable String date);
}
