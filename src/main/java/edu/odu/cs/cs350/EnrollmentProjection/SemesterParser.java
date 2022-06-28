package edu.odu.cs.cs350.EnrollmentProjection;

import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.nio.file.*;

/**
 * This is the Semester Parser class
 * overall the duties of the semester
 * parser is to use its main method
 * to call the other classes for their
 * individual responsibilities
 *
 * Its internal methods are described
 * below
 *
 */

public class SemesterParser {

    /**
     * This is the main function take args.length >3.
     * Create projection object that takes
     * historical data and targerSemster data.
     *
     * @param args the arguments given by the user
     * @param args.length is the num of arguments
     * @param args[0 < args.length - 2] historical data
     * @param args[args.length - 2] targerSemster data
     * @param args[args.length - 1] output destination
     *
     * @throws Exception
     */
	public static void main(String[] args)throws Exception
	{
		//int numArgs = args.length;
		//int targetPosition = numArgs - 2;
		/*Example:
	     *Length of 3
	     *0 1 2
	     *3-1= location
	     *3-2=target
	     *3-3= historical
	     *
	     *Length 10
	     *0 1 2 3...7 8 9
	     *10-1 location
	     *10-2 target
	     *10-3= historical
	     *10-4= historical
	     *...
	     *10-10=historical
	     **/
		if (args.length < 3) {
			throw new Exception("Not Enough Arguments!!!");
		}

		Semester targetSemester = null;

		//collection of semester
		List<Semester> historical = new LinkedList<Semester>();
		Projection semesterProjection;

		for (int i = 0; i < args.length - 2; i++) //loop retrieve historical data
		{
			//if(i < targetPosition)
			//{
				String argPath = args[i];
				String path = argPath + "/dates.txt";
				File tempFile = new File(path);
				boolean exists = tempFile.exists();
				if (exists)
				{
					String first, last = null, line;
				    BufferedReader in = new BufferedReader(new FileReader(path));
				    first = in.readLine();
				    while ((line = in.readLine()) != null) {
				      if (line != null) {
				        last = line;
				      }
				    }
				    
				    in.close();
				       
				    List<String> CsvFiles = new LinkedList<String>();

				    String Semesterid = getSemesterId(argPath);

				   
				    Semester TempSemester = new Semester(Semesterid, first, last);

				    parseForCsvFiles(argPath, CsvFiles);

				    addCollection(CsvFiles, TempSemester, in);

				    historical.add(TempSemester);	    

				}

				else
				{
					System.out.printf("Oops looks like the argument " + args[i] + " doesn't have a dates.txt file" + "\n");
				}
			} //close loop for historical


			//else if (i == targetPosition)
			//{

			String argPath = args[args.length - 2];
			String path = argPath + "/dates.txt";
			File tempFile = new File(path);
			boolean exists = tempFile.exists();
			if (exists)
			{
				String first, last = null, line;
			    BufferedReader in = new BufferedReader(new FileReader(path));
			    first = in.readLine();
			    while ((line = in.readLine()) != null) {
			      if (line != null) {
			        last = line;
			      }
			    }

			    in.close();

				List<String> CsvFiles = new LinkedList<String>();

				String Semesterid = getSemesterId(argPath);
				
				targetSemester = new Semester(Semesterid, first, last);

				parseForCsvFiles(argPath, CsvFiles);

				addCollection(CsvFiles, targetSemester, in);
		   
				} // close the target
			
			if (targetSemester != null) //send data to projection for calculation
			{
				String excelLocation = args[args.length - 1];
				semesterProjection = new Projection(historical, targetSemester, excelLocation);
				semesterProjection.printSummaryReport();
				semesterProjection.printDetailedReport();
			}


	} // close the main



//-----------------------------------

//	public static String getDates(String path, String first, String last) throws Exception
//	{
//		String line;
//	    BufferedReader in = new BufferedReader(new FileReader(path));
//	    first = in.readLine();
//	    while ((line = in.readLine()) != null) {
//	      if (line != null) {
//	        last = line;
//	      }
//	    }
////	    System.out.println("first: "+first + "\n" + "last: "+last);
//	    in.close();
//	    return first;
//	}
//-------------------------------------

    /**
     * This function take a List<String> filenames.
     * Add the all cvs file from the
     * parentDirectory to filenames.
     *
     * @param parentDirectory the parent directory
     * @param filenames a list of file names
     *
     */
	public static void parseForCsvFiles(String parentDirectory, List<String> filenames) {
       /*Quote code Begin
        *
        * Jai, John. “Finding CSV Files in a Directory?
        * Finding CSV Files in a Directory?
        * (Beginning Java Forum at Coderanch), Powered by JForum,
        * 2012,coderanch.com/t/544063/java/Finding-CSV-files-directory.
        *
        * */
		File[] filesInDirectory = new File(parentDirectory).listFiles();
        for (File file : filesInDirectory) {
            if (file.isDirectory()) {
                parseForCsvFiles(file.getAbsolutePath(), filenames);
            }
            addCSVfile(filenames, file);
        }
        /*Quote code End*/
    }


