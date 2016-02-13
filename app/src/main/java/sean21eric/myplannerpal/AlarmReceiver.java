package sean21eric.myplannerpal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Sean on 2/11/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("onRecieve", "Alarm Recieved!");
        Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
    }

}
