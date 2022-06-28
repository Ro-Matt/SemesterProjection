package edu.odu.cs.cs350.EnrollmentProjection;

import java.io.*;
import java.text.DecimalFormat;

/**
 * This is the Summary Projection Report class.
 * overall the duties of the report
 * is to print the summary report on the terminal output
 *
 * Its internal methods are described
 * below.
 *
 */
public class SummaryProjectionReport {

	Double summaryPercent;

	/**
     * Construct a SummaryProjectionReport from a provided percent
     * @param percent desired percent
     */
	SummaryProjectionReport(Double percent) {
		summaryPercent = percent;
	}

	/**
	 * printSummaryReportHeader
	 *
	 * This function prints the header information for the summary report.
	 * Print the summary percent for the first line.
	 * Output is sent to standard out
	 */
	public void printSummaryReportHeader() {

		Double summaryPercentValue;
		DecimalFormat dFormat = new DecimalFormat("##");

		summaryPercentValue = getSummaryPercent();

		System.out.print(" " + dFormat.format(summaryPercentValue) + "% of enrollment period has elaped.");
		//System.out.print("\n\tCourse\t" + "Enrollment\t" + "Projected\t" + "Cap");
		String outputHeadertCourse=" Course";
		String outputHeaderEnrollment= "Enrollment";
		String outputHeadertProjected="Projected" ;
		String outputHeaderCap="Cap";
		 System.out.printf("\n%8s\t%5s\t%8s\t %5s", outputHeadertCourse, outputHeaderEnrollment, outputHeadertProjected,outputHeaderCap);
	}

	/**
	 * printSummaryReportHeader
	 *
	 * This function prints one line of data for each course.
	 * Output is sent to standard out
	 * @param courseName desired coursename
     * @param overallEnrollment desired overallenrollment
     * @param projection desired projection
     * create overallCap ArrayList of overallcap
	 */
	public void printSummaryReport(String courseName, int overallEnrollment, int projection, int overallCap) {
		String NewcourseName=" ";
		String Newprojection=" ";
		if (projection>overallCap)
		{
			NewcourseName="*"+courseName;
		}
		else {
			NewcourseName=" "+courseName;
		}
		if (projection<0){
			Newprojection="N/A";
		}
		else 
		{
			Newprojection=String.valueOf( projection);
		}
		 System.out.printf("\n%8s\t%3d\t\t%8s\t   %3d", NewcourseName, overallEnrollment, Newprojection,overallCap);
	        //System.out.printf("\t%03d", overallEnrollment);
	       // System.out.printf("\t%5s", Newprojection);
	      //  System.out.printf("\t%03d", overallCap);
	        
		//System.out.print("\n\t" + NewcourseName + " " + overallEnrollment
				//+ "\t\t" + Newprojection + "\t\t" + overallCap);

	}

	/**
	 * getSummaryPercent
	 *
	 * This function returns the summaryPercent for
	 * the given SummaryReport.  Required for summary report
	 * @return summaryPercent object
	 */
	public Double getSummaryPercent()
	{
		return summaryPercent;
	}

}
