package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.displayjoke.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, DownloadJokeTask.DownloadJokeCallback {

    private DownloadJokeTask task;

    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Button button = root.findViewById(R.id.btnTellJoke);
        progressBar = root.findViewById(R.id.progress);

        button.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if (task != null) {
            task.cancel(true);
        }

        task = new DownloadJokeTask(this);
        task.execute();

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(Joke joke) {
        progressBar.setVisibility(View.INVISIBLE);
        Context context = getContext();

        if (context != null) {
            JokeActivity.open(getContext(), joke.getText());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        progressBar.setVisibility(View.INVISIBLE);
        throwable.printStackTrace();

        Context context = getContext();

        if (context != null) {
            Toast.makeText(context, R.string.joke_error, Toast.LENGTH_SHORT).show();
        }
    }
}
