package vivisystem.com.strict_morse;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hughie on 16/6/14.
 */
public class MorseCharacter implements Serializable
{
    public MorseCharacter()
    {
        mMorseCharacters = MorseAlphabet.values();
        mTextLines = new ArrayList<>();
        mTextLines2 = new ArrayList<>();
    }

    public static MorseAlphabet[] getCharacters()
    {
        return morseCharacters;
    }

    public static void printCharacters()
    {
        Log.d("Hughie", "***printCharacters starts***");
        for(MorseAlphabet alphabet : morseCharacters)
        {
            Log.d("Hughie", "   " + alphabet.ch + " = " + alphabet.code);
        }
        Log.d("Hughie", "***printCharacters ends***");
    }

    private static MorseAlphabet[] morseCharacters = MorseAlphabet.values();
    private MorseAlphabet[] mMorseCharacters;

    public void printInnerCharacters()
    {
        Log.d("Hughie", "***printInnerCharacters starts***");
        for(MorseAlphabet alphabet : mMorseCharacters)
        {
            Log.d("Hughie", "   " + alphabet.ch + " = " + alphabet.code);
        }
        Log.d("Hughie", "***printInnerCharacters ends***");
    }


    public static void printTextLines(List<String> values)
    {
        if(values == null)
        {
            Log.d("Hughie", "***printTextLines stopped because of null values***");
            return;
        }
        Log.d("Hughie", "***printTextLines starts***");
        int i = 0;
        for (String line : values)
        {
            Log.d("Hughie", "   " + (++i) + "=[" + line + "]");
        }
        Log.d("Hughie", "***printTextLines ends***");
    }

    public void resetTextLines(String[] from)
    {
        mTextLines.clear();
        for (String text: from)
            mTextLines.add(text);
    }
    public List<String> getTextLines()
    {
        return mTextLines;
    }
    private List<String> mTextLines;

    public void resetTextLines2(String[] from)
    {
        mTextLines2.clear();
        for (String text: from)
            mTextLines2.add(text);
    }
    public List<String> getTextLines2()
    {
        return mTextLines2;
    }
    private transient List<String> mTextLines2;
}
