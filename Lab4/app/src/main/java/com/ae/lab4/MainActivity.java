package com.ae.lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button saveAsPublcButton;
    Button saveAsPrivateButton;
    Button backButton;
    EditText dataText;

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveAsPublcButton = (Button) findViewById(R.id.savePublicBtn);
        saveAsPrivateButton = (Button) findViewById(R.id.savePrivateBtn);
        backButton = (Button) findViewById(R.id.backBtn);
        dataText = (EditText) findViewById(R.id.dataTxt);
    }

    public void WHERES_THE_TOAST(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public File getPrivateStorageDir(String folderName) {
        File file = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), folderName);
        file.mkdirs();
        return file;
    }

    public File getPublicStorageDir(String folderName) {
        File file = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), folderName);
        file.mkdirs();
        return file;
    }

    public void savePrivate(View view) {
        try {
            String data = dataText.getText().toString();
            File f = getPrivateStorageDir("l4");
            File af = new File(f, "lab4.txt");
            af.createNewFile();
            FileWriter writer = new FileWriter(af);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(data);
            buffer.close();
            WHERES_THE_TOAST("WROTE PRIVATE");
        } catch (Exception e) {
            WHERES_THE_TOAST(e.toString());
        }
    }

    public void savePublic(View view) {
        try {
            String data = dataText.getText().toString();
            File f = getPublicStorageDir("l4");
            File af = new File(f,  "lab4.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(af);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
            WHERES_THE_TOAST("WROTE PUBLIC");
        } catch (Exception e) {
            WHERES_THE_TOAST(e.toString());
        }
    }

    public void view(View view) {
        Intent intent = new Intent(this, ViewSavedData.class);
        startActivity(intent);
    }
}
