package com.ae.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewNote extends AppCompatActivity {

    EditText title;
    EditText content;
    Button back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        intent = getIntent();
        title = findViewById(R.id.titleTxt);
        content = findViewById(R.id.contentTxt);
        back = findViewById(R.id.backBtn);

        String titleStr = intent.getStringExtra("title");
        Note note = new Note();
        NoteDB noteDB = new NoteDB(this);
        note = noteDB.getNote(titleStr);

        title.setText(note.getTitle());
        content.setText(note.getContent());
    }

    public void goBack(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
