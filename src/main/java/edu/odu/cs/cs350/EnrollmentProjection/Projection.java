package edu.odu.cs.cs350.EnrollmentProjection;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
/**
 * This is the Projection class.  The main purpose of this class
 * is to calculate the projected enrollment for every class in the target semester.
 * It also calculates normalized dates for every Course node in the allCourse linked list
 * as well as invokes the Summary and Detailed reports so they can be generated.
 */

public class Projection {
	/**
	 * Stores the SemesterId of the target semester.
	 */
	private String targetID;   		
	/**
	 * Stores the enrollment projection for a given course in the 
	 * target semester.
	 */
	private int enrollmentProjection;
	
	/**
	 * Stores the output directory of the excel spreadsheet
	 */
	private String detailedOutput;
	/**
	 * A linked list of historical semester objects.
	 */
	LinkedList<Semester> historicalList = null; // to be implemented in increment 2
	/**
	 * The target semester.
	 */
	Semester targetSemester = null;				// target semester
	
	/**
	 * Default Constructor -  initialize all data.
	 * set targetID to an empty string " ".
	 * Allocate historical list.
	 * Allocate targetSemester.
	 */
	Projection()
	{
		targetID = null;
		detailedOutput = null;
		historicalList = new LinkedList<Semester>();
		targetSemester = new Semester();
	}
	/**
	 * Construct a projection from the given hList and tSem.
	 * @param hList desired historicalList.
	 * @param tSem desired targetSemester.
	 * Sets targetID member using tSem.
	 * Calls the calcTimeElapsed() method in each Semester object.
	 */
	Projection(List<Semester> hList, Semester tSem, String detailedOutput)
	{
		// allocate historicalList with h_list
		historicalList = new LinkedList<Semester>(hList);
		
		// allocate target semester
		targetSemester = new Semester(tSem);
		
		// store ID of target semester
		targetID = targetSemester.getSemesterId();
		
		// Store the directory of the detailed project report
		this.detailedOutput = detailedOutput;
		// calculate normalized dates for all historical semesters
		for(Semester obj : historicalList)
		{
			obj.calcTimeElapsed();
		}
		
		// caluclate normalized dates for the target semester
		targetSemester.calcTimeElapsed();
	}
	
