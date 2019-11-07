package com.ae.lab2;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class mService {
    private WifiManager wifi;
    private Context applicationContext;
    SimpleAdapter adapter;
    List<ScanResult> results;
    String ITEM_KEY = "key";
    ArrayList<HashMap<String, String>> theList = new ArrayList<>();

    mService(Context applicationContext) {
        this.applicationContext = applicationContext;
        this.wifi = (WifiManager) applicationContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    void onStartCommand(Button startButton, TextView txt) {
        startButton.setText(Environment.getExternalStorageState());
        Toast.makeText(applicationContext, "" + Environment.getExternalStorageState(), Toast.LENGTH_LONG).show();
        try {
            txt.setText(String.format("%s%swifi.txt", applicationContext.getFilesDir(), File.separator));
            FileWriter writer = new FileWriter(applicationContext.getFilesDir() + File.separator + "wifi.txt");
            writer.append(this.wifi.getScanResults().toString());
            writer.flush();
            writer.close();
            Toast.makeText(this.applicationContext, "WOW, THAT SURE WAS FUN WRITING THE FILE!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            txt.setText(e.getMessage());
        }
    }

    void onStopCommand(Button stopButton) {
    }
}
