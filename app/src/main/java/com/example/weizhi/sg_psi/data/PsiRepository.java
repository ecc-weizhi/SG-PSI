package com.example.weizhi.sg_psi.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class PsiRepository implements PsiDataSource, PsiDataSource.GetPsiCallback{
    private static final int CACHE_TIMEOUT = 90000; // 15 minutes

    private static PsiRepository mInstance;

    // A simple memory cache for psi information.
    private List<RegionInfo> mCache;
    private Long mCacheLastUpdate;
    private boolean mIsCacheDirty = false;

    private final PsiDataSource mRemoteSource;

    private GetPsiCallback mCurrentCallback;

    private PsiRepository(@NonNull PsiDataSource remoteSource){
        mRemoteSource = remoteSource;
    }

    public static PsiRepository getInstance(@NonNull PsiDataSource remoteSource){
        if(mInstance == null){
            mInstance = new PsiRepository(remoteSource);
        }

        return mInstance;
    }

    public void forceRefresh(){
        mIsCacheDirty = true;
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
            mCurrentCallback = callback;
            mRemoteSource.getPsi(this);
        }
        else{
            callback.onPsiLoaded(mCache);
        }
    }

    @Override
    public void onPsiLoaded(@NonNull List<RegionInfo> regionInfoList) {
        mCache = regionInfoList;
        mCacheLastUpdate = System.nanoTime();
        mIsCacheDirty = false;
        if(mCurrentCallback != null){
            mCurrentCallback.onPsiLoaded(regionInfoList);
            mCurrentCallback = null;
        }
    }

    @Override
    public void onDataNotAvailable(int reason) {
        mCache = null;
        mCacheLastUpdate = null;
        mIsCacheDirty = false;
        if(mCurrentCallback != null){
            mCurrentCallback.onDataNotAvailable(reason);
            mCurrentCallback = null;
        }
    }
}
