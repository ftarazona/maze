package graph;

public class MazeReadingException
	extends Exception	{
	
	private String filename;
	private String message;
	private int line;

	public MazeReadingException(String filename, int line, String message)	{
		this.filename = filename;
		this.message = message;
		this.line = line;
	}

	public void printMessage()	{
		System.out.println("File " + filename + ": line " + Integer.toString(line) + ": " + message);
	}
}
