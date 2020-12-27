package ui;

public interface CommandInterface	{

	public void execute(String[] args)
		throws InvalidArgumentsException, ExecutionException;
}
