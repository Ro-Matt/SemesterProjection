package edu.odu.cs.cs350.EnrollmentProjection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.FileReader;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSemesterParser {

	@Before
	public void setUP()
	{

	}
	
	@Test
	public void testMain() throws Exception
	{	
//		String[] hasTxt = {"src", "test", "resources" , "202030", "dates.txt"};
//		Path path = Paths.get("src", "test", "resources" , "202030", "dates.txt");
//		String argPath = "src\\test\\resources\\202030\\dates.txt";
//		BufferedReader in = null;
//		//BufferedReader in = new BufferedReader(new FileReader(argPath));
//		
//		try {
//			in = new BufferedReader(new FileReader(path.toString()));
//		}
//		catch (FileNotFoundException exc) {
//			fail("Oops looks like that doesn't have a dates.txt file.");
//		}
//		
//		try {
//			in = new BufferedReader(new FileReader(path.toString()));
//			String first = in.readLine();
//			assertThat(first, equalTo("2020-10-19"));
//			String line,last = null;
//			while ((line = in.readLine()) != null) {
//				assertThat(line, equalTo("2021-05-19"));
//			      if (line != null) {
//			        last = line;
//					assertThat(last, equalTo("2021-05-19"));
//			      }
//			    }
//		}
//		catch (IOException exc) {
//			fail("No File cannot open");
//		}

		Path path = Paths.get("src", "test", "resources" , "201710", "dates.txt");
		String argPath = "src\\test\\resources\\201710\\dates.txt";
		BufferedReader in = null;

		String[] args = {"src\\test\\resources\\201710", 
				 		 "src\\test\\resources\\201720", 
				 		 "src\\test\\resources\\201830"};
		try {
			SemesterParser.main(args);
			in = new BufferedReader(new FileReader(path.toString()));
			String first = in.readLine();
			assertThat(first, equalTo("2020-10-19"));
			String line,last = null;
			while ((line = in.readLine()) != null) {
				assertThat(line, equalTo("2021-05-19"));
			      if (line != null) {
			        last = line;
					assertThat(last, equalTo("2021-05-19"));
			      }
			    }
			assertTrue(args.length >= 3);
		}catch (Exception e) 
		{
			e.getMessage();
			//throw new Exception("Not Enough Arguments!!!");
		}
		
		String[] args2 = {"src\\test\\resources\\201710"};
		try {
			SemesterParser.main(args2);
			assertFalse(args2.length >= 3);
		}catch (Exception d) 
		{
			d.getMessage();
			//throw new Exception("Not Enough Arguments!!!");
		}
		
		
//		/// Make sure the directory has dates.txt.
//		String path = "src\\test\\resources\\202030";
//		String argPath = path + "\\dates.txt";
//		File tempFile = new File(argPath);
//		boolean exists = tempFile.exists();
//		
//		assertTrue(exists);
//		
//		/// Make sure it can tell if it does not have a dates.txt.
//		String path2 = "src\\test\\resources\\201830";
//		
//		String argPath2 = path2 + "\\dates.txt";
//		File tempFile2 = new File(argPath2);
//		boolean exists2 = tempFile2.exists();
//		
//		assertFalse(exists2);
	}
	
//	@Test
//	public void TestNumArgs() throws Exception
//	{
//		String[] args = {"src\\test\\resources\\201710", 
//						 "src\\test\\resources\\201720", 
//						 "src\\test\\resources\\201830"};
//		try {
//			SemesterParser.main(args);
//			assertFalse(args.length < 3);
//		}catch (Exception e) 
//		{
//			throw new Exception("Not Enough Arguments!!!");
//		}
//		
//		String[] args2 = {"src\\test\\resources\\201710"};
//		try {
//			SemesterParser.main(args2);
//			assertFalse(args2.length >= 3);
//		}catch (Exception d) 
//		{
//			d.getMessage();
//			//throw new Exception("Not Enough Arguments!!!");
//		}
//		
//		
//		
//		
//	}

	   @Test
	    public void testsemesterID()
	    {
		   	String path3 = "src\\test\\resources\\201830";
	    	String id="201830";
	    	String notId="resources";
	    	assertEquals(id, SemesterParser.getSemesterId(path3));
	    	
	    	assertNotEquals(notId, SemesterParser.getSemesterId(path3));
	    }
	   
	   
	   @Test
	    public void testRemoveQuotes()
	    {
		   	String normal = "201830";
	    	String extraQuote="\"201830\"";
	    	//String extraQuote2="\"201830\"";
	    	//assertEquals(extraQuote2,extraQuote);
	    	assertEquals(normal, SemesterParser.removeQuote(extraQuote));
	    	//assertFalse(extraQuote2.equals(SemesterParser.removeQuote(extraQuote)));
	    	//assertNotEquals(extraQuote2, SemesterParser.getSemesterId(extraQuote));
	    }
	   @Test
	    public void testgetDate()
	    {
		   	String path = "src\\test\\resources\\202030\\2020-10-08.csv";
	    	String date="2020-10-08";
	    	assertEquals(date, SemesterParser.getFileDate(path));	    
	    }
	   @Test
	    public void testparseForCsvFiles()
	    {
		   List<String> CsvFiles = new LinkedList<String>();
		   List<String> Empty = new LinkedList<String>();
		   String path3 = "src\\test\\resources\\GabyPaserTesting\\202030";
		  
		    assertThat(CsvFiles.size(),equalTo(Empty.size()));
		    
		   File[] filesInDirectory = new File(path3).listFiles();
	        for (File file : filesInDirectory) {
	            if (file.isDirectory()) {
	            	SemesterParser.parseForCsvFiles(file.getAbsolutePath(), CsvFiles);
	            }
	            SemesterParser.addCSVfile(CsvFiles, file);
	        }
		 // before mutation
	      assertThat(CsvFiles.toString(), stringContainsInOrder(Arrays.asList("src\\test\\resources\\GabyPaserTesting\\202030\\2020-10-08.csv", "src\\test\\resources\\GabyPaserTesting\\202030\\2020-10-09.csv")));
	      
		  assertThat(CsvFiles.size(), not(equalTo(Empty.size())));
		   
		 //  SemesterParser.parseForCsvFiles(path3, CsvFiles);
		   
		 //after Mutation
		   
		   
	    }
	   @Test
	    public void testaddCsvFiles()
	    {
		   List<String> CsvFiles = new LinkedList<String>();
		   List<String> Empty = new LinkedList<String>();
		  
		  
		   
		   
		   File file = new File("src\\test\\resources\\GabyPaserTesting\\202030\\2020-10-08.csv");
		   
		   SemesterParser.addCSVfile(CsvFiles, file);
		   
		   assertThat(CsvFiles.toString(), stringContainsInOrder(Arrays.asList("src\\test\\resources\\GabyPaserTesting\\202030\\2020-10-08.csv")));
		   
		   File file2 = new File("src\\test\\resources\\GabyPaserTesting\\202030\\dates.txt");
		   SemesterParser.addCSVfile(CsvFiles, file2);
		   
		   assertThat(CsvFiles.toString(), stringContainsInOrder(Arrays.asList("src\\test\\resources\\GabyPaserTesting\\202030\\2020-10-08.csv")));
		   
		   assertThat(CsvFiles.size(), not(equalTo(Empty.size())));
	    }
	   @Test
	    public void testaddcollectionParseData() throws IOException 
	    {
		  
		 Semester testSemesterParse=new Semester("202030","2020-10-19","2021-05-19");
		// Semester Snapshot=new Semester("202030","2020-10-19","2021-05-19");
		
		 String Date1="2020-10-08"; 
		// String Date2="2020-10-09";
		 
		 List<String> CsvFiles= new LinkedList<String>();
		 CsvFiles.add("src\\test\\resources\\GabyPaserTesting\\202030\\2020-10-08.csv");	 
		
		 String[] pathSteps = {"src", "test", "resources", "GabyPaserTesting", "202030","2020-10-08.csv"};
	     Path path = Paths.get("src", "test", "resources", "GabyPaserTesting", "202030","2020-10-08.csv"); 
	     
		 BufferedReader inBuffer = null;
		 try {
			SemesterParser.addCollection(CsvFiles, testSemesterParse, inBuffer) ;
			SemesterParser.addTheParserSnapshot(testSemesterParse, Date1, path.toString());
			String line1 = "Seats,CRN,SUBJ,CRSE,TITLE,CR HRS,XLST CAP,ENR,LINK,XLST GROUP,SCHED TYPE,,CAMPUS,INSM,PRINT?,TIME,DAYS,BLDG,ROOM,OVERRIDE,INSTRUCTOR,,OVERALL CAP,OVERALL ENR,,,PTRM START,PTRM END,WL CAP,WL,WL REMAIN,NOTES,COMMENTS,COLL"; //the line we will try to read
			String line2 ="25,32600,CS,112,INFO LIT FORMER ENGN MAJORS,1,25,0,,8O,A,I,B,ASYN,Y,HOURS ARR,,WEB2,,,\"GUPTA,R\",,25,0,1,ASYN,17-May-21,26-Jun-21,0,0,0,,,";
			InputStream stream = new ByteArrayInputStream((line1+"\n").getBytes(StandardCharsets.UTF_8)); //this stream will output the example string
			InputStream stdin = System.in; //save the standard in to restore it later
			System.setIn(stream);
			inBuffer = new BufferedReader(new InputStreamReader(System.in));
			assertEquals(line1, inBuffer.readLine()); //check 
			System.setIn(stdin);//restore the stardard in
		
			final String expectedOutput = "Semester level\n202030\n  Date Level:2020-10-08\n  Course Level:CS 112\n  \n\tCS 112\t0\t\t25";
			final String actualOutput = testSemesterParse.toString();
			  
			//assertTrue(actualOutput.equals(expectedOutput));
			assertThat(testSemesterParse.size(), is(1));///start1
			  
			//testSemesterParse.getalldateList().
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	   
	    }
	   @Test
	    public void testaddTheParserSnapshot() throws IOException 
	    {
		   Semester testSemesterParse=new Semester("202030","2020-10-19","2021-05-19");
		   Semester testSemesterParse2=new Semester("202030","2020-10-19","2021-05-19");
			// Semester Snapshot=new Semester("202030","2020-10-19","2021-05-19");
			
			 String Date1="2020-10-08"; 
			// String Date2="2020-10-09";
			 
			 String[] pathSteps = {"src", "test", "resources", "GabyPaserTesting", "202030","2020-10-08.csv"};
		     Path path = Paths.get("src", "test", "resources", "GabyPaserTesting", "202030","2020-10-08.csv"); 
			 //String CsvFiles="src\\test\\resources\\GabyPaserTesting\\202030\\2020-10-08.csv";
			 
			 String line1 = "Seats,CRN,SUBJ,CRSE,TITLE,CR HRS,XLST CAP,ENR,LINK,XLST GROUP,SCHED TYPE,,CAMPUS,INSM,PRINT?,TIME,DAYS,BLDG,ROOM,OVERRIDE,INSTRUCTOR,,OVERALL CAP,OVERALL ENR,,,PTRM START,PTRM END,WL CAP,WL,WL REMAIN,NOTES,COMMENTS,COLL"; //the line we will try to read
			 String line4="25,32600,CS,112,INFO LIT FORMER ENGN MAJORS,1,25,0,,8O,A,I,B,ASYN,Y,HOURS ARR,,WEB2,,,\"GUPTA,R\",,25,0,1,ASYN,17-May-21,26-Jun-21,0,0,0,,," ;
			 
			 BufferedReader aBuffer = null;
			 try {
				int CRN = 32600;
				String subject="CS";
				String CRES="112";
				int Enroll= 0;
			 	int OVERALLCAP = 25;
			 	int OVERALLENROLL = 0;
			 	
				 SemesterParser.addTheParserSnapshot(testSemesterParse, Date1, path.toString());
				 aBuffer = new BufferedReader(new FileReader(path.toString()));
				 String firtsline = aBuffer.readLine(); 
				 String secondline = aBuffer.readLine();
				 assertThat(firtsline,equalTo(line1));
				 assertThat(secondline,equalTo(line4));
				 
				 String[] snapshotInfo = secondline.split(","); 
				 
				 Snapshot  cs350Snapshot= new Snapshot(CRN, subject, CRES, Enroll,OVERALLCAP, OVERALLENROLL, Date1);
				 
				 Snapshot  cs350SnapshotRead= new Snapshot(CRN, SemesterParser.removeQuote(snapshotInfo[2]), SemesterParser.removeQuote(snapshotInfo[3]), Integer.parseInt(SemesterParser.removeQuote(snapshotInfo[7])), OVERALLCAP, OVERALLENROLL, Date1);
				 
				 assertThat(cs350SnapshotRead.getCalendarDate(), is(Date1));///start1
				 assertThat(cs350SnapshotRead.getCap(),is(OVERALLCAP));
				 assertThat(cs350SnapshotRead.getCRN(),is(CRN));
				 assertThat(cs350SnapshotRead.getCRSE(),is(CRES));
				 assertThat(cs350SnapshotRead.getOverallEnrollment(),is(OVERALLENROLL));
				 assertThat(cs350SnapshotRead.getCourseName(),is("CS 112"));
				 assertThat(cs350SnapshotRead.getSUBJ(),is(subject));
				 
				 assertThat(cs350SnapshotRead.getCap(),equalTo(cs350Snapshot.getCap()));
				 assertThat(cs350SnapshotRead.getCRN(),equalTo(cs350Snapshot.getCRN()));
				 assertThat(cs350SnapshotRead.getCRSE(),equalTo(cs350Snapshot.getCRSE()));
				 assertThat(cs350SnapshotRead.getOverallEnrollment(),equalTo(cs350Snapshot.getOverallEnrollment()));
				 assertThat(cs350SnapshotRead.getCourseName(),equalTo(cs350Snapshot.getCourseName()));
				 assertThat(cs350SnapshotRead.getSUBJ(),equalTo(cs350Snapshot.getSUBJ()));
				 assertThat(cs350SnapshotRead.getCalendarDate(),equalTo(cs350Snapshot.getCalendarDate()));
				 
				 testSemesterParse2.add(cs350Snapshot);
				 
				 String []line2=line4.split(",");
				 assertThat(snapshotInfo,equalTo(line2));  
				 final String expectedOutput = "Semester level\n202030\n  Date Level:2020-10-08\n  Course Level:CS 112\n  \n\tCS 112\t0\t\t25";
				 final String actualOutput = testSemesterParse.toString();
					  
				assertTrue(actualOutput.equals(expectedOutput));
				assertThat(testSemesterParse.size(), is(1));///start1
				
				//assertTrue(testSemesterParse.equals(testSemesterParse2));
				assertThat(testSemesterParse.size(),equalTo(testSemesterParse2.size()));
				
		        }
		        catch (FileNotFoundException exc) {
		            fail("File Could Not be Opened");
		        }
			 

	    }
	   
	   
	   
} 


