package utils;

import java.util.Comparator;

import model.logic.YouTubeVideo;

public class ComparadorXViews implements Comparator<YouTubeVideo>
	{

			@Override
			public int compare(YouTubeVideo o1, YouTubeVideo o2) 
			{
				// TODO Auto-generated method stub
				int comparaciao = Integer.parseInt(o1.getViews())-Integer.parseInt(o2.getViews());
				if (comparaciao>0)
				{
					return 1;
				}
				else if (comparaciao<0)
				{
					return -1;
				}
				else
					return 0;
			}
		
	}


