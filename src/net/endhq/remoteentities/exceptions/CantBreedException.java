package net.endhq.remoteentities.exceptions;

@SuppressWarnings("serial")
public class CantBreedException extends RuntimeException
{
	public CantBreedException()
	{
		super("Entity can't breed.");
	}
}