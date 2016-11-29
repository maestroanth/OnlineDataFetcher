
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Regex {

	public static final String GET_GIRLS_NAME = "class=\"name \">(.*?)</span";
	public static final String GET_SONG_WRITER = "Writer(s)?:\\s*</strong>(.*?)<br";
	public static final String GET_SONG_PRODUCER = "Producer(s)?:\\s*</strong>(.*?)<br";
	public static final String GET_SONG_RELEASE_DATE = "Released:\\s*</strong>(.*?)<br";
	public static final String GET_THIRTY_CHARACTERS = "No.(.*?)\"</p";
	public static final String GET_NEXT_URL = "\"image_wrapper ajax_load_profile_link loaded\" href=\"(.*?)\" data-low=" ;
	
	public static final String MAIN_URL = "http://www.rollingstone.com/music/lists/the-500-greatest-songs-of-all-time-20110407";
	
	public String NextURL;
	//public String GirlsName;
	public String SongWriter;
	public String SongProducer;
	public String SongReleaseDate;
	public String ThirtyCharacters;
	
	//pattern attributes
	//public Pattern girlsName;
	public Pattern songWriter;
	public Pattern songProducer;
	public Pattern songReleaseDate;
	public Pattern thirtyCharacters;
	public Pattern nextURL;
	
	//constructor
	public Regex() 
	{

		//girlsName = Pattern.compile(GET_GIRLS_NAME);
		songWriter = Pattern.compile(GET_SONG_WRITER);
		songProducer = Pattern.compile(GET_SONG_PRODUCER);
		songReleaseDate = Pattern.compile(GET_SONG_RELEASE_DATE);
		thirtyCharacters = Pattern.compile(GET_THIRTY_CHARACTERS);
		nextURL = Pattern.compile(GET_NEXT_URL);

		}
	/*
	 * 
	 * Main Method
	 * 
	 * 
	 */
	
	public void RunProfileAnalysis(String SiteScript)
	{
		//Matcher findGirlsName;
		Matcher findSongWriter;
		Matcher findSongProducer;
		Matcher findSongReleaseDate;
		Matcher findThirtyCharacters;
		Matcher findNextURL;
		
		/*
		 * Don't be confused the local variables with same name are capitalized mean to go for print!
		 */


		String PartOfNextURL;
		
		//findGirlsName = girlsName.matcher(SiteScript);
		findSongWriter = songWriter.matcher(SiteScript);
		findSongProducer = songProducer.matcher(SiteScript);
		findSongReleaseDate = songReleaseDate.matcher(SiteScript);
		findThirtyCharacters = thirtyCharacters.matcher(SiteScript);
		findNextURL = nextURL.matcher(SiteScript);
		/*
		if (findGirlsName.find()) 
		{	
			MatchResult Result = findGirlsName.toMatchResult();//get match result from string
			String NameResult = Result.toString();//converts back to a string
			
			GirlsName = ClipGirlsName(NameResult);	
			System.out.println("Girl's Name: " + this.girlsName);
		}
		
		*/
		if (findSongWriter.find()) 
		{	
			MatchResult Result = findSongWriter.toMatchResult();//get match result from string
			String WriterResult = Result.toString();//converts back to a string
			SongWriter = ClipWriterReleaseDate(WriterResult);	
			System.out.println("Song Writer: " + this.SongWriter);
		}
		
		if (findSongProducer.find()) 
		{	
			MatchResult Result = findSongProducer.toMatchResult();//get match result from string
			String ProducerResult = Result.toString();//converts back to a string
			
			SongProducer= ClipProducer(ProducerResult);	

			System.out.println("Song Producer: " + this.SongProducer);
		}
		
		if (findSongReleaseDate.find()) 
		{	
			MatchResult Result = findSongReleaseDate.toMatchResult();//get match result from string
			String ReleaseDateResult = Result.toString();//converts back to a string
			
			SongReleaseDate = ClipWriterReleaseDate(ReleaseDateResult);	
			//need to figure out how to get weird of weirdness of release date
			SongReleaseDate = SongReleaseDate.replaceAll("\\&.*?#.*?;.*?","");
			System.out.println("Song Release: " + this.SongReleaseDate);
		}
		
		if (findThirtyCharacters.find())
		{
			MatchResult Result = findThirtyCharacters.toMatchResult();//get match result from string
			String ThirtyCharactersResult = Result.toString();//converts back to a string
			
			ThirtyCharacters = ClipThirtyCharacters(ThirtyCharactersResult);	
			ThirtyCharacters = ThirtyCharacters.replaceAll("\\&.*?#.*?;.*?","");
			System.out.println("Description: " + this.ThirtyCharacters);
		}
		
		if (findNextURL.find())
		{
			MatchResult Result = findNextURL.toMatchResult();//get match result from string
			String NextURLResult = Result.toString();//converts back to a string
			PartOfNextURL = ClipNextURL(NextURLResult);	
			this.NextURL = "http://www.rollingstone.com/music/lists/the-500-greatest-songs-of-all-time-20110407" + PartOfNextURL;
			
			System.out.println("Next Page: " + this.NextURL);
		}
		
	}
		
	
	/**
	 * 
	 * Methods that do the string clipping
	 * 
	 * 
	 */
	
	public final String ClipGirlsName(String MatchResult) 
	{

		String NameDisplay = MatchResult.substring(MatchResult.lastIndexOf(">") + 1, MatchResult.lastIndexOf("<"));
		return NameDisplay;
	}
	public final String ClipWriterReleaseDate(String MatchResult) 
	{
		int clipValue;

		int CLIP = 4; //<br <-clips of the number of characters from this		
		//now clips the string
		
		clipValue = MatchResult.length() - CLIP;
		String SongDisplay = MatchResult.substring(MatchResult.lastIndexOf(">") + 1,  MatchResult.lastIndexOf("<"));
		return SongDisplay;
	} 
	private String ClipThirtyCharacters(String MatchResult) {
		String DescriptionDisplay = MatchResult.substring(MatchResult.lastIndexOf(">") + 1, MatchResult.lastIndexOf("<"));
		return DescriptionDisplay;
	}
	public final String ClipProducer(String MatchResult) 
	{
		int clipValue;

		int CLIP = 11; //<-clips the &nslp crap		
		//now clips the string
		
		clipValue = MatchResult.length() - CLIP;
		String SongDisplay = MatchResult.substring(MatchResult.lastIndexOf(">") + 1,  MatchResult.lastIndexOf("<"));
		return SongDisplay;
	} 
	
	public final String ClipNextURL(String MatchResult) 
	{
		int clipValue;

		int CLIP = 24; //<-clips the " class="listPagination crap		
		//now clips the string
		
		clipValue = MatchResult.length() - CLIP;
		String NextURL= MatchResult.substring(MatchResult.lastIndexOf("407") + 3, clipValue);
		
		return NextURL;
	} 
}