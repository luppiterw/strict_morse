package vivisystem.com.strict_morse;

import android.os.Environment;

/**
 * Created by hughie on 16/6/14.
 */
public class MorseDef
{
    public static final char CHAR_SLASH = '/';
    public static final String NAME_STRICT_MORSE = "StrictMorse";
    public static final String NAME_STORE_ALPHABET = "alphabet.txt";

    public static final String PATH_STORAGE = Environment.getExternalStorageDirectory().getPath();
    public static final String PATH_APP_ROOT = PATH_STORAGE + CHAR_SLASH + NAME_STRICT_MORSE;
    public static final String PATH_SLASH_APP_ROOT = PATH_APP_ROOT + CHAR_SLASH;

    public static final String PATH_STORE_ALPHABET = PATH_SLASH_APP_ROOT + NAME_STORE_ALPHABET;
}
