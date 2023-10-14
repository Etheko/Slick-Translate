package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import net.suuft.libretranslate.Language;

import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

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

    public void buttonPressed(View view){

        multi1 = findViewById(R.id.multiText1);
        multi2 = findViewById(R.id.multiText2);


        String text = multi1.getText().toString();


        Traductor translator = new Traductor(); //creo una clase traductor donde se haga la operacion de traducir para no hacerla aqui
        String trText = translator.traducir(text);
        multi2.setText(trText);

    }

}