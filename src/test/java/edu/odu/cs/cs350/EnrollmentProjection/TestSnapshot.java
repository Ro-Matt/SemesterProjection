package edu.odu.cs.cs350.EnrollmentProjection;

import static org.junit.Assert.*;

import org.junit.Test;


import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Before;
import static org.hamcrest.Matchers.*;
//import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.IsNull;


import java.util.Arrays;
import java.util.Iterator;


import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSnapshot {

	Snapshot defConst;
	Snapshot parConst;
	
	@Before
	public void setUp()
	{
		defConst = new Snapshot(); // default
		parConst = new Snapshot(1, "SUBJ", "CRSE", 1, 1, 1,"2016-02-01"); //non-default
	}
	
	
	@Test
	public void testDefaultConstructor()
	{
		assertNotNull(defConst);
		defConst.setCRN(1);
		defConst.setSUBJ("SUBJ");
		defConst.setCRSE("CRSE");
		defConst.setCourseName("SUBJ","CRSE");
		defConst.setENR(1);
		defConst.setCap(1);
		defConst.setOverallEnrollment(1);
		defConst.setcalendarDate("0000-00-00");

		assertEquals(defConst.getOverallEnrollment(), 1);	
		// See if getCap() has changed
		assertEquals(defConst.getCap(), 1);
		// See if getENR has changed
		assertEquals(defConst.getENR(), 1);
		// See if getCRSE has changed
		assertEquals(defConst.getCRSE(), "CRSE");
		// See if getSUBJ() has changed
		assertEquals(defConst.getSUBJ(), "SUBJ");
		// See if getcourseName has changed
		assertEquals(defConst.getCourseName(), "SUBJ CRSE");
		// See if getCRN() has changed
		assertEquals(defConst.getCRN(), 1);
		assertThat(defConst.getCalendarDate(), containsString ("0000-00-00"));
		
		//assertThat(defConst,containsString ("\n\tSUBJ CRSE\t1\t\t1")); 
		
		
	}
	
	@Test
	public void testEquals()
	{
		assertNotEquals(defConst, parConst);
	}
	
	@Test
	public void testParameterizedConstructor()
	{
		assertNotNull(parConst);
		
		// Check parameterized construction
		assertEquals(parConst.getCRN(), 1);
		assertEquals(parConst.getSUBJ(), "SUBJ");
		assertEquals(parConst.getCRSE(), "CRSE");
		assertEquals(parConst.getENR(), 1);
		assertEquals(parConst.getCap(), 1);
		assertEquals(parConst.getOverallEnrollment(), 1);
		assertThat(parConst.getCalendarDate(), containsString ("2016-02-01"));
		// Create a copy for comparison
		Snapshot parCopy = new Snapshot(1, "SUBJ", "CRSE", 1, 1, 1,"2016-02-01");
		
		// Test setCRN
		parConst.setCRN(0);
		assertEquals(parConst.getCRN(), 0);
	
		// Test setSUBJ
		parConst.setSUBJ(null);
		assertEquals(parConst.getSUBJ(), null);
		
		// Test setCRSE
		parConst.setCRSE(null);
		assertEquals(parConst.getCRSE(),null);
		
		// Test setCourseName
		parConst.setCourseName(" ", " ");
		assertEquals(parConst.getCourseName(),"   ");
		
		// Test setENR
		parConst.setENR(0);
		assertEquals(parConst.getENR(), 0);
		
		// Test setCap
		parConst.setCap(0);
		assertEquals(parConst.getCap(), 0);
		
		// Test setOverallEnrollment
		parConst.setOverallEnrollment(0);
		assertEquals(parConst.getOverallEnrollment(), 0);
		
		// Test set setCalendarDate
		parConst.setcalendarDate("0000-00-00");
		assertEquals(parConst.getCalendarDate(),"0000-00-00");
		
		assertNotEquals(parConst, parCopy);
		
	}
	
	 @Test
	  public void testToStringConstructor()
	   {
	       
		Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");
		 	
	    final String expectedOutput = "\n\tCS 350\t1\t\t1";
	    final String actualOutput = cs350Snapshot.toString();
	        
	        //assertThat(actualOutput, startsWith("\n"));
	      
	        //assertThat(actualOutput, containsString("\tCS 350\t1\t\t1"));
	    assertTrue(actualOutput.equals(expectedOutput));
	        
	    }
}
