package vivisystem.com.strict_morse;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by hughie on 16/6/14.
 */
public class MorseCharacter implements Serializable
{
    public MorseCharacter()
    {

    }

    public static MorseAlphabet[] getCharacters()
    {
        return mMorseCharacters;
    }

    public static void printCharacters()
    {
        Log.d("Hughie", "***printCharacters starts***");
        for(MorseAlphabet alphabet : mMorseCharacters)
        {
            Log.d("Hughie", "   " + alphabet.ch + " = " + alphabet.code);
        }
        Log.d("Hughie", "***printCharacters ends***");
    }

    private static MorseAlphabet[] mMorseCharacters = MorseAlphabet.values();
}
