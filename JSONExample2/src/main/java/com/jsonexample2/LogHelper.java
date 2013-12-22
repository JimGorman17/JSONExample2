package com.jsonexample2;

import android.util.Log;

/**
 * Created by jgorman on 12/19/13.
 */
public class LogHelper {
    public static void ProcessAndThreadId(String label) {
        String logMessage = String.format("%s, Process ID:%d, Thread ID:%d", label, android.os.Process.myTid(), Thread.currentThread().getId());
        Log.i("com.example.jsonexample", logMessage);
    }
}
