package com.example.weizhi.sg_psi;

import com.birbit.android.jobqueue.JobManager;
import com.example.weizhi.sg_psi.busevent.GetPsiEvent;
import com.example.weizhi.sg_psi.data.PsiDataSource;
import com.example.weizhi.sg_psi.data.RegionInfo;
import com.example.weizhi.sg_psi.data.RemotePsiDataSource;
import com.example.weizhi.sg_psi.network.response.PsiJson;
import com.squareup.otto.Bus;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class RemotePsiDataSourceTest {
    @Test
    public void getPsiSuccessTest(){
        Bus mockedBus = mock(Bus.class);
        JobManager mockedJobManager = mock(JobManager.class);
        PsiDataSource.GetPsiCallback mockedCallback = mock(PsiDataSource.GetPsiCallback.class);
        List<RegionInfo> regionInfoList = new ArrayList<>();
        GetPsiEvent event = new GetPsiEvent(GetPsiEvent.SUCCESS, regionInfoList);

        RemotePsiDataSource remotePsiDataSource = new RemotePsiDataSource(mockedBus, mockedJobManager);
        remotePsiDataSource.getPsi(mockedCallback);
        remotePsiDataSource.onReceiveEvent(event);
        verify(mockedCallback).onPsiLoaded(regionInfoList);
    }

    @Test
    public void getPsiErrorNetworkTest(){
        Bus mockedBus = mock(Bus.class);
        JobManager mockedJobManager = mock(JobManager.class);
        PsiDataSource.GetPsiCallback mockedCallback = mock(PsiDataSource.GetPsiCallback.class);
        GetPsiEvent event = new GetPsiEvent(GetPsiEvent.ERROR_NETWORK, null);

        RemotePsiDataSource remotePsiDataSource = new RemotePsiDataSource(mockedBus, mockedJobManager);
        remotePsiDataSource.getPsi(mockedCallback);
        remotePsiDataSource.onReceiveEvent(event);
        verify(mockedCallback).onDataNotAvailable(PsiDataSource.GetPsiCallback.ERROR_NETWORK);
    }

    @Test
    public void getPsiErrorUnknownTest(){
        Bus mockedBus = mock(Bus.class);
        JobManager mockedJobManager = mock(JobManager.class);
        PsiDataSource.GetPsiCallback mockedCallback = mock(PsiDataSource.GetPsiCallback.class);
        GetPsiEvent event = new GetPsiEvent(GetPsiEvent.ERROR_UNKNOWN, null);

        RemotePsiDataSource remotePsiDataSource = new RemotePsiDataSource(mockedBus, mockedJobManager);
        remotePsiDataSource.getPsi(mockedCallback);
        remotePsiDataSource.onReceiveEvent(event);
        verify(mockedCallback).onDataNotAvailable(PsiDataSource.GetPsiCallback.ERROR_UNKNOWN);
    }
}
