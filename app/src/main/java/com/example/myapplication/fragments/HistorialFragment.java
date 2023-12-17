package com.example.myapplication.fragments;

import android.bluetooth.BluetoothManager;
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

public class HistorialFragment extends Fragment implements HistorialAdapter.OnItemDeleteListener{

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

        // Ocultar el botón de descarga si no hay historial y mostrar el texto de que no hay historial
        if (Ejemplo.isEmpty()) {
            view.findViewById(R.id.downloadHistory).setVisibility(View.GONE);
            view.findViewById(R.id.emptyHistory).setVisibility(View.VISIBLE);
        }
        // Mostrar el botón de descarga si hay historial y ocultar el texto de que no hay historial
        else {
            view.findViewById(R.id.downloadHistory).setVisibility(View.VISIBLE);
            view.findViewById(R.id.emptyHistory).setVisibility(View.GONE);
        }


        HistorialAdapter wordsAdapter = new HistorialAdapter(this.requireActivity(), 0, Ejemplo);
        wordsAdapter.setOnItemDeleteListener(this);
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
    public void onItemDelete(int position) {
        eliminarElementoDelHistorial(position, requireView());
    }

    public void eliminarElementoDelHistorial(int position,@NonNull View view) {
        ArrayList<TranslatorData> historial = MainActivity.translator.getHistory();

        // Elimina el elemento del historial
        historial.remove(position);

        // Notifica al adaptador que los datos han cambiado
        HistorialAdapter adapter = (HistorialAdapter) ((ListView) view.findViewById(R.id.HistorialList)).getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        // Actualiza la visibilidad del botón de descarga
        actualizarVisibilidadBotonDescarga(view);

        // Puedes mostrar un mensaje o realizar otras acciones si es necesario
        Toast.makeText(requireActivity(), "Elemento eliminado del historial", Toast.LENGTH_SHORT).show();
    }

    private void actualizarVisibilidadBotonDescarga(@NonNull View view) {
        if (MainActivity.translator.getHistory().isEmpty()) {
            view.findViewById(R.id.downloadHistory).setVisibility(View.GONE);
            view.findViewById(R.id.emptyHistory).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.downloadHistory).setVisibility(View.VISIBLE);
            view.findViewById(R.id.emptyHistory).setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
