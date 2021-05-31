package com.example.biametriclogin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//класс для регистрации
public class Register extends AppCompatActivity {

    private static final String PREFS_FILE = "Account";
    private static final String PREF_NAME = "Name";
     EditText editPasswd;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        editPasswd=findViewById(R.id.editPasswd);
        Button save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(v -> Enter());
    }

    public void saveName() {
        // получаем введенное имя
        EditText nameBox = (EditText) findViewById(R.id.editPasswd);
        String name = nameBox.getText().toString();
        // сохраняем его в настройках
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(PREF_NAME, name);
        prefEditor.apply();
    }

    public void Enter() {
        saveName();
        Toast toast = Toast.makeText(getApplicationContext(),
                "Данные сохранены", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
    public void b0(View view) {
        editPasswd = findViewById(R.id.editPasswd);

        editPasswd.setText(editPasswd.getText().toString() + "0");
    }

    public void b1(View view) {
        editPasswd = findViewById(R.id.editPasswd);

        editPasswd.setText(editPasswd.getText().toString() + "1");
    }

    public void b2(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "2");
    }

    public void b3(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "3");
    }

    public void b4(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "4");
    }

    public void b5(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "5");
    }

    public void b6(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "6");
    }

    public void b7(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "7");
    }

    public void b8(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "8");
    }

    public void b9(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(editPasswd.getText().toString() + "9");
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }

    public void delete(View view) {
        editPasswd = findViewById(R.id.editPasswd);
        editPasswd.setText(removeLastChar(editPasswd.getText().toString()));

    }

}
