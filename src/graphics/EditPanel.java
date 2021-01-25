package graphics;

import java.awt.*;
import javax.swing.*;

public class EditPanel extends JPanel	{

	private final AddRowButton	addrowButton;
	private final AddColButton	addcolButton;
	private final AddBoxButton	addboxButton;
	private final AddFlagButton	addflagButton;
	private final RemRowButton	remrowButton;
	private final RemColButton	remcolButton;
	private final RemBoxButton	remboxButton;
	private final RemFlagButton	remflagButton;

	public EditPanel(MazeApp app)	{
		GridLayout lay = new GridLayout(4, 2);
		lay.setHgap(3);
		lay.setVgap(3);
		setLayout(lay);
		setBorder(BorderFactory.createTitledBorder("Edit"));

		add(addrowButton	= new AddRowButton(app));
		add(remrowButton	= new RemRowButton(app));
		add(addcolButton	= new AddColButton(app));
		add(remcolButton	= new RemColButton(app));
		add(addboxButton	= new AddBoxButton(app));
		add(remboxButton	= new RemBoxButton(app));
		add(addflagButton	= new AddFlagButton(app));
		add(remflagButton	= new RemFlagButton(app));
	}
}
