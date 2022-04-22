package com.example.admissionvido.UI.admissions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admissionvido.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdmissionsMethodActivity extends AppCompatActivity {


    String method1 = "điểm thi THPTQG", method2 = "đánh giá năng lực", method3 = "học bạ";
    TextView diemThi, danhGiaNangLuc, hocBa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admissions_method);

        diemThi = findViewById(R.id.diemXetTuyen);
        danhGiaNangLuc = findViewById(R.id.danhGiaNangLuc);
        hocBa = findViewById(R.id.hocBa);

        diemThi.setOnClickListener(view -> {
            String[] profile = {};
            List<String> testList = new ArrayList<>(Arrays.asList(profile));
            testList.add(method1);
            profile = testList.toArray(new String[0]);

            Intent intent = new Intent(AdmissionsMethodActivity.this, ScoreExamAdmissionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("method", profile);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        danhGiaNangLuc.setOnClickListener(view -> {
            String[] profile = {};
            List<String> testList = new ArrayList<>(Arrays.asList(profile));
            testList.add(method2);
            profile = testList.toArray(new String[0]);

            Intent intent = new Intent(AdmissionsMethodActivity.this, CompetencyAssessmentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("method", profile);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        hocBa.setOnClickListener(view -> {
            String[] profile = {};
            List<String> testList = new ArrayList<>(Arrays.asList(profile));
            testList.add(method3);
            profile = testList.toArray(new String[0]);

            Intent intent = new Intent(AdmissionsMethodActivity.this, SchoolProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("method", profile);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}