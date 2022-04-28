package com.example.admissionvido.UI.admissions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.admissionvido.R;

import java.util.Arrays;

public class CompetencyAssessmentActivity extends AppCompatActivity {

    String[] profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competency_assessment);

        Button next = findViewById(R.id.next);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            profile = (String[]) bundle.get("method");
            Log.e("here3!", Arrays.toString(profile));
        }

        next.setOnClickListener(view -> {
            Intent intent = new Intent(CompetencyAssessmentActivity.this, UserInforActivity.class);
            Bundle scoreBundle = new Bundle();
            scoreBundle.putSerializable("score", profile);
            startActivity(intent);
        });
    }
}