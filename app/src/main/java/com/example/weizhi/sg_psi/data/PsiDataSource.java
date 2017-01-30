package com.example.weizhi.sg_psi.data;

import android.support.annotation.NonNull;

import com.example.weizhi.sg_psi.network.response.PsiJson;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public interface PsiDataSource {
    interface GetPsiCallback {
        int ERROR_NETWORK = 2;
        int ERROR_UNKNOWN = 3;

        void onPsiLoaded(@NonNull PsiJson psiJson);
        void onDataNotAvailable(int reason);
    }

    void getPsi(@NonNull GetPsiCallback callback);
}
