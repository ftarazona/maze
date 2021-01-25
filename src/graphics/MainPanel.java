package graphics;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel	{

	private final FilePanel		filePanel;
	private final DijkstraPanel	dijkstraPanel;
	private final EditPanel		editPanel;

	public MainPanel(MazeApp app)	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		add(filePanel		= new FilePanel(app));
		add(dijkstraPanel	= new DijkstraPanel(app));
		add(editPanel		= new EditPanel(app));
	}
}
