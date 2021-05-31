package com.example.biametriclogin.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biametriclogin.R;
//класс где заполняется запись
public class NoteActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;

    private DatabaseAdapter adapter;
    private long id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        Button delButton = findViewById(R.id.deleteButton);
        adapter = new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getLong("id");
        }
        // если 0, то добавление
        if (id > 0) {
            // получаем элемент по id из бд
            adapter.open();
            Note note = adapter.getNote(id);
            title.setText(note.getTitle());
            desc.setText(String.valueOf(note.getDesc()));
            adapter.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void SaveValidator(View view) {
        String Title = title.getText().toString();
        String Desc = desc.getText().toString();
        Note note = new Note(id, Title, Desc);
        adapter.open();
        if (Title.equals("") && Desc.equals("")) {
            //данные пустые
            Toast.makeText(getApplicationContext(), this.getString(R.string.add_field),
                    Toast.LENGTH_SHORT)
                    .show();
        } else if (Title.equals("")) {
            //невведен заголовок
            Toast.makeText(getApplicationContext(), this.getString(R.string.add_title),
                    Toast.LENGTH_SHORT)
                    .show();
        } else if (Desc.equals("")) {
            //невведен заголовок
            Toast.makeText(getApplicationContext(), this.getString(R.string.add_desc),
                    Toast.LENGTH_SHORT)
                    .show();
        } else if (id > 0) {
            adapter.update(note);
            adapter.close();
            goHome();
        } else {
            adapter.insert(note);
            adapter.close();
            goHome();
        }


    }

    public void delete(View view) {
        adapter.open();
        adapter.delete(id);
        adapter.close();
        goHome();
    }

    private void goHome() {
        // переход к note
        Intent intent = new Intent(this, com.example.biametriclogin.Note.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}