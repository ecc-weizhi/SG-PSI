package com.example.weizhi.sg_psi.data;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.example.weizhi.sg_psi.busevent.GetPsiEvent;
import com.example.weizhi.sg_psi.service.GetPsiJob;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class RemotePsiDataSource implements PsiDataSource{

    private final Bus mBus;
    private JobManager mJobManager;
    private GetPsiCallback mCallback;


    public RemotePsiDataSource(@NonNull Bus bus, @NonNull JobManager jobManager){
        mBus = bus;
        mBus.register(this);
        mJobManager = jobManager;
    }

    @Override
    public void getPsi(@NonNull GetPsiCallback callback) {
        mCallback = callback;
        mJobManager.addJobInBackground(new GetPsiJob(null, null));
    }

    @Subscribe
    public void onReceiveEvent(GetPsiEvent event){
        if(mCallback != null){
            switch (event.status){
                case GetPsiEvent.SUCCESS:
                    mCallback.onPsiLoaded(event.regionInfoList);
                    break;

                case GetPsiEvent.ERROR_NETWORK:
                    mCallback.onDataNotAvailable(GetPsiCallback.ERROR_NETWORK);
                    break;

                case GetPsiEvent.ERROR_UNKNOWN:
                    mCallback.onDataNotAvailable(GetPsiCallback.ERROR_UNKNOWN);
                    break;
            }
        }
    }

}
