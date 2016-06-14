package vivisystem.com.strict_morse;

import android.os.Environment;

/**
 * Created by hughie on 16/6/14.
 */
public class MorseDef
{
    public static final char CHAR_SLASH = '/';
    public static final String NAME_STRICT_MORSE = "StrictMorse";
    public static final String NAME_STORE_ALPHABET = "alphabet.sm";

    public static final String PATH_STORAGE = Environment.getExternalStorageDirectory().getPath();
    public static final String PATH_STRICT_MORSE = PATH_STORAGE + CHAR_SLASH + NAME_STRICT_MORSE;
    public static final String PATH_SLASH_STRICT_MORSE = PATH_STRICT_MORSE + CHAR_SLASH;

    public static final String PATH_STORE_ALPHABET = PATH_SLASH_STRICT_MORSE + NAME_STORE_ALPHABET;
}
