package com.example.displayjoke;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static String JOKE_KEY_EXTRA = JokeActivity.class.getName().concat("_joke");

    public static void open(@NonNull Context context, @NonNull String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JOKE_KEY_EXTRA, joke);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView tvJoke = findViewById(R.id.tvJoke);

        String joke = getIntent().getStringExtra(JOKE_KEY_EXTRA);

        if (joke != null) {
            tvJoke.setText(joke);
        } else {
            tvJoke.setTextColor(Color.RED);
            tvJoke.setText(R.string.joke_error);
        }

    }
}
