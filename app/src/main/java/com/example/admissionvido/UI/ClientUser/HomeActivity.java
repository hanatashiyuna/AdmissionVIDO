package com.example.admissionvido.UI.ClientUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admissionvido.Fragment.HomeFragment;
import com.example.admissionvido.Fragment.ProfileFragment;
import com.example.admissionvido.R;
import com.example.admissionvido.Scan.Capture;
import com.example.admissionvido.UI.admissions.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeActivity extends AppCompatActivity {

    public String getValueUser = "";
    String[] identityCard;
    String qrcode;
    TextView nameIdentity, numberIC, birthIC, genderIC, originIC, dateInitIC;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FrameLayout contentLayout = findViewById(R.id.contentPanel);
        LinearLayout nameCard = findViewById(R.id.nameCard);
        nameIdentity = findViewById(R.id.nameIC);
        numberIC = findViewById(R.id.number_identity);
        birthIC = findViewById(R.id.date_of_birth);
        genderIC = findViewById(R.id.gender);
        originIC = findViewById(R.id.place_of_origin);
        dateInitIC = findViewById(R.id.date_of_init);
        ImageView scan = findViewById(R.id.scan);

        scan.setOnClickListener(view -> {
            //khoi tao intent
            IntentIntegrator intentIntegrator = new IntentIntegrator(HomeActivity.this);
            //Set prompt text
            intentIntegrator.setPrompt("Để có đèn flash, hãy sử dụng phím tăng âm lượng");
            //Set beep
            intentIntegrator.setBeepEnabled(true);
            //locked orientation
            intentIntegrator.setOrientationLocked(true);
            //set capture activity
            intentIntegrator.setCaptureActivity(Capture.class);
            //initiate scan
            intentIntegrator.initiateScan();

        });

        FloatingActionButton fab = findViewById(R.id.fab);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setBackground(null);

        fab.setOnClickListener(view ->
            getSupportFragmentManager().beginTransaction().replace(R.id.contentPanel, new HomeFragment()).commit()
        );

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentPanel, new HomeFragment()).commit();
                    break;
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentPanel, new ProfileFragment()).commit();
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check condition
        if(intentResult.getContents() != null){
            /*
             * khi content = null: khoi tao alert dialog
             */
            getValueUser = intentResult.getContents();

            qrcode = getValueUser.replace("|", "_");
            identityCard = qrcode.split("_");
            if(!getValueUser.equals("")){
                nameIdentity.setText(identityCard[2]);
                numberIC.setText(identityCard[0]);
                birthIC.setText(identityCard[3]);
                genderIC.setText(identityCard[4]);
                originIC.setText(identityCard[5]);
                dateInitIC.setText(identityCard[6]);

            }

        }else{
            /*
             * khi content ko null: in ra 1 ket qua
             */
            Toast.makeText(HomeActivity.this, "Bạn vẫn chưa scan thứ gì...", Toast.LENGTH_SHORT).show();
        }

    }


}