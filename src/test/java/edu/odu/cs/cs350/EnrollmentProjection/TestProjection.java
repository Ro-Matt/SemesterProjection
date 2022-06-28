package edu.odu.cs.cs350.EnrollmentProjection;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjection {

	Snapshot Snapshot1;
	Snapshot Snapshot2;
	Snapshot Snapshot3;
	
	Semester sem1;
	Semester sem2;
	Semester target;
	List<Semester> semList;
	
	Projection projDef;
	Projection projPar;
	
	@Before
	public void BeforeEach()
	{
		Snapshot1 = new Snapshot(1, "CS", "120", 1, 1, 1, "2020-10-01");
		Snapshot2 = new Snapshot(2, "CS", "121G", 1, 1, 1, "2020-02-01");
		Snapshot3 = new Snapshot(2, "CS", "250", 1, 1, 1, "2020-05-01");
		
		sem1 = new Semester("202010", "2020-09-01", "2020-10-01");
		sem2 = new Semester("202020", "2020-01-01", "2020-02-01");
		
		semList = new LinkedList<Semester>();
		semList.add(sem1);
		semList.add(sem2);
		
		target = new Semester("202030", "2020-05-01", "2020-06-01");
		target.add(Snapshot3);
		
		projPar = new Projection(semList, target," ");	
		projDef = new Projection();
	}
	
	@Test
	public void testDefConstructor()
	{
		assertEquals(projDef.getTargetID(), null);
		assertNotNull(projDef.historicalList);
		assertNotNull(projDef.targetSemester);
		assertThat(projDef.historicalList.size(), equalTo(0));		
	}
	
	@Test
	public void testParamConstructor()
	{
		// make sure the historical list and the target semester were allocated
		assertNotNull(projPar.historicalList); 
		assertNotNull(projPar.targetSemester);
		
		// test setTargetID 
		assertThat(projPar.getTargetID(), containsString("202030"));
	}
	
	
	@Test
	public void testprintSummaryReport()
	{
		
		Snapshot Snapshot12 = new Snapshot(1, "CS", "120", 1, 1, 1, "2020-10-01");

		
		
		Snapshot copySnapshot32 = new Snapshot(2, "CS", "250", 1, 1, 1, "2020-05-01");
		
		
		
		Snapshot Snapshot32 = new Snapshot(2, "CS", "250", 1, 1, 1, "2020-05-01");
		Semester sem12,target2;
		
		sem12 = new Semester("202010", "2020-09-01", "2020-10-01");
	
		
		sem12.add(Snapshot12);
		sem12.add(copySnapshot32);
		
		List<Semester> semList2;
		semList2 = new LinkedList<Semester>();
		semList2.add(sem12);
		semList2.add(sem12);
		
		target2 = new Semester("202030", "2020-05-01", "2020-06-01");
		target2.add(Snapshot32);
		
		
		Projection projPar3 = new Projection(semList2, target2,"projection report.xlsx");	
		Projection projPar4 = new Projection(semList2, target2,"projection report.xlsx");
		projPar3.printSummaryReport();
		
		LinkedList<Semester> historicalList = new LinkedList<Semester>(semList2);
		int courseProjection; 	
											
		// store the target date d', for c(d')
		double nrmlTarg = target2.getLastDate().getNormalizedDate();
		
		// convert nrmlTarg to percentage
		double nrmlTargParam = nrmlTarg * 100;

		// create summaryReport obj with the normalized date as param
		SummaryProjectionReport summaryReport = new SummaryProjectionReport(nrmlTargParam);
		
		// copy the allCourse list from last Date node in the target semester
		// essentially a list of c(d')
		List<Course> targetCourseList = new LinkedList<Course>(target2.getLastDate().getAllCourse());
		// essentially a list of h(1)
		List<Course> histCourseList = new LinkedList<Course>();
		
		histCourseList.addAll((historicalList).getLast().dateList.getLast().getAllCourse());
		
		// print summary header
		///summaryReport.printSummaryReportHeader();
		
		for(Course targetCourse : targetCourseList)
		{
			for (Course histCourse : histCourseList)
			{
				if (histCourse.getCourseName().equals(targetCourse.getCourseName()))
				{			
					// calculate projection in here
					// This value will be h(d'), interpolated or not. 
				    int historicalLatest = projPar3.interpolateHistoricalEnrollment(targetCourse.getCourseName(), nrmlTarg); //this is h(d')
				    // calculate the projection using all the variables
				    assertThat(projPar3.interpolateHistoricalEnrollment(targetCourse.getCourseName(), nrmlTarg), equalTo(projPar4.interpolateHistoricalEnrollment(targetCourse.getCourseName(), nrmlTarg)) );
					
				    courseProjection = projPar3.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest );
				    assertThat(projPar3.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest ), equalTo(projPar4.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest ) ));
					
					//summaryReport.printSummaryReport(targetCourse.getCourseName(), targetCourse.getOverallEnrollment(), courseProjection, targetCourse.getOverallCap());
				}
			}
			
		}
		
		
		
		//Projection projPar4 = new Projection(semList2, target2,"projection report.xlsx");
		//projPar4.printSummaryReport();
		//assertEquals(projPar4.printSummaryReport(),projPar3.printSummaryReport() );
		
	}
	@Test
	public void testsort()
	{
		//Projection projPar3 = new Projection(semList2, target2,"projection report.xlsx");
		ArrayList<DetailedProjectionReport> list=new ArrayList<DetailedProjectionReport>();
		DetailedProjectionReport row1_1=new DetailedProjectionReport("CS361", 'H',1, 0,"201910", .1, 30);
	    
		DetailedProjectionReport row1_10=new DetailedProjectionReport("CS362", 'H',2, 0,"202010", .6, 40); 
		DetailedProjectionReport row1_9=new DetailedProjectionReport("CS362", 'H',1, 0,"202010", .5, 20);
		DetailedProjectionReport row1_25=new DetailedProjectionReport("CS411", 'P',1, 4," ", .5, 60);
		
		DetailedProjectionReport row1_12=new DetailedProjectionReport("CS362", 'H',4, 0,"202010", 1.05, 80);
		
		
		DetailedProjectionReport row1_5=new DetailedProjectionReport("CS361", 'C',1, 4,"202130", 0, 0);
	
		
		DetailedProjectionReport row1_16=new DetailedProjectionReport("CS362", 'P',1, 4," ", .5, 60);
		
		
	
	
		  list.add(row1_1); 
		  list.add(row1_10); 
		  list.add(row1_9); 
		  list.add(row1_25); 
		  list.add(row1_12); 
		  list.add(row1_5); 
		  list.add(row1_16); 
		  //projPar.SendprintDetailedReport(list);
		 // Collections.sort(list);
		  
			//DetailedProjectionReport callPrint = new DetailedProjectionReport();
		    //callPrint.printDetailReport(detailedList, detailedOutput);
		 // list.stream().map(s -> s.toString()).forEach(System.out::print);
		  
	}
	/*@Test
	public void testProjectionRelativePath() throws IOException
	{
		/*SemesterParser TestingProjestionSemesterParser= new SemesterParser();
		Path path = Paths.get("src", "test", "resources" , "201710", "dates.txt");
		//String argPath = "src\\test\\resources\\201710\\dates.txt";
		BufferedReader in = null;

		String[] args = {
				 		 "src\\test\\resources\\202010", 
				 		 "src\\test\\resources\\202020", 
				 		 "src\\test\\resources"};
		
	
		Semester targetSemester = null;

		//collection of semester
		List<Semester> historical = new LinkedList<Semester>();
		

		for (int i = 0; i < args.length - 2; i++) //loop retrieve historical data
		{
			//if(i < targetPosition)
			//{
				String argPath = args[i];
				String path2 = argPath + "/dates.txt";
				File tempFile = new File(path2);
				boolean exists = tempFile.exists();
				if (exists)
				{
					String first, last = null, line;
				    in = new BufferedReader(new FileReader(path2));
				    first = in.readLine();
				    while ((line = in.readLine()) != null) {
				      if (line != null) {
				        last = line;
				      }
				    }
				    
				    in.close();
				       
				    List<String> CsvFiles = new LinkedList<String>();

				    String Semesterid = TestingProjestionSemesterParser.getSemesterId(argPath);

				   
				    Semester TempSemester = new Semester(Semesterid, first, last);

				    TestingProjestionSemesterParser.parseForCsvFiles(argPath, CsvFiles);

				    try {
						TestingProjestionSemesterParser.addCollection(CsvFiles, TempSemester, in);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				    historical.add(TempSemester);	    

				}

				
			} //close loop for historical


			//else if (i == targetPosition)
			//{

			String argPath = args[args.length - 2];
			String path2 = argPath + "/dates.txt";
			File tempFile = new File(path2);
			boolean exists = tempFile.exists();
			if (exists)
			{
				String first, last = null, line;
			     in = new BufferedReader(new FileReader(path2));
			    first = in.readLine();
			    while ((line = in.readLine()) != null) {
			      if (line != null) {
			        last = line;
			      }
			    }

			    in.close();

				List<String> CsvFiles = new LinkedList<String>();

				String Semesterid = TestingProjestionSemesterParser.getSemesterId(argPath);
				
				targetSemester = new Semester(Semesterid, first, last);

				TestingProjestionSemesterParser.parseForCsvFiles(argPath, CsvFiles);

				try {
					TestingProjestionSemesterParser.addCollection(CsvFiles, targetSemester, in);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   
				} // close the target
			
		
				String excelLocation = args[args.length - 1];
				Projection semesterProjection = new Projection(historical, targetSemester, excelLocation);
				Projection projPar4 = new Projection(historical, targetSemester,"projection report.xlsx");
				
				
				LinkedList<Semester> historicalList = new LinkedList<Semester>(historical);
				
				 ArrayList<DetailedProjectionReport> detailedList = new ArrayList<DetailedProjectionReport>();
				  // initialize row, column, and type
				  int col = 0;
				  int row = 0;
				  char type = 'H';
				  // iterate through list of semesters
				  for(Semester histSem : historicalList)
				  {
					  // get the semester ID
					  String tempSemId = histSem.getSemesterId();
					  
					  
					  // iterate through the dates list of this semester
					  for(Dates histDate : histSem.dateList)
					  {
						  // store the normalized date
						  double tempNormalDate = histDate.getNormalizedDate();
						  row++;
						  // iterate through the list of courses for this snapshot date
						  for(Course histCourse : histDate.getAllCourse())
						  {
							  String tempCourseName = histCourse.getCourseName();
							  int tempEnrollment = histCourse.getOverallEnrollment();
							  DetailedProjectionReport reportObj = new DetailedProjectionReport(tempCourseName, type, row, col, tempSemId, tempNormalDate, tempEnrollment);
							  detailedList.add(reportObj);
						  } // end of course loop
						  
					  } // end of dates loop
					  
					  // reset row
					  row = 0;
					  // increment col
					  col += 2;
					  
				  } // end of semester loop
				  
				  String currentSemId = targetSemester.getSemesterId();
				  type = 'C';
				  // loop for the current/projected course
				  for(Dates currDate : targetSemester.dateList)
				  {
					  double tempNormalDate = currDate.getNormalizedDate();
					  row++;
					  for(Course currCourse : currDate.getAllCourse())
					  {
						  String tempCourseName = currCourse.getCourseName();
						  int tempEnrollment = currCourse.getOverallEnrollment();
						  DetailedProjectionReport reportObj = new DetailedProjectionReport(tempCourseName, type, row, col, currentSemId, tempNormalDate, tempEnrollment);
						  detailedList.add(reportObj);
				  	  } // end course loop
				  }// end date loop
				  
				  col += 2;
				  type = 'P';
				  int courseProjection = 0;
				  int historicalLatest = 0;
				  
				  for(Course targetCourse : targetSemester.dateList.getLast().getAllCourse())
					{
					  	row = 0;
					  	String lastCurrName = targetCourse.getCourseName();
					  	int lastCurrEnr = targetCourse.getOverallEnrollment();
					  	double lastCurrNormDate = targetSemester.dateList.getLast().getNormalizedDate();
					  	
					  	// get the last snapshot of the current semester and add it to the list
					  	DetailedProjectionReport lastCurr = new DetailedProjectionReport(lastCurrName, type, row, col, currentSemId, lastCurrNormDate, lastCurrEnr); 
					  	detailedList.add(lastCurr);
					  	
						for (Course histCourse : historicalList.getLast().dateList.getLast().getAllCourse())
						{
							if (histCourse.getCourseName().equals(targetCourse.getCourseName()))
							{			
								// calculate projection in here
								// This value will be h(d'), interpolated or not. 
							  
								
								
								historicalLatest = semesterProjection.interpolateHistoricalEnrollment(targetCourse.getCourseName(), targetSemester.dateList.getLast().getNormalizedDate()); //this is h(d')
							    // calculate the projection using all the variables
								courseProjection = semesterProjection.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest );
								row++;
								assertThat(semesterProjection.interpolateHistoricalEnrollment(targetCourse.getCourseName(), targetSemester.dateList.getLast().getNormalizedDate()), equalTo(projPar4.interpolateHistoricalEnrollment(targetCourse.getCourseName(), targetSemester.dateList.getLast().getNormalizedDate())));
								
							    //courseProjection = projPar3.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest );
							    assertThat(semesterProjection.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest ), equalTo(projPar4.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest ) ));
								
								DetailedProjectionReport projected = new DetailedProjectionReport(lastCurrName, type, row, col, currentSemId, 1.0, courseProjection);
								detailedList.add(projected);
							}
						} // end histCourse loop
						
					} // end targetCourse loop
				
				  

				
				  //projPar4.printSummaryReport();
					
					//LinkedList<Semester> historicalList = new LinkedList<Semester>(semList2);
					//int courseProjection; 	
														
					// store the target date d', for c(d')
					double nrmlTarg = targetSemester.getLastDate().getNormalizedDate();
					
					// convert nrmlTarg to percentage
					double nrmlTargParam = nrmlTarg * 100;

					// create summaryReport obj with the normalized date as param
					SummaryProjectionReport summaryReport = new SummaryProjectionReport(nrmlTargParam);
					
					// copy the allCourse list from last Date node in the target semester
					// essentially a list of c(d')
					List<Course> targetCourseList = new LinkedList<Course>(targetSemester.getLastDate().getAllCourse());
					// essentially a list of h(1)
					List<Course> histCourseList = new LinkedList<Course>();
					
					histCourseList.addAll((historicalList).getLast().dateList.getLast().getAllCourse());
					
					// print summary header
					///summaryReport.printSummaryReportHeader();
					
					for(Course targetCourse : targetCourseList)
					{
						for (Course histCourse : histCourseList)
						{
							if (histCourse.getCourseName().equals(targetCourse.getCourseName()))
							{			
								// calculate projection in here
								// This value will be h(d'), interpolated or not. 
							   historicalLatest = semesterProjection.interpolateHistoricalEnrollment(targetCourse.getCourseName(), nrmlTarg); //this is h(d')
							    // calculate the projection using all the variables
							    assertThat(semesterProjection.interpolateHistoricalEnrollment(targetCourse.getCourseName(), nrmlTarg), equalTo(projPar4.interpolateHistoricalEnrollment(targetCourse.getCourseName(), nrmlTarg)) );
								
							    courseProjection = semesterProjection.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest );
							    assertThat(semesterProjection.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest ), equalTo(projPar4.calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest ) ));
								
								//summaryReport.printSummaryReport(targetCourse.getCourseName(), targetCourse.getOverallEnrollment(), courseProjection, targetCourse.getOverallCap());
							}
						}
						
					}*/
					//projPar4.printDetailedReport();
		
		
	//}
	
	

}




