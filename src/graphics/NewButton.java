package graphics;

import java.awt.*;
import javax.swing.*;

public class NewButton extends JButton	{

	private final MazeApp app;

	public NewButton(MazeApp app)	{
		super("New maze");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
