package com.udacity.gradle.builditbigger;

public class AsyncTaskResult<T> {
    private T result;

    @Override
    public String toString() {
        return "AsyncTaskResult{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }

    private Exception error;

    public T getResult() {
        return result;
    }

    public Exception getError() {
        return error;
    }

    public AsyncTaskResult(T result) {
        super();
        this.result = result;
    }

    public AsyncTaskResult(Exception error) {
        super();
        this.error = error;
    }
}