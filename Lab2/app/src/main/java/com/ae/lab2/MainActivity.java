package com.ae.lab2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private mService service;

    Button startButton, stopButton;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.service = new mService(getApplicationContext());
        this.startButton = findViewById(R.id.startBtn);
        this.stopButton = findViewById(R.id.stopBtn);
        this.txt = findViewById(R.id.maintxt);
        this.startButton.setOnClickListener(this);
        this.stopButton.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this.getApplicationContext(), "LOOKS LIKE PERMISSION ISSUES", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this.getApplicationContext(), "I MUST SHOW U SOMETHING", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.getApplicationContext(), "I DON'T HAVE TO SHOW YOU ANYTHING", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                this.startService();
                break;
            case R.id.stopBtn:
                this.stopService();
                break;
        }
    }

    private void startService() {
        this.service.onStartCommand(this.startButton, this.txt);
    }

    private void stopService() {
        this.service.onStopCommand(this.stopButton);
    }
}
