package vivisystem.com.strict_morse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonTestWrite = (Button)findViewById(R.id.button_test_write);
        mButtonTestWrite.setOnClickListener(mButtonTestWriteOnClickListener);
        mButtonTestRead = (Button)findViewById(R.id.button_test_read);
        mButtonTestRead.setOnClickListener(mButtonTestReadOnClickListener);



        MorseKeyUtil.getKeyInfo(MorseKey.class);

        MorseKey tmpMorseKey = new MorseKey();
        MorseKeyUtil.getKeyInfo(tmpMorseKey.getClass());

        Log.d("Hughie", "" + MorseCharacter.getCharacters());
//        MorseCharacter.printCharacters();

        Log.d("Hughie", "MainActivity.onCreate() called finished.");
    }

    private Button mButtonTestWrite;
    private View.OnClickListener mButtonTestWriteOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick");

            File file = new File(MorseDef.PATH_STORE_ALPHABET);
            if(!file.exists())
            {
                Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick " + MorseDef.PATH_STORE_ALPHABET + " 不存在，准备创建");
                try
                {
                    if (file.createNewFile())
                        Log.d("Hughie", "   " + MorseDef.PATH_STORE_ALPHABET + " 创建成功");
                    else
                        Log.d("Hughie", "   " + MorseDef.PATH_STORE_ALPHABET + " 创建失败");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    Log.d("Hughie", "   IOException " + e.toString());
                    return;
                }
            }
            else if(!file.isFile())
            {
                Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick " + MorseDef.PATH_STORE_ALPHABET + " is Not A File !!");
                return;
            }

                try
            {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(MorseDef.PATH_STORE_ALPHABET));

                objectOutputStream.writeObject(new MorseCharacter());
                objectOutputStream.close();

                Log.d("Hughie", "mButtonTestReadOnClickListener.onClick write end");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick IOException " + e.toString());
            }

        }
    };

    private Button mButtonTestRead;
    private View.OnClickListener mButtonTestReadOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Log.d("Hughie", "mButtonTestReadOnClickListener.onClick");


            try
            {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(MorseDef.PATH_STRICT_MORSE));

                MorseCharacter morseCharacter = (MorseCharacter)objectInputStream.readObject();
                Log.d("Hughie", "mButtonTestReadOnClickListener.onClick read = " + morseCharacter);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Log.d("Hughie", "mButtonTestReadOnClickListener.onClick IOException " + e.toString());
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
                Log.d("Hughie", "mButtonTestReadOnClickListener.onClick ClassNotFoundException " + e.toString());
            }
        }
    };
}
