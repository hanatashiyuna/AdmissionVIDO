package com.example.admissionvido.UI.ClientUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admissionvido.Adapters.NCAdapter;
import com.example.admissionvido.Fragment.HomeFragment;
import com.example.admissionvido.Fragment.ProfileFragment;
import com.example.admissionvido.Models.NameCard;
import com.example.admissionvido.R;
import com.example.admissionvido.Scan.Capture;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeActivity extends AppCompatActivity {

    public String getValueUser = "";
    String[] identityCard;
    String qrcode;
    TextView nameIdentity, nameUser;
    private ViewPager2 mViewPager2;
    CircleIndicator3 mCircleIndicator3;
    List<NameCard> mListNameCard = new ArrayList<>();
    NCAdapter ncAdapter;
    LinearLayout nameCardDemo;
    FrameLayout contentPanel;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameUser = findViewById(R.id.name_user);
        nameCardDemo = findViewById(R.id.nameCardDemo);
        nameIdentity = findViewById(R.id.nameIC);
        contentPanel = findViewById(R.id.contentPanel);
        LinearLayout input = findViewById(R.id.inputMethod);
        LinearLayout scan = findViewById(R.id.scanMethod);
        mViewPager2 = findViewById(R.id.view_pager2);
        mCircleIndicator3 = findViewById(R.id.circle_indicator);

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
        if(intentResult.getContents() != null){
            getValueUser = intentResult.getContents();
            qrcode = getValueUser.replace("|", "_");
            identityCard = qrcode.split("_");
            if(!getValueUser.equals("")){
                //set title cho name card
                nameUser.setText(identityCard[2]);
                //set du lieu cho name card
                mListNameCard.add(new NameCard("CCCD: " + identityCard[0], "Ngày Sinh: " + identityCard[3], "Giới Tính: " + identityCard[4], "Địa Chỉ: " + identityCard[5], "", "", "Tên: " + identityCard[2]));
                ncAdapter = new NCAdapter(HomeActivity.this, mListNameCard);
                mViewPager2.setAdapter(ncAdapter);
                mCircleIndicator3.setViewPager(mViewPager2);

            }
            //xóa view nếu có hồ sơ
            if(nameCardDemo.getVisibility() != View.GONE){
                nameCardDemo.setVisibility(View.GONE);
            }

        }else{
            Toast.makeText(HomeActivity.this, "Bạn vẫn chưa scan thứ gì...", Toast.LENGTH_SHORT).show();
        }

    }
}