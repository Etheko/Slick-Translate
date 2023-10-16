package com.example.myapplication.fragments;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Activity;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.Traductor;

import java.util.Objects;

public class TraductorFragment extends Fragment {

    private EditText multi2;
    private String idioma_origin, idioma_destination;

    public static TraductorFragment newInstance() {
        return new TraductorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.translator_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setupView() {
        Spinner spinner_origin = requireView().findViewById(R.id.spinner_origin_lang);
        Spinner spinner_destination = requireView().findViewById(R.id.spinner_destination_lang);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.requireActivity(), R.array.languages_array_origin, android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.requireActivity(), R.array.languages_array_destination, android.R.layout.simple_dropdown_item_1line);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_origin.setAdapter(adapter);
        spinner_destination.setAdapter(adapter2);

        // Configura el listener para el Spinner
        spinner_origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                idioma_origin = parentView.getItemAtPosition(position).toString();
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
                idioma_destination = parentView.getItemAtPosition(position).toString();
                // "seleccion" contiene el texto del elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar la ausencia de selección si es necesario
            }
        });

        Button button = (Button) requireView().findViewById(R.id.buttonTrad);
        button.setOnClickListener(this::buttonPressed);

        ImageButton imageButton = (ImageButton) requireView().findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this::copyButtonPressed);
    }

    public void buttonPressed(View view) {
        EditText multi1 = requireView().findViewById(R.id.multiText1);
        multi2 = requireView().findViewById(R.id.multiText2);
        String text = multi1.getText().toString();

        if (text.isEmpty()) {
            Toast.makeText(this.requireActivity(), "No hay texto para traducir", Toast.LENGTH_SHORT).show();
        } else {
            hideSoftKeyboard(this.requireActivity());
            Traductor translator = new Traductor(); //creo una clase traductor donde se haga la operacion de traducir para no hacerla aqui
            String trText = translator.traducir(this.idioma_origin, this.idioma_destination, text);
            multi2.setText(trText);
        }
    }

    public void copyButtonPressed(View view) {
        multi2 = requireView().findViewById(R.id.multiText2);
        String text2 = multi2.getText().toString();

        if (text2.isEmpty()) {
            Toast.makeText(this.requireActivity(), "No hay texto para copiar", Toast.LENGTH_SHORT).show();
        } else {
            ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(text2);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
        }
    }

}