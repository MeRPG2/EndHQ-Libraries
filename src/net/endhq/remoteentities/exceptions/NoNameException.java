package net.endhq.remoteentities.exceptions;

@SuppressWarnings("serial")
public class NoNameException extends RuntimeException
{
	public NoNameException(String message)
	{
		super(message);
	}
}