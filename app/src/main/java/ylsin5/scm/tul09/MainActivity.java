package ylsin5.scm.tul09;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    boolean gameLoop=true;
    private MyView MV;
    private Thread thread;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MV = new MyView(this);
        setContentView(MV);
        thread = new Thread() {
            public void run() {
                MV.update();
                handler.postDelayed(this, 100);
            }
        };

    }
    @Override
    protected void onStart(){

        super.onStart();
        //gameLoop=false;
    }
    @Override
    protected void onResume() {

        super.onResume();
        handler.removeCallbacks(thread);
        handler.postDelayed(thread, 0);
        Log.d("", "onResume");
        //gameLoop=true;
    }

    @Override
    protected void onPause() {

        super.onPause();
        //gameLoop=false;
        handler.removeCallbacks(thread);
        Log.d("", "onPause");
    }
    @Override
    protected void onStop() {

        super.onStop();
        //gameLoop=false;
    }
}
