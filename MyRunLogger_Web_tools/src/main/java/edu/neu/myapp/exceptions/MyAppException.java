package edu.neu.myapp.exceptions;

public class MyAppException extends Exception
{
	public MyAppException(String message)
	{
		super(message);
	}
	
	public MyAppException(String message, Throwable cause)
	{
		super(message,cause);
	}
}