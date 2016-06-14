package vivisystem.com.strict_morse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Hughie", "MainActivity.onCreate() called finished.");

        MorseKeyUtil.getKeyInfo(MorseKey.class);

        MorseKey tmpMorseKey = new MorseKey();
        MorseKeyUtil.getKeyInfo(tmpMorseKey.getClass());


    }
}
