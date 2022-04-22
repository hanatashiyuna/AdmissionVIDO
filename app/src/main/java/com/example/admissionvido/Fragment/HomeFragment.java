package com.example.admissionvido.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admissionvido.Adapters.UniversitiesAdapter;
import com.example.admissionvido.Models.University;
import com.example.admissionvido.R;
import com.example.admissionvido.UI.admissions.ProfileActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {

    View view;
    List<University> universityList;
    public String[] profile = {};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView universityRec = view.findViewById(R.id.rec_university);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        universityRec.setLayoutManager(gridLayoutManager);
        getListUniversity();

        UniversitiesAdapter universitiesAdapter = new UniversitiesAdapter(getActivity(), universityList, this::onClickGoToMajors);
        universityRec.setAdapter(universitiesAdapter);
        return view;
    }

    public void getListUniversity(){
        universityList = new ArrayList<>();
        universityList.add(new University(R.drawable.logo, "University of Economics Ho Chi Minh City"));
        universityList.add(new University(R.drawable.logo, "University Of Transport And Communications"));
        universityList.add(new University(R.drawable.logo, "University Of Transport And Communications"));
        universityList.add(new University(R.drawable.logo, "University Of Transport And Communications"));
        universityList.add(new University(R.drawable.logo, "University Of Transport And Communications"));
    }

    private void onClickGoToMajors(University university) {
        List<String> testList = new ArrayList<>(Arrays.asList(profile));
        testList.add(String.valueOf(university.getUniversity()));
        profile = testList.toArray(new String[0]);
        Log.e("here!", Arrays.toString(profile) + "====================================================");

        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("profile", profile);
        bundle.putSerializable("university", university);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}