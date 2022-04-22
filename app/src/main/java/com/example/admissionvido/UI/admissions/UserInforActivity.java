package com.example.admissionvido.UI.admissions;


import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admissionvido.R;
import com.example.admissionvido.UI.Spinner.CustomSpinner;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class UserInforActivity extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener {

    TextView dateOfBirth;
    EditText birth;
    DatePickerDialog.OnDateSetListener dateSetListener;
    ImageView calendar_icon, font_side_identity, back_side_identity;
    CustomSpinner genderSpinner, areaSpinner, consultantsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor);

        dateOfBirth = findViewById(R.id.birth);
        birth = findViewById(R.id.ed_birth);
        calendar_icon = findViewById(R.id.img_calendar);
        genderSpinner = findViewById(R.id.gender);
        areaSpinner = findViewById(R.id.area);
        consultantsSpinner = findViewById(R.id.consultants);
        font_side_identity = findViewById(R.id.font_side_identity);
        back_side_identity = findViewById(R.id.back_side_identity);

        //calendar date of birth
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int moth = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        birth.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(UserInforActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, dateSetListener, year, moth, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        dateSetListener = (datePicker, year1, month, dayOfMoth) -> {
            month = month+1;
            String date = dayOfMoth +"/"+month+"/"+ year1;
            birth.setText(date);
        };
        calendar_icon.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(UserInforActivity.this, (datePicker, year12, month, day1) -> {
                month = month + 1;
                String date = day1 +"/"+month+"/"+ year12;
                birth.setText(date);
            }, year, moth, day);
            datePickerDialog.show();
        });

        //gender spinner
        genderSpinner.setDropDownVerticalOffset(110);
        genderSpinner.setSpinnerEventsListener(this);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(UserInforActivity.this, R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String text = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //area spinner
        areaSpinner.setDropDownVerticalOffset(110);
        areaSpinner.setSpinnerEventsListener(this);
        ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(UserInforActivity.this, R.array.area, R.layout.support_simple_spinner_dropdown_item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String text = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //consultants spinner
        consultantsSpinner.setDropDownVerticalOffset(110);
        consultantsSpinner.setSpinnerEventsListener(this);
        ArrayAdapter<CharSequence> consultantsAdapter = ArrayAdapter.createFromResource(UserInforActivity.this, R.array.consultants, R.layout.support_simple_spinner_dropdown_item);
        consultantsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consultantsSpinner.setAdapter(consultantsAdapter);
        consultantsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String text = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //handle identity
        font_side_identity.setOnClickListener(view -> requestPermission(font_side_identity));
        back_side_identity.setOnClickListener(view -> requestPermission(back_side_identity));
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        genderSpinner.setBackground(getResources().getDrawable(R.drawable.bg_simple_spinner_up, getTheme()));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        genderSpinner.setBackground(getResources().getDrawable(R.drawable.bg_simple_spinner_down, getTheme()));
    }

    public void requestPermission(ImageView imgIdentity){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker(imgIdentity);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(UserInforActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    public void openImagePicker(ImageView imgIdentity){
        TedBottomPicker.with(UserInforActivity.this)
                .show(uri -> {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        imgIdentity.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
    }
}