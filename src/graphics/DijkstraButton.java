package graphics;

import java.awt.*;
import javax.swing.*;

public class DijkstraButton extends JButton	{

	private final MazeApp app;

	public DijkstraButton(MazeApp app)	{
		super("Run Dijkstra");
		this.app = app;
		setPreferredSize(new Dimension(120, 20));
	}
}
