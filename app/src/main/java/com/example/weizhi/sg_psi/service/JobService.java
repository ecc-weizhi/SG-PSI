package com.example.weizhi.sg_psi.service;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.example.weizhi.sg_psi.SgPsiApplication;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class JobService extends FrameworkJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return SgPsiApplication.getInstance().getJobManager();
    }
}
