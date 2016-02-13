package sean21eric.myplannerpal;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    //vars
    ImageButton btn_PickDate;
    ImageButton btn_PickTime;
    ImageButton btn_SetAlarm;
    ImageButton btn_CancelAlarm;
    EditText et_Date;
    EditText et_Time;
    EditText et_AlarmName;

    //fragment vars
    DialogFragment datePickerFragment = new DatePickerFragment();
    DialogFragment timePickerFragment = new TimePickerFragment();

    //layout vars
    LinearLayout ll_Root;

    //for alarms
    AlarmManager alarmManager;

    //time
    int hour, minute, year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialize activity, find by ids and set onclick listeners
        initialize();

    }//onCreate()











    //date fragment dialog functions
    public void showDatePickerDialog(View v) {

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void showTimePickerDialog(View v) {

        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        Toast.makeText(this, "Activity Date set " + month + " - " + day + " - " + year, Toast.LENGTH_LONG).show();
        et_Date.setText(month + " - " + day + " - " + year);

        this.year = year;
        this.month = month;
        this.day = day;

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        Toast.makeText(this, "Activity Time set " + hourOfDay+ " : " + minute, Toast.LENGTH_LONG).show();
        et_Time.setText(hourOfDay + " : " + minute);

        this.hour = hourOfDay;
        this.minute = minute;
    }

    private void setOnclickListeners(){
        //set the onclick listeners

        btn_PickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(ll_Root);
            }
        });

        btn_PickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(ll_Root);
            }
        });

        btn_SetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        btn_CancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    private void findByIds(){



        //vars
        btn_PickDate = (ImageButton)findViewById(R.id.ImageButton_Alarm_Date);
        btn_PickTime = (ImageButton)findViewById(R.id.ImageButton_Alarm_Time);
        btn_SetAlarm = (ImageButton)findViewById(R.id.ImageButton_Alarm_SetAlarm);
        btn_CancelAlarm = (ImageButton)findViewById(R.id.ImangButton_Alarm_Cancel);
        et_Date = (EditText)findViewById(R.id.EditText_Alarm_Date);
        et_Time = (EditText)findViewById(R.id.EditText_Alarm_Time);
        et_AlarmName = (EditText)findViewById(R.id.EditText_Alarm_Name);

        //layout vars
        ll_Root = (LinearLayout)findViewById(R.id.LinearLayout_Alarm_Root);

    }

    private void initialize(){

        findByIds();
        setOnclickListeners();

        alarmManager =  (AlarmManager)getSystemService(Context.ALARM_SERVICE);

    }

    private void setAlarm(){


       Calendar calendar = Calendar.getInstance();
       //calendar.setTimeInMillis(System.currentTimeMillis() + 5000);

       calendar.set(year,month, day, hour, minute, 0);


        Intent intent = new Intent(getBaseContext(), MyReceiver1.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
        // alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , pendingIntent);

        Snackbar.make(ll_Root, "Alarm set for: " + hour + " : " + minute + " : " + 0, Snackbar.LENGTH_LONG).setAction("Action", null).show();



        /*
        AlarmManager alarmMgr;
         PendingIntent alarmIntent;

        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

// Set the alarm to start at 8:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 11);

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 1, alarmIntent);

*/

        //Snackbar.make(ll_Root, "Alarm set for: 1 minute", Snackbar.LENGTH_LONG).setAction("Action", null).show();




    }

    private void cancelAlarm(){

        Intent intent = new Intent(getBaseContext(), MyReceiver1.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);

        if(alarmManager != null){
            alarmManager.cancel(pendingIntent);
            Snackbar.make(ll_Root, "Alarm cancelled", Snackbar.LENGTH_LONG).setAction("Action", null).show();

        }else{
            Snackbar.make(ll_Root, "Nothing to cancell", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }


    }






















}//close Alarm_Activity class
