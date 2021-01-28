package ui;

public class UninitializedVariableException extends UIException	{

	public static final long serialVersionUID = 202101282153L;
	private final String str;

	public UninitializedVariableException(String str)	{
		this.str = str;
	}

	@Override
	public String getMessage()	{
		return String.format("Variable %s is not initialized.", str);
	}
}
