package com.enlern.pen.serialport.application;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.enlern.pen.serialport.preferences.SerialSharedPreferences;

import java.io.File;
import java.io.IOException;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

/**
 * Created by pen on 2018/1/10.
 */

public class SerialApplication extends Application {
    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort = null;

    private SerialApplication mContext;
    private String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public SerialPort getSerialPort() throws SecurityException, IOException {
        if (mSerialPort == null) {
            /* Read serial port parameters */
            String path = (String) SerialSharedPreferences.get(mContext, "SERIAL_PATH", "/dev/ttyUSB0");
            int rate = (Integer) SerialSharedPreferences.get(mContext, "SERIAL_RATE", 115200);

			/* Check parameters */
            if ((path.length() == 0) || (rate == -1)) {
                Toast.makeText(mContext, "请先配置串口", Toast.LENGTH_SHORT).show();
            }

            Log.i(TAG, "getSerialPort: 串口号：" + path + " 波特率：" + rate);

			/*Open the serial port*/
            mSerialPort = new SerialPort(new File(path), rate, 0);
        }
        return mSerialPort;
    }

    public void closeSerialPort() {
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
    }
}