	/*
	 * printSummaryReport creates a summaryProjectionReport object using the % time elapsed.
	 * This method repeatedly calls the printSummaryReport method in summaryProjectionReport.
	 */
	public void printSummaryReport()
	{
		int courseProjection; 	
											
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
		histCourseList.addAll(historicalList.getLast().dateList.getLast().getAllCourse());
		
		// print summary header
		summaryReport.printSummaryReportHeader();
		
		for(Course targetCourse : targetCourseList)
		{
			for (Course histCourse : histCourseList)
			{
				if (histCourse.getCourseName().equals(targetCourse.getCourseName()))
				{			
					// calculate projection in here
					// This value will be h(d'), interpolated or not. 
				    int historicalLatest = interpolateHistoricalEnrollment(targetCourse.getCourseName(), nrmlTarg); //this is h(d')
				    // calculate the projection using all the variables
					courseProjection = calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest );
					
					summaryReport.printSummaryReport(targetCourse.getCourseName(), targetCourse.getOverallEnrollment(), courseProjection, targetCourse.getOverallCap());
				}
			}
			
		}
	}
	
	/**
	 *printDetailedReport  makes a collection of DetailedReportProjection objects
	 *from the semester directories given in the command line arguments.   
	 *The collection and output directory are sent to the printDetailReport
	 *
	 */
	public void printDetailedReport()
	{
		// push objects on to this
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
				  if(tempNormalDate <= 1.10)
				  {
					  row++;
					  // iterate through the list of courses for this snapshot date
					  for(Course histCourse : histDate.getAllCourse())
					  {
						  String tempCourseName = histCourse.getCourseName();
						  int tempEnrollment = histCourse.getOverallEnrollment();
						  
						  DetailedProjectionReport reportObj = new DetailedProjectionReport(tempCourseName, type, row, col, tempSemId, tempNormalDate, tempEnrollment);
						  detailedList.add(reportObj);
					  } // end of course loop
				  }
				  
				  
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
			  if(tempNormalDate <= 1.10)
				  {
				  	row++;
					for(Course currCourse : currDate.getAllCourse())
					  {
						  String tempCourseName = currCourse.getCourseName();
						  int tempEnrollment = currCourse.getOverallEnrollment();
						  DetailedProjectionReport reportObj = new DetailedProjectionReport(tempCourseName, type, row, col, currentSemId, tempNormalDate, tempEnrollment);
						  detailedList.add(reportObj);
					  } // end course loop
				  } //end of if
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
					    historicalLatest = interpolateHistoricalEnrollment(targetCourse.getCourseName(), targetSemester.dateList.getLast().getNormalizedDate()); //this is h(d')
					    // calculate the projection using all the variables
						courseProjection = calculateEnrollmentProjection(targetCourse.getOverallEnrollment(), histCourse.getOverallEnrollment(), historicalLatest );
						row++;
						
						DetailedProjectionReport projected = new DetailedProjectionReport(lastCurrName, type, row, col, currentSemId, 1.0, courseProjection);
						detailedList.add(projected);
					}
				} // end histCourse loop
				
			} // end targetCourse loop
		
		  DetailedProjectionReport callPrint = new DetailedProjectionReport();
	      callPrint.printDetailReport(detailedList, detailedOutput);
		  //this.SendprintDetailedReport(detailedList);
	}
	
	/**
	 * interpolateHistoricalEnrollment takes a course name and a normalized date 
	 * to search the last historical semester in historicalList and current semester as
	 * targetSemester.  The integer computed is h(d')
	 * 
	 * @param courseName
	 * @param targetNormalDate
	 * @return integer h(d')
	 */
	public int interpolateHistoricalEnrollment(String courseName, double targetNormalDate)
	{
		// Linear Interpolation
		// h(d') = h(d_a) + [(d - d_a) / (d_b - d_a)](h(d_b) - h(d_a)
		// Where d_a and d_b are normalized dates such that d_a < d' < d_b
		// h(d') is the return value
		
		double historicalEnrollment = 0;
		// date d'_a
		double normalDateBefore = 0;
		// enrollment at d'_a
		int enrAtDateBefore = 0;
		// date d'_b
		double normalDateAfter = 0;
		// enrollment at d'_b 
		int enrAtDateAfter = 0;
		
		// Create a list of dates from the last historical semester
		LinkedList<Dates> latestSemesterDates = new LinkedList<Dates>();
		latestSemesterDates.addAll(historicalList.getLast().dateList);
		
		boolean dateFound = false;
		// if d' is found in the latest historical semester, return the enrollment at that course for that date
		for(Dates histDate : latestSemesterDates)
		{
			if(histDate.getNormalizedDate() == targetNormalDate)
			{
				for(Course course : histDate.getAllCourse())
				{
					if(course.getCourseName().equals(courseName))
					{
						historicalEnrollment = course.getOverallEnrollment();
						dateFound = true;
						break;
					}
				} // end inner for
			}
			if(dateFound)
				break;
		}
		if(dateFound)
		{
			int returnVal =  (int) Math.round(historicalEnrollment);
			return returnVal;
		}
		
		// IF we cannot find a date in the latest semester such that d'_hist = d'_target,
		// we have to find the closest date.  
					
			/*
			 * Increment a temporary normalized date, starting at targetNormalDate and search through the 
			 * list of historical dates until date d'_b is found
			 */
			double tempNormalDate = targetNormalDate;
			// reset flag
			dateFound = false;
			while(tempNormalDate <= 1.0 | !dateFound)
			{
				for (Dates histDate : latestSemesterDates)
				{
					if(histDate.getNormalizedDate() == tempNormalDate)
					{
						normalDateAfter = histDate.getNormalizedDate();
						for(Course atDate : histDate.getAllCourse())
						{
							if(atDate.getCourseName().equals(courseName))
							{
								enrAtDateAfter = atDate.getOverallEnrollment();
								dateFound = true;
								break;
							}
							
						} // end inner for
						
					}
					if (dateFound)
						break;
				}// end outer for
				if(!dateFound)
					tempNormalDate += 0.01;
			}// end while
			
			
				
			/*
			 * Decrement a temporary normalized date, starting at d'_target and search through the 
			 * list of historical dates until date d'_a is found
			 */
		
			// reset flag and counter
			dateFound = false;
			tempNormalDate = targetNormalDate;
			
			while(tempNormalDate > -0.0 | !dateFound)
			{
				for (Dates histDate : latestSemesterDates)
				{
					
					if(histDate.getNormalizedDate() == tempNormalDate)
					{
						normalDateBefore = histDate.getNormalizedDate();
						for(Course atDate : histDate.getAllCourse())
						{
							if(atDate.getCourseName().equals(courseName))
							{
								enrAtDateBefore = atDate.getOverallEnrollment();
								dateFound = true;
								break;
							}
						} // end inner for
					}
					if(dateFound)
						break;
				} // end for
				if(!dateFound)
					tempNormalDate -= 0.01;
			} // end while
			
			// now that the dates and enrollments at those dates are saves, we can calculate the interpolation of
			// h(d') and return that value
			// linear interpolation: h(d') = h(d_a) + [(d - d_a) / (d_b - d_a)](h(d_b) - h(d_a)
			historicalEnrollment = enrAtDateBefore + ( ((targetNormalDate - normalDateBefore) / (normalDateAfter - normalDateBefore)) * (enrAtDateAfter - enrAtDateBefore));
			int returnVal = (int) Math.round(historicalEnrollment);
			
			return returnVal;
	}
	
	
	/**
	 * calculateEnrollmentProjection uses the last semester in historicalList and targetSemester 
	 * to calculate the projected enrollment on the add/drop deadline for
	 * the target semester.  This value is returned as an integer
	 * @param targLatest enrollment at c(d')
	 * @param histAddDrop enrollment at h(1)
	 * @param histLatest enrollment at h(d')
	 * @return integer enrollment projection
	 */
	public int calculateEnrollmentProjection(int targLatest, int histAddDrop, int histLatest  )
	{
		// c(1)  -> local scope variable targetProjection, return value.
		// c(d') -> targetLatest
		// h(1)  -> histAddDrop
		// h(d') -> histLatest, from interpolateHistoricalEnrollment
		
		// Expression
		// c(1)/h(1) = c(d')/h(d')
		// solving for c(1)
		// c(1) = h(1) [c(d')/h(d')]
		double calculation = 0;		// double to int conversion
		int targetProjection = 0;	// The projected enrollment c(1) to be calculated. Return value.
		
		if (histLatest == 0)
			return -1;
		else
		{
			calculation = (double)histAddDrop * ((double)targLatest / (double)histLatest);
			targetProjection = (int)Math.round(calculation);
			if(targetProjection != 0)
				return targetProjection;
			else
				return -1;
		}
		
	}
	/*
	public void SendprintDetailedReport(ArrayList<DetailedProjectionReport> detailedList)
	{
		
		Collections.sort(detailedList);
		DetailedProjectionReport callPrint = new DetailedProjectionReport();
	    
	    try{
	    	Formatter x = new Formatter("REPORTTHESTUFF.txt");
	    	x.format("CRName  " + "Row  " + "Col  " + "Date  " + "Type  \n");
	    	for(DetailedProjectionReport obj:detailedList) {
	    		x.format(obj.getCourseName() + "   " + obj.getRow() + "   " + obj.getCol() + "   " + obj.getNormalizedDate() + "   " + obj.getType() + "   \n");
	    	}
	    }catch(Exception e) {
	    	System.out.println("Can't create file!!!");
	    };
		callPrint.printDetailReport(detailedList, detailedOutput);
		//detailedList.stream().map(s -> s.getCourseName()).forEach(System.out::print);
			
	}
	
	 */
	
	/**
	 * This function returns a semesterID
	 * @return targetID the semesterID of a given semester.
	 */
	public String getTargetID() {
		return targetID;
	}
	
}