package vivisystem.com.strict_morse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import vivisystem.com.strict_morse.tests.MorseUtility;
import vivisystem.com.test_jni.TestNative;

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
        mButtonTestLoadWeixin = (Button)findViewById(R.id.button_test_load_weixin);
        mButtonTestLoadWeixin.setOnClickListener(mButtonTestLoadWeixinOnClickListener);



        MorseKeyUtil.getKeyInfo(MorseKey.class);

        MorseKey tmpMorseKey = new MorseKey();
        MorseKeyUtil.getKeyInfo(tmpMorseKey.getClass());

        Log.d("Hughie", "" + MorseCharacter.getCharacters());
//        MorseCharacter.printCharacters();
        forClassLoader();
        Log.d("Hughie", "MainActivity.onCreate() called finished.");
    }

    private static final String[] writeLines = {
        "Line 1", "Line 2", "ABD AFFF", "SSSS", "测试中文 四大 ssa"
    };
    private static final String[] writeLines2 = {
            "2 Line 12", "2 Line 2", "2 ABD AFFF", "2 SSSS", "2 测试中文 四大 ssa"
    };
    private Button mButtonTestWrite;
    private View.OnClickListener mButtonTestWriteOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick");
            {
                File file = new File(MorseDef.PATH_APP_ROOT);
                if(!file.exists())
                {
                    Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick " + MorseDef.PATH_APP_ROOT + " 目录不存在，准备创建");
                    if(!file.mkdirs())
                    {
                        Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick " + MorseDef.PATH_APP_ROOT + " 目录创建失败，结束");
                        return;
                    }
                    Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick " + MorseDef.PATH_APP_ROOT + " 目录创建成功，继续下一步检查");

                }
                else if(!file.isDirectory())
                {
                    Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick " + MorseDef.PATH_APP_ROOT + " 不是个目录，结束");
                    return;
                }
                Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick " + MorseDef.PATH_APP_ROOT + " 是个目录，继续下一步检查");

            }

            {
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
                    MorseCharacter character = new MorseCharacter();
                    character.resetTextLines(writeLines);
                    character.resetTextLines2(writeLines2);

                    objectOutputStream.writeObject(character);
                    objectOutputStream.close();

                    Log.d("Hughie", "mButtonTestReadOnClickListener.onClick write end");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    Log.d("Hughie", "mButtonTestWriteOnClickListener.onClick IOException " + e.toString());
                }
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
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(MorseDef.PATH_STORE_ALPHABET));

                MorseCharacter morseCharacter = (MorseCharacter)objectInputStream.readObject();
                Log.d("Hughie", "mButtonTestReadOnClickListener.onClick read 1= " + morseCharacter);
                if(morseCharacter == null)
                {
                    Log.d("Hughie", "mButtonTestReadOnClickListener.onClick read 1 is NULL");
                    return;
                }
                MorseCharacter.printTextLines(morseCharacter.getTextLines());
                MorseCharacter.printTextLines(morseCharacter.getTextLines2());

//                morseCharacter.printInnerCharacters();
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


    private Button mButtonTestLoadWeixin;
    private View.OnClickListener mButtonTestLoadWeixinOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Toast.makeText(MainActivity.this, "Loading WeiXin", Toast.LENGTH_LONG).show();
            MorseUtility.callWeiXin(MainActivity.this);
            Log.d("Hughie", "mButtonTestLoadWeixinOnClickListener.onClick TestNative.init()=" + TestNative.init());
        }
    };


    private void forClassLoader()
    {
        int i = 0;
        ClassLoader classLoader = getClassLoader();
        if(classLoader != null)
        {
            Log.d("Hughie",  (++i) + " forClassLoader classLoader = " + classLoader.toString());

            while(classLoader.getParent() != null)
            {
                classLoader = classLoader.getParent();
                Log.d("Hughie",  (++i) + " forClassLoader classLoader.getParent() = " + classLoader.toString());
            }
        }


    }

}
