package com.health.myapplication.ConfirmActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.health.myapplication.R;
import com.health.myapplication.TextDrawable;

public class ConfirmSympthomActivity extends AppCompatActivity {
    Button btn_SyConfirm;
    Button btn_SyCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sympthom);

        btn_SyConfirm = findViewById(R.id.btn_SyConfirm);


        EditText regular = (EditText) findViewById(R.id.txt_regular);
        regular.setCompoundDrawables(new TextDrawable(regular, "+61 "), null, new TextDrawable(regular, "\u2605"), null);

        EditText view = findViewById(R.id.notes);
        final TextDrawable textDrawable = new TextDrawable(view, "\u263A");
        textDrawable.setFillText(true);
        textDrawable.getPaint().setColor(Color.RED);

        CountDownTimer c = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textDrawable.setText(textDrawable.getText()+"\u263A");
            }

            @Override
            public void onFinish() {

            }
        };
        c.start();
    }
}
