package com.udacity.gradle.builditbigger;

import android.util.Log;

import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;

import org.junit.Test;

import static org.junit.Assert.*;

public class DownloadJokeTaskTest {

    private final DownloadJokeTask.DownloadJokeCallback callback = new DownloadJokeTask.DownloadJokeCallback() {
        @Override
        public void onSuccess(Joke joke) {

        }

        @Override
        public void onError(Throwable throwable) {

        }
    };

    @Test
    public void onPostExecute() {
        Log.v("NonEmptyStringTest", "Running NonEmptyStringTest test");
        AsyncTaskResult<Joke> result = null;
        DownloadJokeTask endpointsAsyncTask = new DownloadJokeTask(callback);
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(this.getClass().getName(), result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
        assertNotNull(result.getResult());
        assertNotNull(result.getResult().getText());
    }
}