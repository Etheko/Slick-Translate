package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HistorialAdapter extends ArrayAdapter<TranslatorData> {

    public HistorialAdapter(@NonNull Context context, int resource, ArrayList<TranslatorData> words) {
        super(context, resource, words);
    }

    public interface OnItemDeleteListener {
        void onItemDelete(int position);

    }

    public interface OnItemCopyListener {
        void onItemCopy(int position);
    }

    private OnItemDeleteListener onItemDeleteListener;

    private OnItemCopyListener onItemCopyListener;

    public void setOnItemCopyListener(OnItemCopyListener listener) {
        this.onItemCopyListener = listener;
    }

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        this.onItemDeleteListener = listener;
    }
    
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View list_item = convertView;

        if (list_item == null) {
            list_item = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }

        TranslatorData current = getItem(position);
        assert current != null;

        TextView TextoTraduccion = list_item.findViewById(R.id.TextoTraduccion);
        TextoTraduccion.setText(current.getOriginalText());

        TextView Traduccion = list_item.findViewById(R.id.Traduccion);
        Traduccion.setText(current.getTranslatedText());

        TextView Idiomas = list_item.findViewById(R.id.Idiomas);
        String fromLanguage = current.getFromLanguage();
        if(fromLanguage == "NONE"){
            Idiomas.setText("AUTO -> " + current.getToLanguage());
        }else{
            Idiomas.setText(fromLanguage + " -> " + current.getToLanguage());
        }

        list_item.setPadding(15, 15, 15, 15);

        ImageButton Eliminar = list_item.findViewById(R.id.btnDelete);
        Eliminar.setOnClickListener(v -> {
            if (onItemDeleteListener != null) {
                onItemDeleteListener.onItemDelete(position);
            }
        });

        ImageButton Copiar = list_item.findViewById(R.id.btnCopy);
        Copiar.setOnClickListener(v -> {
            if (onItemCopyListener != null) {
                onItemCopyListener.onItemCopy(position);
            }
        });


        return list_item;
    }
}
