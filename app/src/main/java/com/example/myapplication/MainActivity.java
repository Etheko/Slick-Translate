package com.example.myapplication;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText multi1;
    EditText multi2;
    Spinner spinner_origin;
    String idioma_origin;
    String idioma_destination;
    Spinner spinner_destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Spinner spinnerorigin = (Spinner) findViewById(R.id.spinner_origin_lang);
        Spinner spinnerdestination = (Spinner) findViewById(R.id.spinner_destination_lang);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array_origin, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.languages_array_destination, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerorigin.setAdapter(adapter);
        spinnerdestination.setAdapter(adapter2);

        spinner_origin = findViewById(R.id.spinner_origin_lang);
        spinner_destination = findViewById(R.id.spinner_destination_lang);

        // Configura el listener para el Spinner
        spinner_origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String seleccion_origin = parentView.getItemAtPosition(position).toString();
                idioma_origin=seleccion_origin;
                // "seleccion" contiene el texto del elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar la ausencia de selección si es necesario
            }
        });

        // Configura el listener para el Spinner
        spinner_destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String seleccion_destination = parentView.getItemAtPosition(position).toString();
                idioma_destination=seleccion_destination;
                // "seleccion" contiene el texto del elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar la ausencia de selección si es necesario
            }
        });


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
            String trText = translator.traducir(this.idioma_origin,this.idioma_destination,text);
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