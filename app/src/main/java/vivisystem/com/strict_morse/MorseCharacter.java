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
}
