package com.enlern.pen.seriala;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDataReceived(byte[] buffer, int size) {
        Log.i(TAG, "onDataReceived: " + size);
    }
}
