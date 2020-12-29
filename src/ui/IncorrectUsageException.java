package ui;

public class IncorrectUsageException extends UIException	{

	private final int nArgsExp;
	private final int nArgsRec;

	public IncorrectUsageException(int exp, int rec)	{
		this.nArgsExp = exp;
		this.nArgsRec = rec;
	}

	public getMessage()	{ return "Expected " + String.valueOf(nArgsExp + " arguments but received " + String.valueOf(nArgsRec) + "."; }
}
