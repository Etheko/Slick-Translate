package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class aboutDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.requireActivity());
        builder.setTitle("Autores")
                .setMessage("Aplicación creada por:\n\n" +
                        "- Enrique Tentor\n" +
                        "- Adrián Soriano\n" +
                        "- Pedro Torrecilla\n" +
                        "- Alba Velasco\n" +
                        "- Alberto Roldán\n" +
                        "- Cristian A. Vlad\n" +
                        "- Izan Ruiz\n")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    // do nothing
                });

        return builder.create();
    }
}
