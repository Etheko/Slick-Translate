package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.HistorialAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TranslatorData;

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

        HistorialAdapter wordsAdapter = new HistorialAdapter(this.requireActivity(), 0, Ejemplo);
        List.setAdapter(wordsAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
