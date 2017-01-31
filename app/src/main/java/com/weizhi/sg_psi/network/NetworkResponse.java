package com.weizhi.sg_psi.network;

import android.support.annotation.Nullable;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class NetworkResponse<T> {
    public final T payload;
    public final Integer httpStatusCode;
    public final Throwable error;

    public NetworkResponse(@Nullable T payload,
                           @Nullable Integer httpStatusCode,
                           @Nullable Throwable error){
        this.payload = payload;
        this.httpStatusCode = httpStatusCode;
        this.error = error;
    }
}
