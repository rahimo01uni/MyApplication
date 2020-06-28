package com.health.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Progress.progressActivity;
import com.health.myapplication.Reminder.AddReminderActivity;
import com.health.myapplication.TeamActivity.TeamActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent=new Intent(MainActivity.this, AddReminderActivity.class);
                startActivity(intent);

            }
        });

        //Reminder
        final TextView reminderID = (TextView) findViewById(R.id.txt_reminder);
        reminderID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), AddReminderActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        //Team
        final TextView teamID = (TextView) findViewById(R.id.txt_team);
        teamID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), TeamActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M&& !Settings.canDrawOverlays(this)) {
            Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(myIntent, 5469);
        }


        //progress
        final TextView progressID = (TextView) findViewById(R.id.txt_progress);
        progressID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), progressActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 5469: {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(Settings.canDrawOverlays(this)) {
                        Toast.makeText(this,"Permission Granted!",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(this,"Permission Not Granted!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    return;
                }
            }
        }
    }

}