	/**
	 * this function adds CSV files to a list of strings
	 * @param filenames the string to be aded
	 * @param file the file being read
	 */
	public static void addCSVfile(List<String> filenames, File file) {
		String Path = file.getAbsolutePath();
		String fileExtenstion = Path.substring(Path.lastIndexOf(".") + 1, Path.length());
		if ("csv".equals(fileExtenstion)) {

		    filenames.add(Path);

		}
	}
	//-------------------------------------
	/**
     *Retrieve the string that holds [arrOfStr.length-2].
     *
     * This function take the string name file
     * and split the base on ':' ,'\' or '.' .
     * Store String[] arrOfStr the file split result.
     * Save the arrOfStr[arrOfStr.length-2] in a
     * local variable name tempDate.
     *
     * @param file
     *
     * @return current tempDate
     */
	public static String getFileDate(String file)
	 {
		 String[] arrOfStr = file.split("[:?\\\\\\.]+"); //separate base on : \  - or .
	        //tempDate.charAt(0)-tempDate.charAt(3) year
		    //tempDate.charAt(5)-tempDate.charAt(6) month
		    //tempDate.charAt(8)-tempDate.charAt(9) day
		 String tempDate = arrOfStr[arrOfStr.length - 2];

		 return tempDate;
	 }
	//-------------------------------------
	/**
     *Retrieve the string that holds [arrOfStr.length-1].
     *
     * This function take the string name file
     * and split the base on ':' or '\' .
     *
     * Store String[] arrOfStr the file split result.
     *
     * Save the arrOfStr[arrOfStr.length-1]
     * in a local variable name tempSemesterid
     *
     * @param file
     *
     * @return current tempSemesterid
     */
	public static String getSemesterId(String file)
	 {
		 String[] arrOfStr = file.split("[:?\\\\]+"); //separate base on : or \
		// String[] arrOfStr = file.split("[?\\\\]+"); 
		 String tempSemesterid = arrOfStr[arrOfStr.length - 1];

		 return tempSemesterid;
	 }
	//-------------------------------------
	 /**
     * Retrieve the str.
     *
     * This function take a string and remove
     * the any additional quotes.
     *
     * @param str desired  str
     *
     * @return current str
     */
	public static String removeQuote(String str)
	 {
		 return str.replaceAll("^[\"']+|[\"']+$", "");
	 }
	//-------------------------------------
    /**
     * Retrieve the Semester.
     *
     * Update Semester base on the an input directory.
     *
     * @param inBuffer input source
     * @param collection is the Semester to be updated
     * @param filenames collection of csv file where data is store
     *
     * @return current Semester
     */
	public static Semester addCollection(List<String> filenames, Semester collection, BufferedReader inBuffer)throws Exception {
		String line = "";
		String Date;
		for (String file:filenames)
	        {
	        	// System.out.println("CSV file -> " + f);
	        	Date = getFileDate(file);
	        	//System.out.println("CSV Date -> " + Date);
	        	try
	  	 		{
	  	 		//parsing a CSV file into BufferedReader class constructor
	        	addTheParserSnapshot(collection, Date, file);
		  	 	} //close try
	        	catch (IOException e)
	  	 		{
	  	 			e.printStackTrace();
	  	 		}
	  	 	} //close for loop
	        // System.out.print(collection );//print semester
		return collection;
	} //close addCollection


	/**
	 * adds the parser snapshot
	 * @param collection a collection of semesters
	 * @param Date the date of the semester
	 * @param file the file to parse
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void addTheParserSnapshot(Semester collection, String Date, String file)
			throws FileNotFoundException, IOException {
		BufferedReader inBuffer;
		String line;
		inBuffer = new BufferedReader(new FileReader(file));

		line = inBuffer.readLine(); ///remove the heading from the snapshot

		while ((line = inBuffer.readLine()) != null)   //returns a Boolean value
		{
		 		String[] snapshotInfo = line.split(","); // use comma as separator

		 		int CRN = 0;
		 		int OVERALLCAP = 0;
		 		int OVERALLENROLL = 0;

		 		if (removeQuote(snapshotInfo[1]).charAt(0) < 65)
		 		{
		 			CRN = Integer.parseInt(removeQuote(snapshotInfo[1]));
		 		}

		 		if (!removeQuote(snapshotInfo[23]).isEmpty())
		 		{
		 			OVERALLCAP = Integer.parseInt(removeQuote(snapshotInfo[23])); //23
		 		}

		 		if (!removeQuote(snapshotInfo[24]).isEmpty())
		 		{
		 			OVERALLENROLL = Integer.parseInt(removeQuote(snapshotInfo[24])); //24
		 		}

		 		Snapshot tempSnapshot = new Snapshot(CRN, removeQuote(snapshotInfo[2]), removeQuote(snapshotInfo[3]), Integer.parseInt(removeQuote(snapshotInfo[7])), OVERALLCAP, OVERALLENROLL, Date);

		 		collection.add(tempSnapshot);		  	 		
			} //close while
		inBuffer.close();
	}
}
