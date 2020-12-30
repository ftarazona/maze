package ui;

public class UnknownBoxTypeException extends UIException	{

	public static final long serialVersionUID = 202012301427L;
	private final String type;

	public UnknownBoxTypeException(String type)	{
		this.type = type;
	}

	public String getMessage()	{ return "Unknown box type: " + type; }
}
