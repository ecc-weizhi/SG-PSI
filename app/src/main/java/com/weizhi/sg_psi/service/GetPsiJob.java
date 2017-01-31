package com.weizhi.sg_psi.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.weizhi.sg_psi.SgPsiApplication;
import com.weizhi.sg_psi.busevent.GetPsiEvent;
import com.weizhi.sg_psi.data.RegionInfo;
import com.weizhi.sg_psi.network.NetworkResponse;
import com.weizhi.sg_psi.network.response.PsiJson;
import com.weizhi.sg_psi.util.PsiJsonParser;

import java.util.List;

import timber.log.Timber;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class GetPsiJob extends Job {
    private static final String SINGLE_INSTANCE = "GetPsiJob";
    private static final int PRIORITY = 1;

    private final String mDateTime;
    private final String mDate;

    public GetPsiJob(@Nullable String dateTime,
                     @Nullable String date) {
        super(new Params(PRIORITY).requireNetwork().singleInstanceBy(SINGLE_INSTANCE));
        mDateTime = dateTime;
        mDate = date;
    }

    @Override
    public void onAdded() {
        Timber.d("%s onAdded", getClass().getSimpleName());
    }

    @Override
    public void onRun() throws Throwable {
        final NetworkResponse<PsiJson> result =
                SgPsiApplication.getWebService().getPsi(mDateTime, mDate);


        if(result.error == null &&
                200 <= result.httpStatusCode && result.httpStatusCode < 300){
            // Success
            Timber.i("getPsi WS response success");

            PsiJson psiJson = result.payload;
            List<RegionInfo> regionInfoList = PsiJsonParser.parse(psiJson);
            SgPsiApplication.getBusInstance()
                    .post(new GetPsiEvent(GetPsiEvent.SUCCESS, regionInfoList));
        }
        else {
            // fail
            if(result.error != null){
                throw result.error;
            }
            else {
                throw new FailHttpStatusCode(result.httpStatusCode);
            }
        }
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        // Job has exceeded retry attempts or shouldReRunOnThrowable() has decided to cancel.
        if(throwable instanceof FailHttpStatusCode){
            int statusCode = ((FailHttpStatusCode) throwable).httpStatusCode;
            if(400 <= statusCode && statusCode < 500){
                SgPsiApplication.getBusInstance()
                        .post(new GetPsiEvent(GetPsiEvent.ERROR_NETWORK, null));
            }
        }
        else{
            SgPsiApplication.getBusInstance()
                    .post(new GetPsiEvent(GetPsiEvent.ERROR_UNKNOWN, null));
        }
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable,
                                                     int runCount, int maxRunCount) {
        // An error occurred in onRun.
        // Return value determines whether this job should retry or cancel. You can further
        // specify a backoff strategy or change the job's priority. You can also apply the
        // delay to the whole group to preserve jobs' running order.

        if(throwable instanceof FailHttpStatusCode){
            int statusCode = ((FailHttpStatusCode) throwable).httpStatusCode;
            if(400 <= statusCode && statusCode < 500){
                return RetryConstraint.CANCEL;
            }
        }

        return RetryConstraint.createExponentialBackoff(runCount, 1000);
    }

    private static class FailHttpStatusCode extends Exception{
        public final int httpStatusCode;
        public FailHttpStatusCode(int httpStatusCode){
            this.httpStatusCode = httpStatusCode;
        }
    }
}
