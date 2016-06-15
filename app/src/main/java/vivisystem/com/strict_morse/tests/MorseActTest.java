package vivisystem.com.strict_morse.tests;

import java.lang.reflect.Constructor;

/**
 * Created by hughie on 16/6/15.
 */
public class MorseActTest
{
    public static Object getObject(String className)
    {
        Object object = null;
        try
        {
            Class c = Class.forName(className);
            if(c != null)
            {
//                Constructor constructor = c.getDeclaredConstructor(new Class)
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
