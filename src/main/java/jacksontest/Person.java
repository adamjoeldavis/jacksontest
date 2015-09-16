package jacksontest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person implements DataObject
{
	@JsonProperty("value")
	private String value_;

	@JsonProperty("deleted")
	private boolean deleted_ = false;

	@Override
	public String getValue()
	{
		return value_;
	}

	public void setValue(String value)
	{
		value_ = value;
	}

	public boolean isDeleted()
	{
		return deleted_;
	}

	public void setDeleted(boolean deleted)
	{
		deleted_ = deleted;
	}
	
	public String toString()
	{
		return "{value: \"" + value_ + "\", deleted: " + deleted_ + "}";
	}
}