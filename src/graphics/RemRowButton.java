package graphics;

import java.awt.*;
import javax.swing.*;

public class RemRowButton extends JButton	{

	private final MazeApp app;

	public RemRowButton(MazeApp app)	{
		super("Remove row");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
