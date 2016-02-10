package sean21eric.myplannerpal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class AddEvent extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{




    //vars
    DatabaseHelper myHelper;
    Button btnInsert;
    Button btnShowData;
    Button btnUpdate;
    Button btnDelete;
    Button btnDate;
    EditText nameEditText;
    EditText idEditText;
    ScrollView linearLayout1;
    DialogFragment newFragment = new DatePickerFragment();
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String TAG = "onCreate()";

        myHelper = new DatabaseHelper(this);

        //get the button and edit text
        btnInsert = (Button)findViewById(R.id.BTNInsert);
        btnShowData = (Button)findViewById(R.id.BTNShowData);
        btnUpdate = (Button)findViewById(R.id.BTNUpdate);
        btnDelete = (Button)findViewById(R.id.BTNDelete);
        btnDate = (Button)findViewById(R.id.BTNDate);
        nameEditText = (EditText)findViewById(R.id.EDITTEXT_NAME);
        idEditText = (EditText)findViewById(R.id.EditText_ID);
        datePicker = (DatePicker)findViewById(R.id.datePicker1);

        linearLayout1 = (ScrollView)findViewById(R.id.ScrollLayout1);

        addOnclickListeners();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "All rows deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                myHelper.deleteAllRows();
            }
        });

        Log.d(TAG, "Time to string: " + (datePicker.getMonth() + 1) + " " + datePicker.getDayOfMonth() + " " + datePicker.getYear());

    }//close onCreate()

    public void addOnclickListeners(){

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = nameEditText.getText().toString();
                String date = datePicker.getYear() + "-" + (datePicker.getMonth()) + "-" + datePicker.getDayOfMonth();


                boolean isInserted = myHelper.insertEvent(s, date);

                if(isInserted == true){
                    Snackbar.make(linearLayout1, "SUCCESS! :)", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    Snackbar.make(linearLayout1, "FAILURE :(", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                clearTExtFields();
                deselectEdittext();
            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = myHelper.getAllEvents();

                //check number of events returned
                if (cursor.getCount() == 0) {
                    //no data
                    showAlertDialog("ERROR", "No Data");
                    return;
                }
                //if gets here, there is data
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    //add data to the buffer
                    stringBuffer.append("ID: " + cursor.getString(0) + "\n");
                    stringBuffer.append("NAME: " + cursor.getString(1) + "\n");
                    stringBuffer.append("DATE: " + cursor.getString(2) + "\n\n");
                }

                //show the data in an alert dialog
                showAlertDialog("Data", stringBuffer.toString());
                deselectEdittext();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdated = myHelper.updateEvent(idEditText.getText().toString(), nameEditText.getText().toString(), getDate());

                if (isUpdated == true) {
                    Snackbar.make(linearLayout1, "UPDATE SUCCESS! :)", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Snackbar.make(linearLayout1, "UPDATE FAILURE! :(", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

                clearTExtFields();
                deselectEdittext();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int deletedRows = myHelper.deleteEvent(idEditText.getText().toString());

                if (deletedRows == 0) {
                    //no rows deleted
                    Snackbar.make(linearLayout1, "Delete Failure! :)", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    //rows were deleted
                    Snackbar.make(linearLayout1, "Delete SUCCESS! :)", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

                deselectEdittext();
                clearTExtFields();

            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(linearLayout1);
            }
        });

    }//close addOnclickListener()

    public void showAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void deselectEdittext(){
        //unselect all the edit texts after button click to get rid of blinking curser
        /*
        nameEditText.setSelected(false);
        nameEditText.setFocusable(false);
        nameEditText.setFocusable(true);
        idEditText.setSelected(false);
        idEditText.setFocusable(false);
        idEditText.setFocusable(true);
        */

        idEditText.clearFocus();
        nameEditText.clearFocus();
    }

    public void clearTExtFields(){
        nameEditText.setText("");
        idEditText.setText("");
    }

    public String getDate(){

        String date;
        date = datePicker.getYear() + "-" + (datePicker.getMonth()) + "-" + datePicker.getDayOfMonth();
        return date;
        //Log.d(TAG, "Time to string: " + (datePicker.getMonth() + 1) + " " + datePicker.getDayOfMonth() + " " + datePicker.getYear());


    }

    public void showDatePickerDialog(View v) {

        Bundle b = new Bundle();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        Toast.makeText(this, "Activity Date set " + month + " - " + day + " - " + year, Toast.LENGTH_LONG).show();



    }


























}
