package ui;

public interface CommandInterface	{

	public void run(String[] args)
		throws UIException;

	public String usage();
	public String description();
}
