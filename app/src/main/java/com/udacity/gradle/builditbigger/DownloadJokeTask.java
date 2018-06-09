package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;

import java.io.IOException;

public class DownloadJokeTask extends AsyncTask<Void, Void, AsyncTaskResult<Joke>> {

    private static MyApi myApiService = null;
    private DownloadJokeCallback delegate;

    interface DownloadJokeCallback {
        void onSuccess(Joke joke);

        void onError(Throwable throwable);
    }

    public DownloadJokeTask(DownloadJokeCallback delegate) {
        this.delegate = delegate;
    }

    @Override
    protected AsyncTaskResult<Joke> doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null)
                    .setRootUrl("https://little-jocker-123.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        try {
            return new AsyncTaskResult<Joke>(myApiService.getJoke().execute());
        } catch (IOException e) {
            return new AsyncTaskResult<Joke>(e);
        }
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<Joke> result) {
        if(result.getResult() != null) {
            delegate.onSuccess(result.getResult());
        } else if (result.getError() != null) {
            delegate.onError(result.getError());
        } else {
            delegate.onError(new Exception("Something went wrong"));
        }
    }
}
