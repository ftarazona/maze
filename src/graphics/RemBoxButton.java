package graphics;

import java.awt.*;
import javax.swing.*;

public class RemBoxButton extends JButton	{

	private final MazeApp app;

	public RemBoxButton(MazeApp app)	{
		super("Remove box");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
