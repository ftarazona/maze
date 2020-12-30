package fileops;

public class ReadingException extends Exception	{

	public static final long serialVersionUID = 202012301431L;
	private char unexpected;
	private String type;
	private String expected;

	public ReadingException(char unexpected, String type, String expected)	{
		this.unexpected = unexpected;
		this.type = type;
		this.expected = expected;
	}

	public char getUnexpected()	{
		return unexpected;
	}

	public String getMessage()	{
		return String.format("Unexpected value %d for %s, expecting %s", unexpected, type, expected);
	}
}
