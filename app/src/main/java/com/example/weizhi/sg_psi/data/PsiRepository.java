package com.example.weizhi.sg_psi.data;

import android.support.annotation.NonNull;

import com.example.weizhi.sg_psi.network.response.PsiJson;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class PsiRepository implements PsiDataSource{
    private static final int CACHE_TIMEOUT = 90000; // 15 minutes

    private static PsiRepository mInstance;

    // A simple memory cache for psi information.
    private PsiJson mCache;
    private Long mCacheLastUpdate;
    private boolean mIsCacheDirty = false;

    private final PsiDataSource mRemoteSource;

    private PsiRepository(@NonNull PsiDataSource remoteSource){
        mRemoteSource = remoteSource;
    }

    public static PsiRepository getInstance(@NonNull PsiDataSource remoteSource){
        if(mInstance == null){
            mInstance = new PsiRepository(remoteSource);
        }

        return mInstance;
    }

    public static void destroyInstance() {
        mInstance = null;
    }


    @Override
    public void getPsi(@NonNull GetPsiCallback callback) {
        // Check time. If last update happenss more than CACHE_TIMEOUT milliseconds ago, we flag
        // cache as dirty.
        long currentTime = System.nanoTime();
        if(mCacheLastUpdate != null){
            mIsCacheDirty = mIsCacheDirty &&
                    (Math.abs(currentTime - mCacheLastUpdate) > CACHE_TIMEOUT);
        }

        // Fetch new Psi data if cache is dirty or cache is null.
        if(mIsCacheDirty || mCache == null){
            mRemoteSource.getPsi(callback);
        }
        else{
            callback.onPsiLoaded(mCache);
        }
    }
}
