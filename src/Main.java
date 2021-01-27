import ui.*;

public class Main	{
	public static void main(String[] args)	{
		try	{
			GraphicalInterface ui = new GraphicalInterface();
			ui.run();
		} catch (Exception e)	{
			System.out.println(e.getMessage());
		}
	}
}
