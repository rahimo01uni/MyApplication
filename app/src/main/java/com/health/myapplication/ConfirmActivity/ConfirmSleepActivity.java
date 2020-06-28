package com.health.myapplication.ConfirmActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.emoji.text.EmojiCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import com.health.myapplication.R;
import com.health.myapplication.TextDrawable;


public class ConfirmSleepActivity extends AppCompatActivity {
Button btn_SConfirm;
Button btn_SCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sleep);

        btn_SConfirm = findViewById(R.id.btn_SConfirm);
        btn_SCancel = findViewById(R.id.btn_SCancel);


        EditText regular = (EditText) findViewById(R.id.txt_regular);
        regular.setCompoundDrawables(new TextDrawable(regular, "+61 "), null, new TextDrawable(regular, "\u2605"), null);

        TextView view = (TextView) findViewById(R.id.drawable_test);
        final TextDrawable textDrawable = new TextDrawable(view, "\u263A");
        textDrawable.setFillText(true);
        textDrawable.getPaint().setColor(Color.RED);
        view.setBackgroundDrawable(textDrawable);
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
