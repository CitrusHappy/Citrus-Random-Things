package io.github.citrushappy.util;

public class CriticalException extends RuntimeException
{
    //Catchable, should crash the game if it shows up
    public CriticalException(Throwable t)
    {
        super(t);
    }

    public CriticalException(String string)
    {
        super(string);
    }
}