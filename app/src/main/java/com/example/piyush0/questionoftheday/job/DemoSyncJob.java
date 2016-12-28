package com.example.piyush0.questionoftheday.job;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.example.piyush0.questionoftheday.activities.MainActivity;
import com.example.piyush0.questionoftheday.utils.Refresh;

import java.util.concurrent.TimeUnit;

/**
 * Created by piyush0 on 10/12/16.
 */
public class DemoSyncJob extends Job {

    public static final String TAG = "job_demo_tag";

    @Override
    @NonNull
    protected Result onRunJob(Params params) {

        Refresh.job(getContext().getSharedPreferences(MainActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE), getContext());
        Log.d(TAG, "onRunJob: " + "job ran");
        return Result.SUCCESS;
    }

    public int schedulePeriodicJob() {


        int jobId = new JobRequest.Builder(DemoSyncJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setPersisted(true)
                .build()
                .schedule();
        //TODO: Change the time to 60 minutes

        return jobId;

    }

    public void cancelJob(int jobId) {
        JobManager.instance().cancel(jobId);
    }

}