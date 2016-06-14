package vivisystem.com.strict_morse;

/**
 * Created by hughie on 16/6/12.
 */
public class MorseList
{
    public static MorsePair morsePairs[] = {
    };

    enum Morse
    {



    }


    public class MorsePair
    {
        MorsePair(Character character, String symbol)
        {
            this.character = character;
            this.symbol = symbol;
        }



        Character character;
        String symbol;
    }
}
