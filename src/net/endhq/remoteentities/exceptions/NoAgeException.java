package net.endhq.remoteentities.exceptions;

@SuppressWarnings("serial")
public class NoAgeException extends RuntimeException
{
	public NoAgeException()
	{
		super("Entity needs an age.");
	}
}