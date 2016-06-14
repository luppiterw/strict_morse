package vivisystem.com.strict_morse;

/**
 * Created by hughie on 16/6/12.
 */
public enum MorseAlphabet
{
    A('A', ".-"),
    B('B', "-..."),
    C('C', "-.-."),
    D('D', "-.."),
    E('E', "."),
    F('F', "..-."),
    G('G', "--."),
    H('H', "...."),
    I('I', ".."),
    J('J', ".---"),
    K('K', "-.-"),
    L('L', ".-.."),
    M('M', "--"),
    N('N', "-."),
    O('O', "---"),
    P('P', ".--."),
    Q('Q', "--.-"),
    R('R', ".-."),
    S('S', "..."),
    T('T', "-"),
    U('U', "..-"),
    V('V', "...-"),
    W('W', ".--"),
    X('X', "-..-"),
    Y('Y', "-.--"),
    Z('Z', "--.."),
    Num0('0', "-----"),
    Num1('1', ".----"),
    Num2('2', "..---"),
    Num3('3', "...--"),
    Num4('4', "....-"),
    Num5('5', "....."),
    Num6('6', "-...."),
    Num7('7', "--..."),
    Num8('8', "---.."),
    Num9('9', "----.");


    MorseAlphabet(Character ch, String code)
    {
        this.ch = ch;
        this.code = code;
    }

    Character ch;
    String code;
}
