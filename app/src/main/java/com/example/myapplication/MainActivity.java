package com.example.myapplication;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText multi1;
    EditText multi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void buttonPressed(View view) {
        multi1 = findViewById(R.id.multiText1);
        multi2 = findViewById(R.id.multiText2);
        String text = multi1.getText().toString();

        if (text.isEmpty()) {
            Toast.makeText(this, "No hay texto para traducir", Toast.LENGTH_SHORT).show();
        } else {
            hideSoftKeyboard(this);
            Traductor translator = new Traductor(); //creo una clase traductor donde se haga la operacion de traducir para no hacerla aqui
            String trText = translator.traducir(text);
            multi2.setText(trText);
        }
    }

    public void copyButtonPressed(View view) {
        multi2 = findViewById(R.id.multiText2);
        String text2 = multi2.getText().toString();

        if (text2.isEmpty()) {
            Toast.makeText(this, "No hay texto para copiar", Toast.LENGTH_SHORT).show();
        } else {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(text2);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =(InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(),
                    0
            );
        }
    }

}