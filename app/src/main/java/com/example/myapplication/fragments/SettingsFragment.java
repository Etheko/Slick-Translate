package com.example.myapplication.fragments;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.aboutDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SettingsFragment extends Fragment {

    int position = 0;
    private String[] DireccionPre = {"a.velascom.2021@alumnos.urjc.es","a.roldan.2021@alumnos.urjc.es","p.torrecilla.2021@alumnos.urjc.es","e.tentor.2021@alumnos.urjc.es","ca.vlad.2021@alumnos.urjc.es","i.ruizba.2021@alumnos.urjc.es"};
    public interface SingleChoiceListener {
        void onPositiveButtonClicked(String[] list, int position);

        void onNegativeButtonClicked();
    }

    SingleChoiceListener mListener;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout aboutButton = requireView().findViewById(R.id.autoresButton);
        aboutButton.setOnClickListener(this::aboutButtonPressed);
        LinearLayout darkModeButton = requireView().findViewById(R.id.darkModeOptionButton);
        darkModeButton.setOnClickListener(this::darkModeButtonPressed);
        LinearLayout emailAdress = requireView().findViewById(R.id.mailButton);
        emailAdress.setOnClickListener(this::ClickAndSendEmail);
    }
    public void ClickAndSendEmail(View v){
        sendEmail(DireccionPre);
    }
    public void sendEmail(String[] Direccion){
        Intent Email = new Intent(Intent.ACTION_SENDTO);
        Email.setData(Uri.parse("mailto:"));
        Email.putExtra(Intent.EXTRA_EMAIL,Direccion);
        startActivity(Email);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void aboutButtonPressed(View view) {
        openDialog();
    }

    public void darkModeButtonPressed(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());

        String[] list = getActivity().getResources().getStringArray(R.array.choicesForDarkModeDialog);

        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;

        switch (currentNightMode) {
            case android.content.res.Configuration.UI_MODE_NIGHT_NO:
                position = 2;
                break;
            case android.content.res.Configuration.UI_MODE_NIGHT_YES:
                position = 1;
                break;
            case android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED:
                position = 0;
                break;
        }

        builder.setTitle("Modo oscuro")
                .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position = which;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position == 0) {
                            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        } else if (position == 2) {
                            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        } else if (position == 1) {
                            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing
                    }
                });

        builder.show();
    }

    public void openDialog() {
        aboutDialog dialog = new aboutDialog();
        dialog.show(this.requireActivity().getSupportFragmentManager(), "aboutDialog");
    }

    public void contactDialog() {

    }

}