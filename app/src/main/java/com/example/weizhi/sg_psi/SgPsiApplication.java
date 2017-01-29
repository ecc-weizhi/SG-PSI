package com.example.weizhi.sg_psi;

import android.app.Application;
import android.os.Build;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;
import com.example.weizhi.sg_psi.network.WebService;
import com.example.weizhi.sg_psi.network.WebServiceImpl;
import com.example.weizhi.sg_psi.service.GcmJobService;
import com.example.weizhi.sg_psi.service.JobService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.squareup.otto.Bus;

import timber.log.Timber;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class SgPsiApplication extends Application {
    private JobManager jobManager;
    private static SgPsiApplication mContext;
    private static Bus mBus;
    private static WebService mWebService;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext = this;

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

        getBusInstance().register(this);
        mWebService = new WebServiceImpl(WebServiceImpl.BASE_URL);
        getJobManager();// ensure it is created
    }

    public static SgPsiApplication getInstance(){
        return mContext;
    }

    private void configureJobManager() {
        Configuration.Builder builder = new Configuration.Builder(this)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(1)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120);//wait 2 minute
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.scheduler(
                    FrameworkJobSchedulerService.createSchedulerFor(this, JobService.class), true);
        } else {
            int enableGcm = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
            if (enableGcm == ConnectionResult.SUCCESS) {
                builder.scheduler(
                        GcmJobSchedulerService.createSchedulerFor(this, GcmJobService.class), true);
            }
        }
        jobManager = new JobManager(builder.build());
    }

    public synchronized JobManager getJobManager() {
        if (jobManager == null) {
            configureJobManager();
        }
        return jobManager;
    }

    public static WebService getWebService(){
        return mWebService;
    }

    public static Bus getBusInstance(){
        if(mBus == null){
            mBus = new MainThreadBus();
        }

        return mBus;
    }
}
