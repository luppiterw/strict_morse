package vivisystem.com.i5cnc.strict_morse;

import android.util.Log;

import java.lang.reflect.*;

import Notation.AtomKey;

/**
 * Created by hughie on 16/6/12.
 */
public class MorseKeyUtil
{
    public static void getKeyInfo(Class<?> clazz)
    {
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields)
        {
            if (field.isAnnotationPresent(AtomKey.class))
            {
                AtomKey atomKey = field.getAnnotation(AtomKey.class);
                Log.d("Hughie", "atomKey.atomKeyValue()=\'" + atomKey.atomKeyValue() + "\' is a " + atomKey.atomKeyType() +
                " " + atomKey.toString());

            }
        }
    }
}
