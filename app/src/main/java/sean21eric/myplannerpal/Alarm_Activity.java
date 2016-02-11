package sean21eric.myplannerpal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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

public class Alarm_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    //vars
    ImageButton btn_PickDate;
    ImageButton btn_PickTime;
    EditText et_Date;
    EditText et_Time;
    EditText et_AlarmName;

    //fragment vars
    DialogFragment datePickerFragment = new DatePickerFragment();

    //layout vars
    LinearLayout ll_Root;

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
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        Toast.makeText(this, "Activity Date set " + month + " - " + day + " - " + year, Toast.LENGTH_LONG).show();
        et_Date.setText(month + " - " + day + " - " + year);

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        Toast.makeText(this, "Activity Time set " + hourOfDay+ " : " + minute, Toast.LENGTH_LONG).show();
        et_Time.setText(hourOfDay+ " : " + minute);
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

    }

    private void findByIds(){



        //vars
        btn_PickDate = (ImageButton)findViewById(R.id.ImageButton_Alarm_Date);
        btn_PickTime = (ImageButton)findViewById(R.id.ImageButton_Alarm_Time);
        et_Date = (EditText)findViewById(R.id.EditText_Alarm_Date);
        et_Time = (EditText)findViewById(R.id.EditText_Alarm_Time);
        et_AlarmName = (EditText)findViewById(R.id.EditText_Alarm_Name);

        //layout vars
        ll_Root = (LinearLayout)findViewById(R.id.LinearLayout_Alarm_Root);

    }

    private void initialize(){

        findByIds();
        setOnclickListeners();

    }






















}//close Alarm_Activity class
