package graphics;

import java.awt.*;
import javax.swing.*;

public class DijkstraPanel extends JPanel	{

	private final DijkstraButton	dijkstraButton;
	private final TracepathButton	tracepathButton;

	public DijkstraPanel(MazeApp app)	{
		GridLayout lay = new GridLayout(1, 2);
		lay.setHgap(3);
		lay.setVgap(3);
		setLayout(lay);
		setBorder(BorderFactory.createTitledBorder("Dijkstra"));

		add(dijkstraButton	= new DijkstraButton(app));
		add(tracepathButton	= new TracepathButton(app));
	}
}
