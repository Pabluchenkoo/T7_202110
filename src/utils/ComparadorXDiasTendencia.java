package utils;

import java.text.ParseException;
import java.util.Comparator;

import model.logic.YouTubeVideo;

public class ComparadorXDiasTendencia implements Comparator<YouTubeVideo> 
{
	@Override
	public int compare(YouTubeVideo o1, YouTubeVideo o2) 
	{
		// TODO Auto-generated method stub
		int comparaciao;
		try {
			comparaciao = o1.diasEnTendencia()-o2.diasEnTendencia();
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 100;
	
	}
}
