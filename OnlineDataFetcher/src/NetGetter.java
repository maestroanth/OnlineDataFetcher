import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.* ;

public class NetGetter extends JFrame{

    static Regex getSourceCode(String url)
    {
    	String siteScript = "";
    	String inputLine;
    	
    	Regex MyGirlieRegex = new Regex();
        try
        {
            //creating the URL
            URL pageURL = new URL(url);
            System.out.println("URL being evaluated: " + pageURL);
            //Create the http url connection object
            HttpURLConnection urlConnection = (HttpURLConnection) pageURL.openConnection();
            System.out.println("urlConnection being evaluated: " + urlConnection);
            //Reading the stream

            BufferedReader in = new BufferedReader( new InputStreamReader(pageURL.openStream()));
            try
            {
				while ((inputLine=in.readLine())!= null){
					siteScript = siteScript + inputLine;
				}
		         System.out.println("siteScript: " + siteScript);
				MyGirlieRegex.RunProfileAnalysis(siteScript);
				
			}		
            
	        catch(MalformedURLException ex)
		        {
		            System.out.println(url + " is not a valid URL. Please enter a URL starting with http://");
		        }// end catch for improper URL
        }
	    catch(IOException ie)
	        {
            System.out.println("Error while reading: " + ie.getMessage());
	        }// end catch for io reasons
        System.out.print(siteScript);//enable to see the html script over console
        return MyGirlieRegex;
    }// end getSourceCode method
    
    public static void main (String[] args)
    {
    	String url = "http://www.rollingstone.com/music/lists/the-500-greatest-songs-of-all-time-20110407/bob-dylan-like-a-rolling-stone-20110516";
    	//String url = "https://www.okcupid.com/";
    	Regex MyGirlieRegex = NetGetter.getSourceCode(url);

    	int numOfProfiles = 100;
    	for(int i = 0; i < numOfProfiles; i++)
        {
    		new MyGui(MyGirlieRegex);
        	MyGirlieRegex = NetGetter.getSourceCode(MyGirlieRegex.NextURL);
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }  // end main method	
	

}// end class