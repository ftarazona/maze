package graphics;

import java.awt.*;
import javax.swing.*;

public class FilePanel extends JPanel	{

	private final NewButton		newButton;
	private final OpenButton	openButton;
	private final SaveButton	saveButton;

	public FilePanel(MazeApp app)	{
		GridLayout lay = new GridLayout(2, 2);
		lay.setHgap(3);
		lay.setVgap(3);
		setLayout(lay);
	setBorder(BorderFactory.createTitledBorder("File"));

		add(newButton	= new NewButton(app));
		add(openButton	= new OpenButton(app));
		add(saveButton	= new SaveButton(app));
	}
}
