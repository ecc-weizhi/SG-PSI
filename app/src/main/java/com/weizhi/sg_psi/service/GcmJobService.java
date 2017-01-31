package com.weizhi.sg_psi.service;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;
import com.weizhi.sg_psi.SgPsiApplication;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class GcmJobService extends GcmJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return SgPsiApplication.getInstance().getJobManager();
    }
}
