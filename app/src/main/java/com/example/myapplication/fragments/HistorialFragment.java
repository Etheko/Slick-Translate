package com.example.myapplication.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.HistorialAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TranslatorData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HistorialFragment extends Fragment {

    public static HistorialFragment newInstance() {
        return new HistorialFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView List = requireView().findViewById(R.id.HistorialList);

        ArrayList<TranslatorData> Ejemplo = MainActivity.translator.getHistory();

        //Vincular botón de descarga con el método descargarTexto
        view.findViewById(R.id.downloadHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descargarTexto();
            }
        });

        // Ocultar el botón de descarga si no hay historial
        if (Ejemplo.isEmpty()) {
            view.findViewById(R.id.downloadHistory).setVisibility(View.GONE);
        }
        // Mostrar el botón de descarga si hay historial
        else {
            view.findViewById(R.id.downloadHistory).setVisibility(View.VISIBLE);
        }


        HistorialAdapter wordsAdapter = new HistorialAdapter(this.requireActivity(), 0, Ejemplo);
        List.setAdapter(wordsAdapter);

        // Listener para el click en un elemento de la lista
        List.setOnItemClickListener((parent, view1, position, id) -> {
            TranslatorData current = Ejemplo.get(position);

            // Copiar en el portapapeles el texto traducido
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) requireActivity().getSystemService(android.content.Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", current.getTranslatedText());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(requireActivity(), "Texto copiado al portapapeles", Toast.LENGTH_SHORT).show();
        });

        // Listener para el scroll en la lista (ocultar el botón de descarga y mostrarlo al parar de hacer scroll)


    }

    // Metodo para recorrer la lista del historial y descargarlo como fichero txt
    public void descargarTexto() {
        // Comprobar si hay historial
        if (MainActivity.translator.getHistory().isEmpty()) {
            Toast.makeText(requireActivity(), "No hay historial", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ArrayList<TranslatorData> Ejemplo = MainActivity.translator.getHistory();
            String texto = "";
            for (TranslatorData data : Ejemplo) {
                texto += "[" + data.getFromLanguage() + " -> " + data.getToLanguage() + "]: " + data.getOriginalText() + " -> " + data.getTranslatedText() + "\n";
            }
            //Mover el fichero a la carpeta de descargas
            File downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(downloads, "historial.txt");

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(texto);
                writer.close();
                Toast.makeText(requireActivity(), "Historial descargado en la carpeta Descargas", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
