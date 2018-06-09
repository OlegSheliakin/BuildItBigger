package com.udacity.gradle.builditbigger.backend;

import javax.annotation.Nonnull;

public class Joke {

    private final String text;

    public Joke(@Nonnull String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "text='" + text + '\'' +
                '}';
    }
}
