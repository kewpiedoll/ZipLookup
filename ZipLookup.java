// This program uses a file of ZIP code information 
//      found at the opendatasoft.com website (see link below).
// The program allows a user to find ZIP codes within a certain distance
// 		of another ZIP code. User entry is via the command line.
// ZIP is an acronym for Zone Improvement Project (United States).
// 		More info on ZIP codes here: https://en.wikipedia.org/wiki/ZIP_Code
// Specific file download (CSV): 
// https://public.opendatasoft.com/explore/dataset/us-zip-code-latitude-and-longitude/download/?format=csv&timezone=America/Los_Angeles&lang=en&use_labels_for_header=true&csv_separator=%3B

import java.util.*;
import java.io.*;

public class ZipLookup {

	// radius of Earth in miles
	public static final double RADIUS = 3956.6;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		giveIntro();
		Scanner console = new Scanner(System.in);

		System.out.print("What ZIP code are you interested in? ");
		String target = console.next();
		System.out.print("And what proxomity (in miles)? ");
		double miles = console.nextDouble();
		
		BufferedReader csvReader = new BufferedReader(new FileReader("us-zip-code-latitude-and-longitude.csv"));
		BufferedReader csvReader2 = new BufferedReader(new FileReader("us-zip-code-latitude-and-longitude.csv"));
		
		// call the method to find the lat/long coordinates of the user-entered ZIP code
		String[] targetCoordinates = find(target, csvReader);
		showMatches(targetCoordinates, csvReader2, miles);
	}

	// program introudction for user
	public static void giveIntro() {
		System.out.println("Welcome to the ZIP code database.");
		System.out.println("Enter a 5-digit ZIP code and a proximity,");
		System.out.println("and this program will calculate where");
		System.out.println("that ZIP code is located, along with");
		System.out.println("a list of other ZIP codes with");
		System.out.println("the given proximity.");
		System.out.println();
	}

	// Searches for the given string in the input file; if found,
	// returns the coordinates; otherwise returns an array of "0"s.
	public static String[] find(String target, BufferedReader csvReader) throws IOException {
		String[] row = new String[7];
		while ((csvReader.readLine()) != null) {
    		row = csvReader.readLine().split(";");
    		if (row[0].equals(target)) {
    			return row;
    		}
		}
		csvReader.close();
		String[] nullArray = new String[] {"0","0","0","0","0","0","0"};
		return nullArray;
	}

	// Shows all matches for the given coodinates within the
	// given number of miles
	public static void showMatches(String[] coordinates, BufferedReader csvReader, 
									double miles) throws IOException {
		double lat1 = Double.parseDouble(coordinates[3]);
		double long1 = Double.parseDouble(coordinates[4]);
		String[] row = new String[7];

		while ((csvReader.readLine()) != null) {
    		row = csvReader.readLine().split(";");
    		double lat2 = Double.parseDouble(row[3]);
    		double long2 = Double.parseDouble(row[4]);
    		double distance = distance(lat1, long1, lat2, long2);

    		if (distance < miles) {
    			System.out.printf("    %s  %s %s, %3.2f miles\n", row[0], row[1], row[2], distance);
    		}
		}
		csvReader.close();
	}

	// calculate distance (miles) between two points of lat/long coordinates
	public static double distance(double lat1, double long1, double lat2, double long2) {
		lat1 = Math.toRadians(lat1);
		long1 = Math.toRadians(long1);
		lat2 = Math.toRadians(lat2);
		long2 = Math.toRadians(long2);
		
		double theCos = Math.sin(lat1) * Math.sin(lat2) 
						+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2);
		double arcLength = Math.acos(theCos);
		return arcLength * RADIUS;
	}
}
