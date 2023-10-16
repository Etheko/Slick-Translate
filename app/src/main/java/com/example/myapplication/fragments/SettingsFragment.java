package com.example.myapplication.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.aboutDialog;

public class SettingsFragment extends Fragment {

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void aboutButtonPressed(View view) {
        openDialog();
    }

    public void openDialog(){
        aboutDialog dialog = new aboutDialog();
        dialog.show(this.requireActivity().getSupportFragmentManager(), "aboutDialog");
    }

}