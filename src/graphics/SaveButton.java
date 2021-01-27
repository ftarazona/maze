package graphics;

import java.awt.*;
import javax.swing.*;

public class SaveButton extends JButton	{

	private final MazeApp app;

	public SaveButton(MazeApp app)	{
		super("Save maze");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
