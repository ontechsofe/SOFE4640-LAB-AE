package com.ae.lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class ViewSavedData extends AppCompatActivity {

    TextView dataTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_data);

        dataTxt = findViewById(R.id.dataTxt);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showPrivate(View view) {
        try {
            File f = getPrivateStorageDir("l4");
            File af = new File(f, "lab4.txt");
            af.createNewFile();
            FileReader reader = new FileReader(af);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
            dataTxt.setText(stringBuilder.toString());
        } catch (Exception e) {
            WHERES_THE_TOAST(e.toString());
        }
    }

    public void showPublic(View view) {
        try {
            File f = getPublicStorageDir("l4");
            File af = new File(f, "lab4.txt");
            af.createNewFile();
            FileReader reader = new FileReader(af);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
            dataTxt.setText(stringBuilder.toString());
        } catch (Exception e) {
            WHERES_THE_TOAST(e.toString());
        }
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
}
