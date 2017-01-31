package com.example.weizhi.sg_psi;

import com.weizhi.sg_psi.data.PsiRepository;
import com.weizhi.sg_psi.data.RegionInfo;
import com.weizhi.sg_psi.ui.map.MapContract;
import com.weizhi.sg_psi.ui.map.MapPresenter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class MapPresenterTest {
    @Test
    public void onStartTest(){
        MapContract.View mockedView = mock(MapContract.View.class);
        PsiRepository mockedPsiRepository = mock(PsiRepository.class);

        MapPresenter presenter = new MapPresenter(mockedView, mockedPsiRepository);

        presenter.onStart();
        verify(mockedView).showLoading(true);
        verify(mockedPsiRepository).getPsi(presenter);
    }

    @Test
    public void onMapReadyTest(){
        MapContract.View mockedView = mock(MapContract.View.class);
        PsiRepository mockedPsiRepository = mock(PsiRepository.class);

        MapPresenter presenter = new MapPresenter(mockedView, mockedPsiRepository);

        presenter.onMapReady();
        verify(mockedView).showLoading(true);
        verify(mockedPsiRepository).getPsi(presenter);
    }

    @Test
    public void onRefreshClickTest(){
        MapContract.View mockedView = mock(MapContract.View.class);
        PsiRepository mockedPsiRepository = mock(PsiRepository.class);

        MapPresenter presenter = new MapPresenter(mockedView, mockedPsiRepository);

        presenter.onRefreshClick();
        verify(mockedPsiRepository).forceRefresh();
        verify(mockedView).showLoading(true);
        verify(mockedPsiRepository).getPsi(presenter);
    }

    @Test
    public void onPsiLoadedShowPsiTest(){
        // Set up mocks
        MapContract.View mockedView = mock(MapContract.View.class);
        PsiRepository mockedPsiRepository = mock(PsiRepository.class);

        MapPresenter presenter = new MapPresenter(mockedView, mockedPsiRepository);

        // Prepare data
        List<RegionInfo> regionInfoList = new ArrayList<>();
        presenter.setShowType(MapPresenter.SHOW_PSI);

        // Test
        presenter.onPsiLoaded(regionInfoList);
        verify(mockedView).showLoading(false);
        verify(mockedView).showPsiInfo(regionInfoList);
    }

    @Test
    public void onPsiLoadedShowPollutantTest(){
        // Set up mocks
        MapContract.View mockedView = mock(MapContract.View.class);
        PsiRepository mockedPsiRepository = mock(PsiRepository.class);

        MapPresenter presenter = new MapPresenter(mockedView, mockedPsiRepository);

        // Prepare data
        List<RegionInfo> regionInfoList = new ArrayList<>();
        presenter.setShowType(MapPresenter.SHOW_POLLUTANT);

        // Test
        presenter.onPsiLoaded(regionInfoList);
        verify(mockedView).showLoading(false);
        verify(mockedView).showPollutantInfo(regionInfoList);
    }

    @Test
    public void onDataNotAvailableNetworkErrorTest(){
        // Set up mocks
        MapContract.View mockedView = mock(MapContract.View.class);
        PsiRepository mockedPsiRepository = mock(PsiRepository.class);

        MapPresenter presenter = new MapPresenter(mockedView, mockedPsiRepository);

        // Test
        presenter.onDataNotAvailable(MapPresenter.ERROR_NETWORK);
        verify(mockedView).showLoading(false);
        verify(mockedView).showNetworkError();
    }

    @Test
    public void onDataNotAvailableUnknownErrorTest(){
        // Set up mocks
        MapContract.View mockedView = mock(MapContract.View.class);
        PsiRepository mockedPsiRepository = mock(PsiRepository.class);

        MapPresenter presenter = new MapPresenter(mockedView, mockedPsiRepository);

        // Test
        presenter.onDataNotAvailable(MapPresenter.ERROR_UNKNOWN);
        verify(mockedView).showLoading(false);
        verify(mockedView).showUnknownError();
    }
}
