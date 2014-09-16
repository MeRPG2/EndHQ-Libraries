package net.endhq.remoteentities.persistence;

public interface SerializableData
{
	/**
	 * Returns all the parameters that can be serialized
	 *
	 * @return Serializeable parameters
	 */
	public ParameterData[] getSerializableData();
}