package ui;

import dijkstra.*;
import maze.*;

public class Parser	{

	protected final static int STRING	= 1;
	protected final static int INTEGER	= 2;

	public static int checkArguments(Object[] received, int[][] expected)	{
		int i = 0;
		int j = 0;
		boolean incorrect = false;

		for(i = 0; i < expected.length; i++)	{
			if(received.length < expected[i].length)	{
				continue;
			}

			for(j = 0; j < expected[i].length && !incorrect; j++)	{
				Object obj = received[j];
				switch(expected[i][j])	{
				case STRING:	incorrect = obj instanceof java.lang.String;
					break;
				case INTEGER: 
					try	{
						if (!(obj instanceof java.lang.Integer))	{
							received[j] = (Integer.valueOf((String)obj));
						}
					} catch (Exception e)	{ incorrect = true; }
					break;
				default:
					break;
				}

				if(!incorrect)	{ return i; }
			}
		}

		return -1;
	}
}
