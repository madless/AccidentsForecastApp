package com.madless.accidentsforecast.utils;

import android.util.Log;

import java.util.Locale;

public class Loggr {
    private Class<?> cls;
    private static final String TAG = "AccidentsForecastApp";
    private static final String FILTER_BODY_NAME = "<body>";
    private static final String FILTER_MSG_NAME = " >> ";
    private static final String CHUNK_FORMAT = "CHUNK %d OF %d:  %s";
    private static final int CONSOLE_LINE_MAX_LENGTH = 4000;

    public Loggr(Class<?> cls) {
        this.cls = cls;
    }

    public static void v(Class cls, String message) {
        print(Log.VERBOSE, cls.getSimpleName() + FILTER_MSG_NAME + message);
    }

    public static void d(Class cls, String message) {
        print(Log.DEBUG, cls.getSimpleName() + FILTER_MSG_NAME + message);
    }

    public static void i(Class cls, String message) {
        print(Log.INFO, cls.getSimpleName() + FILTER_MSG_NAME + message);
    }

    public static void w(Class cls, String message) {
        print(Log.WARN, cls.getSimpleName() + FILTER_MSG_NAME + message);
    }

    public static void e(Class cls, String message) {
        print(Log.ERROR, cls.getSimpleName() + FILTER_MSG_NAME + message);
    }

    public void v(String message) {
        v(cls, message);
    }

    public void d(String message) {
        d(cls, message);
    }

    public void i(String message) {
        i(cls, message);
    }

    public void w(String message) {
        w(cls, message);
    }

    public void e(String message) {
        e(cls, message);
    }


    private static void print(int priority, String message) {
        String fullMsg = FILTER_BODY_NAME + message;
        if (fullMsg.length() > CONSOLE_LINE_MAX_LENGTH) {
            int chunkCount = fullMsg.length() / CONSOLE_LINE_MAX_LENGTH;
            for (int i = 0; i <= chunkCount; i++) {
                int max = CONSOLE_LINE_MAX_LENGTH * (i + 1);
                String textToPrint;
                if (max >= fullMsg.length()) {
                    textToPrint = String.format(Locale.ENGLISH, CHUNK_FORMAT, i, chunkCount, fullMsg.substring(CONSOLE_LINE_MAX_LENGTH * i));
                } else {
                    textToPrint = String.format(Locale.ENGLISH, CHUNK_FORMAT, i, chunkCount, fullMsg.substring(CONSOLE_LINE_MAX_LENGTH * i, max));
                }
                Log.println(priority, TAG, textToPrint);
            }
        } else {
            Log.println(priority, TAG, FILTER_BODY_NAME + message);
        }
    }
}
