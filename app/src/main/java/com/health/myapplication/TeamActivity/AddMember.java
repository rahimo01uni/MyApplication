package com.health.myapplication.TeamActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.health.myapplication.R;

public class AddMember extends AppCompatActivity {
EditText Email;
TextView Name;
Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);


        Email = findViewById(R.id.txt_memEmail);
        Name = findViewById(R.id.txt_memName);
        btnAdd = findViewById(R.id.btn_Addmem);

    }
}
