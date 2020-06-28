package com.health.myapplication.TeamActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.health.myapplication.R;

public class EditMemList extends AppCompatActivity {
EditText txt_EditmemEmail;
    EditText txt_EditmemName;
    Button btn_EditAddmem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mem_list);

        txt_EditmemEmail= findViewById(R.id.txt_EditmemEmail);
        txt_EditmemName= findViewById(R.id.txt_EditmemName);
        btn_EditAddmem= findViewById(R.id.btn_EditAddmem);
    }
}
