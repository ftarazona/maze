package graphics;

import java.awt.*;
import javax.swing.*;

public class RemColButton extends JButton	{

	private final MazeApp app;

	public RemColButton(MazeApp app)	{
		super("Remove column");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
