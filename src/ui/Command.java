package ui;

public class Command
	implements CommandInterface	{

	protected Maze maze;

	public Command()	{}

	public void setMaze(Maze maze)	{ this.maze = maze; }

	private abstract boolean parse(String[] args);
	public abstract void execute(String[] args)	
		throws InvalidArgumentsException, ExecutionException;
}
