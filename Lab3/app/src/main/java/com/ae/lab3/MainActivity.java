package com.ae.lab3;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button newNoteButton;
    ListView notesList;
    NoteDB notes;

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
    }

    public void WHERES_THE_TOAST(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNoteButton = (Button) findViewById(R.id.newNoteBtn);
        notesList = (ListView) findViewById(R.id.notesList);

        ArrayList<String> titles;
        notes = new NoteDB(this);
        titles = notes.getTitles();
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        notesList.setAdapter(adapter);

        notesList.setOnItemClickListener((parent, view, position, id) -> {
            String title = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ViewNote.class);
            intent.putExtra("title",title);
            intent.putExtra("old","old");
            int REQUEST_ID=13;
            startActivityForResult(intent, REQUEST_ID);
        });
    }

    public void newNote(View v) {
        Intent intent = new Intent(this, EditNote.class);
        startActivity(intent);
    }
}