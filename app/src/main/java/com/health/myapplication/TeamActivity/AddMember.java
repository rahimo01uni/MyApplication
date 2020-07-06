package com.health.myapplication.TeamActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.health.myapplication.Database.GroupDbHelper;
import com.health.myapplication.R;

public class AddMember extends AppCompatActivity {
EditText Email;
TextView Name;
Button btnAdd;
GroupDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
  db=new GroupDbHelper(this);

        Email = findViewById(R.id.txt_memEmail);
        Name = findViewById(R.id.txt_memName);
        btnAdd = findViewById(R.id.btn_Addmem);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertMember(Email.getText().toString(),Name.getText().toString());

                Toast.makeText(AddMember.this,"Contact inserted",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
