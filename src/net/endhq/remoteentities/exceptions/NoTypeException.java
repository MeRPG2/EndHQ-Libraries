package net.endhq.remoteentities.exceptions;

@SuppressWarnings("serial")
public class NoTypeException extends RuntimeException
{
	public NoTypeException()
	{
		super("No entity type specified");
	}
}