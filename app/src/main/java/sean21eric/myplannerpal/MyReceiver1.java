package sean21eric.myplannerpal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {
    //vars

    public MyReceiver1() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d("onRecieve", "Alarm Recieved!");
        Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();

        MediaPlayer mp = MediaPlayer.create(context, R.raw.sound_missile_lock);
        mp.start();

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
