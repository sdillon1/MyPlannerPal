package sean21eric.myplannerpal;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class HomeScreen extends AppCompatActivity {

    //vars
    DatabaseHelper myHelper;
    Button btnInsert;
    EditText nameEditText;
    LinearLayout linearLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myHelper = new DatabaseHelper(this);

        //get the button and edit text
        btnInsert = (Button)findViewById(R.id.BTNInsert);
        nameEditText = (EditText)findViewById(R.id.EditText1);
        linearLayout1 = (LinearLayout)findViewById(R.id.LinearLayout1);

        addOnclickListener();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }//close onCreate()

    public void addOnclickListener(){
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = nameEditText.getText().toString();

                boolean isInserted = myHelper.insertEvent(s);

                if(isInserted == true){
                    Snackbar.make(linearLayout1, "SUCCESS! :)", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    Snackbar.make(linearLayout1, "FAILURE :(", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }


            }
        });
    }







    /*
     *  For the menus, not important currently
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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

}//close class
