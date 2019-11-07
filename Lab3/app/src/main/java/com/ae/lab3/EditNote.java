package com.ae.lab3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNote extends AppCompatActivity {

    Button saveB;
    EditText titleT;
    EditText contentT;
    NoteDB noteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        noteDB = new NoteDB(this);

        saveB = findViewById(R.id.saveBtn);
        titleT = findViewById(R.id.titleTxt);
        contentT = findViewById(R.id.contentTxt);
    }
    public void save(View v){
        String title = titleT.getText().toString();
        String content = contentT.getText().toString();
        Note note = new Note(title, content);
        noteDB.addNote(note);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
