package net.endhq.remoteentities.exceptions;

@SuppressWarnings("serial")
public class NotTameableException extends RuntimeException
{
	public NotTameableException()
	{
		super("Entity is not tameable.");
	}
}