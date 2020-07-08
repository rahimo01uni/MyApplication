package com.health.myapplication.ConfirmActivity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import com.health.myapplication.R;

public class ConfirmMedicationActivity extends AppCompatActivity  {

TextView txt_MCdesc;
Button btn_MCancel;
Button btn_MConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_medication);



        btn_MConfirm = findViewById(R.id.btn_MConfirm);


    }

}
