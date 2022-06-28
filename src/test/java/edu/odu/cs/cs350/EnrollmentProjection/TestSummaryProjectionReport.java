package edu.odu.cs.cs350.EnrollmentProjection;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestSummaryProjectionReport {
	SummaryProjectionReport testSemDef;
	SummaryProjectionReport testSemPar;
	
	@Before
	public void BeforeEach()
	{
		//testSemDef = new Semester();
		testSemPar = new SummaryProjectionReport(1.0);
	}

	@Test
	public void testParameterizedConstructor()
	{
		assertNotNull(testSemPar);
		
		// Check parameterized construction
		assertEquals(testSemPar.getSummaryPercent(), 1.0,1.0);
		
		// Test setSemesterID
		assertNotEquals(testSemPar.getSummaryPercent(),3);
		

		}
	@Test
	public void testprintSummaryReport()
	{
		  	//PrintStream originalOut = System.out;
	        //ByteArrayOutputStream testStandardOut = new ByteArrayOutputStream();

	        // Test with output capture
	       // System.setOut(new PrintStream(testStandardOut));

	        

	       // System.setOut(originalOut);
	        // End Output capture
	        PrintStream save_out=System.out;
	        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));

	        testSemPar.printSummaryReport("CS 252", 212, 257, 240);
	       String NewcourseName="*CS 252";
	       int overallEnrollment=212;
	       String Newprojection="257";
	       int overallCap=240;
	       System.out.printf("\n%8s\t%3d\t\t%8s\t   %3d", NewcourseName, overallEnrollment, Newprojection,overallCap);
	       
	       //System.out.printf("  *CS 252        212                  257           240");
	      // assertThat("  *CS 252        212                  257           240",containsString(outContent.toString()));
	      // assertThat(testSemPar.printSummaryReport("CS 252", 212, 257, 240),containsString(out.toString()));
	       
	       
	       
	        System.setOut(save_out);
	        
	        
	        testSemPar.printSummaryReport("CS 411w", 42, 41, 140);
		       NewcourseName=" CS 411w";
		       overallEnrollment=42;
		      Newprojection="41";
		       overallCap=140;
		       System.out.printf("\n%8s\t%3d\t\t%8s\t   %3d", NewcourseName, overallEnrollment, Newprojection,overallCap);
		       
		       System.setOut(save_out);
		       
		       testSemPar.printSummaryReport("CS 669", 1, -1, 0);
		       NewcourseName=" CS 669";
		       overallEnrollment=1;
		      Newprojection="N/A";
		       overallCap=0;
		       System.out.printf("\n%8s\t%3d\t\t%8s\t   %3d", NewcourseName, overallEnrollment, Newprojection,overallCap);
		       
		       System.setOut(save_out);
	        //String resultStr = "\n%8s*CS 252\t%3d212\t\t%8s\t   %3d", NewcourseName, overallEnrollment, Newprojection,overallCap;

	/*       assertThat(resultStr, containsString("Test Output"));

	      

	        assertThat(resultStr, stringContainsInOrder(complexAsStrings));
		
		public void printSummaryReport() {
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
		     
*/
		//}

		}
	@Test
	public void testprintSummaryReportHeader()
	{
		
		SummaryProjectionReport testSemDef5= new SummaryProjectionReport(79.0);
		PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        testSemDef5.printSummaryReportHeader();
		Double summaryPercentValue;
		DecimalFormat dFormat = new DecimalFormat("##");

		summaryPercentValue = testSemDef5.getSummaryPercent();

		System.out.print(" " + dFormat.format(summaryPercentValue) + "% of enrollment period has elaped.");
		//System.out.print("\n\tCourse\t" + "Enrollment\t" + "Projected\t" + "Cap");
		
		
		 // assertEquals( " 79% of enrollment period has elaped.", out.toString());
		
		System.setOut(save_out);
		
		
		
		
		/*String outputHeadertCourse=" Course";
		String outputHeaderEnrollment= "Enrollment";
		String outputHeadertProjected="Projected" ;
		String outputHeaderCap="Cap";
		 System.out.printf("\n%8s\t%5s\t%8s\t %5s", outputHeadertCourse, outputHeaderEnrollment, outputHeadertProjected,outputHeaderCap);
*/
		}

}






