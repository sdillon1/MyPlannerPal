package sean21eric.myplannerpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sean on 2/8/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /*
     *  This DatabaseHelper class will have methods to deal with our database interaction
     *  tutorial: https://www.youtube.com/watch?v=T0ClYrJukPA
     *
     */

    //vars
    public static final String DATABASE_NAME = "MyPlannerPal";
    public static final int DATABASE_VERSION = 1;
    //table1
    public static final String TABLE_1_NAME = "EVENT";
    public static final String TABLE_1_COL_1_NAME = "ID";
    public static final String TABLE_1_COL_2_NAME = "NAME";




    public DatabaseHelper(Context context) {
        //creates database in the super method
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_1_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1_NAME);
        onCreate(db);
    }

    //insert methods
    public boolean insertEvent(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_1_COL_2_NAME, name);

        long result = db.insert(TABLE_1_NAME, null, contentValues);

        if(result == -1){
            //insert failed
            return false;
        }else{
            return true;
        }

    }


}
