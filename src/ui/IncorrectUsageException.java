package ui;

public class IncorrectUsageException extends UIException	{
	
	public static final long serialVersionUID = 202012301425L;
	private final int nArgsExp;
	private final int nArgsRec;

	public IncorrectUsageException(int exp, int rec)	{
		this.nArgsExp = exp;
		this.nArgsRec = rec;
	}

	public String getMessage()	{ return "Expected " + String.valueOf(nArgsExp) + " arguments but received " + String.valueOf(nArgsRec) + "."; }
}
