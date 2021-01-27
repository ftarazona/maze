package graphics;

import java.awt.*;
import javax.swing.*;

public class OpenButton extends JButton	{

	private final MazeApp app;

	public OpenButton(MazeApp app)	{
		super("Open maze");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
