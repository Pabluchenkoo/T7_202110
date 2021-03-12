package model.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class YouTubeVideo implements Comparable<YouTubeVideo> 
{
	

	private String videoID;
	
	private String trendingDate;
	
	private String title;
	
	private String channelTitle;
	
	private String categoryID;
	
	private String publishTime;
	
	private String tags;
	
	private String views;
	
	private String likes;
	
	private String dislikes;
	
	private String commentCount;
	
	private String link;
	
	private String commentsDisabled;
	
	private String ratingsDisabled;
	
	private String errorRemoved;
	
	private String description;
	
	private String country;
	
	
	public int compareToID(YouTubeVideo otro)
	{
		int comparacion= Integer.parseInt(this.getVideoID())-Integer.parseInt(otro.getVideoID());
		if(comparacion==0)
		{
			return 0;
		}
		else if (comparacion>0)
		{
			return 1;
		}
		else
			return -1;
	}
	public static class ComparadorXLikes implements Comparator<YouTubeVideo>
	{

		@Override
		public int compare(YouTubeVideo o1, YouTubeVideo o2) {
			// TODO Auto-generated method stub
			int comparaciao = Integer.parseInt(o1.getLikes())-Integer.parseInt(o2.getLikes());
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
	public YouTubeVideo(String videoID, String trendingDate,String title, String channelTitle, String categoryID, String publishTime,
			String tags, String views, String likes, String dislikes, String commentCount, String link, String commentsDisabled,
			String ratingsDisabled, String errorRemoved, String description, String country) {
		super();
		this.videoID = videoID;
		this.trendingDate = trendingDate;
		this.title = trendingDate;
		this.channelTitle = channelTitle;
		this.categoryID = categoryID;
		this.publishTime = publishTime;
		this.tags = tags;
		this.views = views;
		this.likes = likes;
		this.dislikes = dislikes;
		this.commentCount = commentCount;
		this.link = link;
		this.commentsDisabled = commentsDisabled;
		this.ratingsDisabled = ratingsDisabled;
		this.errorRemoved = errorRemoved;
		this.description = description;
		country = country;
	}
	
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}



	public String getTrendingDate() {
		return trendingDate;
	}


	public void setTrendingDate(String trendingDate) {
		this.trendingDate = trendingDate;
	}
	public String getTitle() {
		return title;
	}


	public void setTitle(String pTitle) {
		this.title = pTitle;
	}


	public String getChannelTitle() {
		return channelTitle;
	}


	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}


	public String getCategoryID() {
		return categoryID;
	}



	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}


	public String getPublishTime() {
		return publishTime;
	}




	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}


	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getViews() {
		return views;
	}


	public void setViews(String views) {
		this.views = views;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getDislikes() {
		return dislikes;
	}


	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String isCommentsDisabled() {
		return commentsDisabled;
	}

	public void setCommentsDisabled(String commentsDisabled) {
		this.commentsDisabled = commentsDisabled;
	}

	public String isRatingsDisabled() {
		return ratingsDisabled;
	}

	public void setRatingsDisabled(String ratingsDisabled) {
		this.ratingsDisabled = ratingsDisabled;
	}

	public String isErrorRemoved() {
		return errorRemoved;
	}

	public void setErrorRemoved(String errorRemoved) {
		this.errorRemoved = errorRemoved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getCountry() {
		return country;
	}




	public void setCountry(String country) {
		country = country;
	}

	@Override
	public int compareTo(YouTubeVideo otro) 
	{
		// TODO Auto-generated method stub
		if (Double.parseDouble(this.getTrendingDate()) > Double.parseDouble(otro.getTrendingDate()))
		{
			
			return 1;
		
		}else if(Double.parseDouble(this.getTrendingDate()) == Double.parseDouble(otro.getTrendingDate()))
		{
		
			return 0;
		
		}else{
			
			return -1;
		}
		
		
	}
	public  int diasEnTendencia() throws ParseException
	{
		String[] x = publishTime.split("T");
		String fechaPublish = x[0];
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaPublicacion = formato.parse(fechaPublish);
		Date fechaTrending = formato.parse(trendingDate);
		String fechaP = fechaPublicacion +"";
		String fechaT = fechaTrending + "";
		LocalDate fecha1 = LocalDate.parse(fechaP);
		LocalDate fecha2= LocalDate.parse(fechaT);
		int diasTendencia = (int) ChronoUnit.DAYS.between(fecha1, fecha2);
		return diasTendencia;
		
	}

}
