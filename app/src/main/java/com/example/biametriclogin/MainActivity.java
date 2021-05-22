package com.example.biametriclogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    EditText editPasswd;
    private static final String PREFS_FILE = "Account";
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Login();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
//подсказка
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();





        ImageView biometricLoginButton = findViewById(R.id.login_btn);
        biometricLoginButton.setOnClickListener(view -> biometricPrompt.authenticate(promptInfo));

        Button registerBtn = findViewById(R.id.button);
        registerBtn.setOnClickListener(v -> Register());
        Button loginBtn = findViewById(R.id.enter_btn);
        loginBtn.setOnClickListener(v -> LoginSuccess());


    }


    public void Register() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void Login() {
        Intent intent = new Intent();
        intent.setClass(this, Note.class);
        startActivity(intent);
    }

    public void LoginSuccess() {
        editPasswd = findViewById(R.id.editPasswd);
        settings = getSharedPreferences("Account", MODE_PRIVATE);
        String password = settings.getString("Name", null);
        if (password.contentEquals(editPasswd.getText())) {
            Intent intent = new Intent();
            intent.setClass(this, Note.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Не удалось войти", Toast.LENGTH_SHORT);
            toast.show();
        }
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
