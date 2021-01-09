package ui;

public class ScriptLoadingException extends UIException	{

	public static final long serialVersionUID = 202101090316L;
	private final String msg;

	public ScriptLoadingException(String filename)	{
		msg = "Unable to load script " + filename + "\n";
	}

	public String getMessage()	{
		return msg;
	}
}
