package com.health.myapplication.Overview;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.health.myapplication.Database.SleepDbHelper;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.Reminder;
import com.health.myapplication.UnlockReceiver;
import com.health.myapplication.WakeUpReceiver;
import com.nex3z.notificationbadge.NotificationBadge;
import com.txusballesteros.bubbles.BubblesManager;

import java.util.Calendar;

public class AddLogSleep extends AppCompatActivity {
 String sleep_time,wake_upTime;
 TextView start_time,end_time;
 Button save,ask;
SleepDbHelper db;
   Calendar sleep,wake;
    private BubblesManager bubblesManager;
    private NotificationBadge mBadge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sleep);
        db=new SleepDbHelper(this);
        start_time=(TextView)findViewById(R.id.txt_StartTimeS);
        end_time=(TextView)findViewById(R.id.txt_EndTimeS);
        save=(Button)findViewById(R.id.btn_saveS);



        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddLogSleep.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        sleep = Calendar.getInstance();
                        String hours=""+hourOfDay,minutess=""+minutes;
                        if(hourOfDay==0)hours="00";
                        if(minutes==0)minutess="00";
                        if(hours.length()==1) hours="0"+hours;
                        if(minutess.length()==1)minutess="0"+minutess;
                        start_time.setText(hours+":"+minutess);
                         sleep = Calendar.getInstance();
                         sleep.set(sleep.get(Calendar.YEAR), sleep.get(Calendar.MONTH), sleep.get(Calendar.DATE),hourOfDay,minutes,0);
                        if(sleep.getTimeInMillis()<=Calendar.getInstance().getTimeInMillis())
                        {
                            sleep.set(sleep.get(Calendar.YEAR), sleep.get(Calendar.MONTH), sleep.get(Calendar.DATE)+1,hourOfDay,minutes,0);

                        }


                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }
        });
        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddLogSleep.this, R.style.MyDatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String hours=""+hourOfDay,minutess=""+minutes;
                        if(hourOfDay==0)hours="00";
                        if(minutes==0)minutess="00";
                        if(hours.length()==1) hours="0"+hours;
                        if(minutess.length()==1)minutess="0"+minutess;
                        end_time.setText(hours+":"+minutess);
                        wake=Calendar.getInstance();
                        wake = Calendar.getInstance();

                        wake.set(wake.get(Calendar.YEAR), wake.get(Calendar.MONTH), wake.get(Calendar.DATE),hourOfDay,minutes,0);
                        if(wake.getTimeInMillis()<=Calendar.getInstance().getTimeInMillis())
                        {
                            wake.set(wake.get(Calendar.YEAR), wake.get(Calendar.MONTH), wake.get(Calendar.DATE)+1,hourOfDay,minutes,0);

                        }
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
               // calendar.setTimeInMillis(Long.parseLong(x));
            //    Log.d("Alim1",""+calendar.getTimeInMillis()+ " "+calendar.getTime().getHours()+calendar.getTime().getMinutes());

                if(wake!=null && sleep!=null){
                int id= db.insertSleepReminder(""+sleep.getTimeInMillis(),""+wake.getTimeInMillis());
               Intent intent = new Intent(AddLogSleep.this, UnlockReceiver.class);
               intent.putExtra("wake_up",""+wake.getTimeInMillis());
               intent.putExtra("sleep",""+sleep.getTimeInMillis());
               intent.putExtra("id",""+id );

                PendingIntent intent1 = PendingIntent.getBroadcast(AddLogSleep.this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC, sleep.getTimeInMillis(),intent1);

                    intent = new Intent(AddLogSleep.this, WakeUpReceiver.class);
                    intent.putExtra("wake_up",""+wake.getTimeInMillis());
                    intent.putExtra("sleep",""+sleep.getTimeInMillis());
                    intent.putExtra("id",""+id);

                    intent1 = PendingIntent.getBroadcast(AddLogSleep.this,2,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                 alarmManager.setExact(AlarmManager.RTC, wake.getTimeInMillis(),intent1);

                Intent intent2 = new Intent(AddLogSleep.this,Reminder.class);
                startActivity(intent2);
                finish();} else
           {
               Toast.makeText(AddLogSleep.this,"Please,Enter Sleep and Wake Up Time!",Toast.LENGTH_SHORT).show();

           }


            }
        });
        //go to AddReminder Activity
        Button btnReminder = findViewById(R.id.btn_medicationS);
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLogSleep.this, AddLogMed.class);
                startActivity(intent);
            }
        });

        //go to AddLogMood Activity
        Button btnMood = findViewById(R.id.btn_symS);
        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
