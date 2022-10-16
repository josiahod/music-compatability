	import java.util.Collection;
import java.util.Scanner;

import de.umass.lastfm.*;
	public class topTracking  {
	public static void main(String[] args) 
	{
		double reportResults = 0;
		
		String id = "00ee6268ce38fbdf719a9093e6ca7ef7"; //last fm api id
		
		
		Scanner scan = new Scanner(System.in);  
		System.out.print("Enter User Number One: "); //first user to compare data
		String name1 = scan.nextLine();
		System.out.print("Enter User Number Two: "); //second user to compare data
		String name2 = scan.nextLine();
	    
	    
		Collection<Album> nice = User.getTopAlbums(name1, id); //gather top albums from user 1 and 2
		Collection<Album> nice2 = User.getTopAlbums(name2, id);
		Collection<Artist> nice3 = User.getTopArtists(name1,id); //gathers top artists from user 1 and 2
		Collection<Artist> nice4 = User.getTopArtists(name2, id);
	
		System.out.println(name1 + " and " + name2 + " Compatability Report");
		System.out.println("-----");
		Object[] albums = nice.toArray();
		Object[] albums2 = nice2.toArray();
		String[] allAlbums = getTopAlbum(albums);
		String[] allAlbums2 = getTopAlbum(albums2);
		System.out.println("Your albums in common are...");
		reportResults += similarityReport(combineArr(allAlbums, allAlbums2)); //runs a similarity test on the top 50 albums from each user
		System.out.println("");
		Object[] artist1 = nice3.toArray();
		Object[] artists2 = nice4.toArray();
		String[] allartist1 = getTopAlbum(artist1);
		String[] allartists2 = getTopAlbum(artists2);
		System.out.println("Your artists in common are...");
		reportResults += similarityReportArtist(combineArr(allartist1, allartists2)); //runs similarity report on the top 50 artists from each user
		System.out.println("");
		System.out.println("Your Compatability Rate is " + (reportResults/50)*50 + "%"); //calculates compatability percentage
		

	}
	
	public static String[] getTopAlbum(Object[] nice)
	{
		 String[] allAlbums = new String[nice.length]; 
		for(int i=0; i<nice.length; i++)
		{
		   String s = String.valueOf(nice[i]);
	       String target = "',";
	       int index = s.indexOf(target);
	       int subIndex = index + target.length()-2;
	      // System.out.println(s.substring(12,subIndex));
	       allAlbums[i] = (s.substring(12,subIndex));
		}
		return allAlbums; 
		
		
		
	}
	
	public static double similarityReport(String[] array)
	{
		boolean noResults = true; 
		int counter = 0; 
		for(int i = 0; i < array.length; i++) 
		{  
            for(int j = i + 1; j < array.length; j++)
            {  
                if(array[i].equals(array[j])) 
                {
                    System.out.println("'" + array[j]);
                    noResults = false; 
                    counter++;
                }
   
            }  
        } 
		System.out.println("");
		System.out.println("You have " + counter + " top albums in common");

		if(noResults)
			System.out.println("No Album Similarities Found"); 
		return counter;
	}
	
	public static double similarityReportArtist(String[] array)
	{
		boolean noResults = true; 
		int counter = 0; 
		for(int i = 0; i < array.length; i++) 
		{  
            for(int j = i + 1; j < array.length; j++)
            {  
                if(array[i].equals(array[j])) 
                {
                    System.out.println(array[j]);
                    noResults = false; 
                    counter++;
                }
   
            }  
        } 
		System.out.println("");
		System.out.println("You have " + counter + " top artists in common");

		if(noResults)
			System.out.println("No Artist Similarities Found"); 
		return counter;
	}
	
	public static String[] combineArr(String[] arr1, String[] arr2)
	{ 
		int length1 = arr1.length;
		int length2 = arr2.length;
		String[] result = new String[length1 + length2];  //resultant array of size first array and second array  
		System.arraycopy(arr1, 0, result, 0, length1);  
		System.arraycopy(arr2, 0, result, length1, length2);  
		return result;
		
	}

}
