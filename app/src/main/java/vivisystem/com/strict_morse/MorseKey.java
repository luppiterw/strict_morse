package vivisystem.com.strict_morse;

import Notation.AtomKey;

/**
 * Created by hughie on 16/6/12.
 */
public class MorseKey
{
    @AtomKey(atomKeyType = AtomKey.AtomKeyType.Letter, atomKeyValue = 'A')
    private String morseKey;

    private String morseValue;

    public String getMorseKey()
    {
        return morseKey;
    }

    public void setMorseKey(String morseKey)
    {
        this.morseKey = morseKey;
    }

    public String getMorseValue()
    {
        return morseValue;
    }

    public void setMorseValue(String morseValue)
    {
        this.morseValue = morseValue;
    }
}
