package com.example.admissionvido.Fragment.Subject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admissionvido.Adapters.NCAdapter;
import com.example.admissionvido.Models.NameCard;
import com.example.admissionvido.R;
import com.example.admissionvido.Scan.Capture;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class A1ClassFragment extends Fragment {

    View view;
    List<NameCard> mListNameCard = new ArrayList<>();
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_a1_class, container, false);
        return view;
    }
}