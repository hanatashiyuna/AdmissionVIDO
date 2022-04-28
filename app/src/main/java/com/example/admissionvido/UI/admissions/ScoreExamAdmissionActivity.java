package com.example.admissionvido.UI.admissions;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admissionvido.Fragment.Subject.A1ClassFragment;
import com.example.admissionvido.Fragment.Subject.AClassFragment;
import com.example.admissionvido.Fragment.Subject.BClassFragment;
import com.example.admissionvido.Fragment.Subject.CClassFragment;
import com.example.admissionvido.R;
import com.example.admissionvido.UI.Spinner.CustomSpinner;

import java.util.Arrays;
import java.util.HashMap;

public class ScoreExamAdmissionActivity extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener{

    String[] profile;
    HashMap<String, String> hashMap = new HashMap<>();
    CustomSpinner ad_division;
    String text;
    TextView title_one, title_two, title_three;
    EditText score_subject_one, score_subject_two, score_subject_three;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_exam_admission);

        title_one = findViewById(R.id.title_subject_one);
        title_two = findViewById(R.id.title_subject_two);
        title_three = findViewById(R.id.title_subject_three);
        score_subject_one = findViewById(R.id.score_subject_one);
        score_subject_two = findViewById(R.id.score_subject_two);
        score_subject_three = findViewById(R.id.score_subject_three);

        next = findViewById(R.id.next);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            profile = (String[]) bundle.get("method");
            Log.e("here3!", Arrays.toString(profile));

            if(Arrays.toString(profile).equals("[điểm thi THPTQG]")){
                ad_division = findViewById(R.id.admission_division);
                ad_division.setDropDownVerticalOffset(110);
                ad_division.setSpinnerEventsListener(this);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ScoreExamAdmissionActivity.this, R.array.admissions_division, R.layout.support_simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ad_division.setAdapter(adapter);
                ad_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        text = adapterView.getItemAtPosition(i).toString();
                        if(text.equals("Khối A")){

                            //getSupportFragmentManager().beginTransaction().replace(R.id.get_class_fm, new AClassFragment(), "A Class").commit();
                            title_one.setText("Toán: ");
                            title_two.setText("Vật lý: ");
                            title_three.setText("Hóa học: ");
                        }else if(text.equals("Khối A1")){

                            //getSupportFragmentManager().beginTransaction().replace(R.id.get_class_fm, new A1ClassFragment(), "A1 Class").commit();
                            title_one.setText("Toán: ");
                            title_two.setText("Vật lý: ");
                            title_three.setText("Ngoại ngữ: ");
                        }else if(text.equals("Khối B")){

                            //getSupportFragmentManager().beginTransaction().replace(R.id.get_class_fm, new BClassFragment(), "B Class").commit();
                            title_one.setText("Toán: ");
                            title_two.setText("Hóa học: ");
                            title_three.setText("Sinh học: ");
                        }else if(text.equals("Khối C")){

                            title_one.setText("Ngữ văn: ");
                            title_two.setText("Lịch sử: ");
                            title_three.setText("Địa lý: ");
                            //getSupportFragmentManager().beginTransaction().replace(R.id.get_class_fm, new CClassFragment(), "C Class").commit();
                        }else if(text.equals("Khối D")){

                            title_one.setText("Ngữ văn: ");
                            title_two.setText("Toán: ");
                            title_three.setText("Ngoại ngữ: ");
                            //getSupportFragmentManager().beginTransaction().replace(R.id.get_class_fm, new A1ClassFragment(), "D Class").commit();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }
        next.setOnClickListener(view -> {
            Intent intent = new Intent(ScoreExamAdmissionActivity.this, UserInforActivity.class);
            Bundle nextBundle = new Bundle();
            nextBundle.putSerializable("score_subject", profile);
            startActivity(intent);
        });

    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        ad_division.setBackground(getResources().getDrawable(R.drawable.bg_spinner_up, getTheme()));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        ad_division.setBackground(getResources().getDrawable(R.drawable.bg_spinner_down, getTheme()));
    }
}