package com.example.admissionvido.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admissionvido.R;
import com.example.admissionvido.UI.Spinner.CustomSpinner;

public class MethodOneFragment extends Fragment implements CustomSpinner.OnSpinnerEventsListener{

    View view;
    CustomSpinner ad_division;
    Integer number;
    String text = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_method_one, container, false);

        ad_division = view.findViewById(R.id.admission_division);
        ad_division.setDropDownVerticalOffset(110);
        ad_division.setSpinnerEventsListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.admissions_division, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad_division.setAdapter(adapter);
        //ad_division.getOnItemSelectedListener();
        ad_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        ad_division.setBackground(getResources().getDrawable(R.drawable.bg_spinner_up, getContext().getTheme()));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        ad_division.setBackground(getResources().getDrawable(R.drawable.bg_spinner_down, getContext().getTheme()));
    }
}