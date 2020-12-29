package ui;

public class UnknownBoxTypeException extends UIException	{

	private final String type;

	public UnknownBoxTypeException(String type)	{
		this.type = type;
	}

	public getMessage()	{ return "Unknown box type: " + type; }
}
