package com.example.biametriclogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biametriclogin.db.DatabaseAdapter;
import com.example.biametriclogin.db.NoteActivity;

import java.util.List;


public class Note extends AppCompatActivity {
    private ListView List;
    ArrayAdapter<com.example.biametriclogin.db.Note> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        List = findViewById(R.id.list);

        List.setOnItemClickListener((parent, view, position, id) -> {
            com.example.biametriclogin.db.Note note = arrayAdapter.getItem(position);
            if (note != null) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("id", note.getId());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<com.example.biametriclogin.db.Note> notes = adapter.getNotes();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        List.setAdapter(arrayAdapter);
        adapter.close();
    }

    public void add(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
}
