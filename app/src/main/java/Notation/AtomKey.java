package Notation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hughie on 16/6/12.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AtomKey
{
    enum AtomKeyType
    {
        Letter,
        Digit
    }

    AtomKeyType atomKeyType();

    char atomKeyValue();
}
