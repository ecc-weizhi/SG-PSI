package com.example.weizhi.sg_psi.ui.map;

import android.support.annotation.NonNull;

import com.example.weizhi.sg_psi.data.PsiDataSource;
import com.example.weizhi.sg_psi.data.PsiRepository;
import com.example.weizhi.sg_psi.data.RegionInfo;

import java.util.List;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class MapPresenter implements MapContract.ActionListener,
        PsiDataSource.GetPsiCallback{
    public static final int SHOW_PSI = 1;
    public static final int SHOW_POLLUTANT = 2;

    private MapContract.View mView;
    private PsiRepository mPsiRepository;
    private int mShowType = 1;

    public MapPresenter(@NonNull MapContract.View view,
                        @NonNull PsiRepository psiRepository){
        mView = view;
        mPsiRepository = psiRepository;
    }

    @Override
    public void onStart() {
        getPsi();
    }

    @Override
    public void onMapReady() {
        getPsi();
    }

    @Override
    public void onRefreshClick() {
        mPsiRepository.forceRefresh();
        getPsi();
    }

    @Override
    public void onPsiLoaded(@NonNull List<RegionInfo> regionInfoList) {
        mView.showLoading(false);
        switch(mShowType){
            case SHOW_PSI:
                mView.showPsiInfo(regionInfoList);
                break;

            case SHOW_POLLUTANT:
                mView.showPollutantInfo(regionInfoList);
                break;

            default:
                throw new IllegalStateException("mShowType must be either SHOW_PSI or SHOW_POLLUTANT");
        }
    }

    public void setShowType(int showType){
        mShowType = showType;
    }

    @Override
    public void onDataNotAvailable(int reason) {
        mView.showLoading(false);

        switch(reason){
            case ERROR_NETWORK:
                mView.showNetworkError();
                break;

            case ERROR_UNKNOWN:
                mView.showUnknownError();
                break;
        }
    }

    private void getPsi(){
        mView.showLoading(true);
        mPsiRepository.getPsi(this);
    }
}
