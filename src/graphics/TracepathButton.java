package graphics;

import java.awt.*;
import javax.swing.*;

public class TracepathButton extends JButton	{

	private final MazeApp app;

	public TracepathButton(MazeApp app)	{
		super("Trace Path");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
