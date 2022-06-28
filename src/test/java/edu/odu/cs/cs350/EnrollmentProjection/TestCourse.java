package edu.odu.cs.cs350.EnrollmentProjection;

import org.junit.Test;
import java.util.Iterator;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;



import org.junit.Before;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class TestCourse  {

	Course defualtCourse;
	
	@Before
    public void setUp()
    {
		defualtCourse=new Course();

    }
	 @Test
	public void testDefaultConstructor()
	{
		 //fail("Not yet implemented");	
		 assertThat(defualtCourse.getCourseName(), containsString ("none"));
		 assertThat(defualtCourse.size(), is(0));
		 assertThat(defualtCourse. getOverallCap(), is(0));
		 assertThat(defualtCourse. getOverallEnrollment(), is(0));
	    }

	 @Test//20160201
	public void testAdd()
	{
		// fail("Not yet implemented");
		 Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");
		 Snapshot  cs350Snapshot2= new Snapshot(2, "CS", "350", 1, 1, 1,"2016-02-01");
		 Snapshot  cs250Snapshot= new Snapshot(2, "CS", "250", 1, 1, 1,"2016-02-01");
		 
		 defualtCourse.add(cs350Snapshot);
		 assertThat(defualtCourse.size(), is(1));
		 assertThat(defualtCourse.getOverallCap(), is(1));
		 assertThat(defualtCourse.getOverallEnrollment(), is(1));
		 
		 defualtCourse.add(cs350Snapshot2);
		 assertThat(defualtCourse.size(), is(2));
		 assertThat(defualtCourse.getOverallCap(), is(2));
		 assertThat(defualtCourse.getOverallEnrollment(), is(2));
		 
		 defualtCourse.add(cs250Snapshot);
		 assertThat(defualtCourse.size(), is(2));
		 assertThat(defualtCourse.getOverallCap(), is(2));
		 assertThat(defualtCourse.getOverallEnrollment(), is(2));
		 
		 Iterator<Snapshot> it = defualtCourse.iterator();
		 assertTrue(it.hasNext());
		 assertThat(it.next().getCourseName(), is("CS 350"));
	     assertThat(it.next().getCourseName(), is("CS 350"));
	     assertFalse(it.hasNext());
		 
		 
	    }
	 @Test
	 public void testEquals()
	    {
		// fail("Not yet implemented");
		 Course defualtcourse2=new Course();
		 Course defualtcourse3=new Course();
		 assertThat(defualtcourse2, equalTo(defualtcourse3));
		// assertThat(defualtDate, equalTo(defualtDate2));
		 Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");

		 String defualtcourse4="not course";
		 defualtcourse2.add(cs350Snapshot);
		
		 
		// assertThat(NondefualtDate, equalTo(NondefualtDate3));
		 assertThat(defualtcourse2, not(equalTo(defualtcourse3)));
		 //assertThat(fromOneByOne, equalTo(fromOneByOne));
		 assertThat(defualtcourse3, not(equalTo(defualtcourse4)));
		 
	    }
	 
	 @Test
	    public void testToStringConstructor()
	    {
		 Course defualtCourse5=new Course();
		 	
	       Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");
	     
	       
	       defualtCourse5.add(cs350Snapshot);
			 
	        final String expectedOutput = "Course Level:CS 350\n  \n\tCS 350\t1\t\t1";
	        final String actualOutput = defualtCourse5.toString();

	        // Check the pieces of the resulting string (machine Floating Point)
	        //assertThat(actualOutput, startsWith("Course Level:CS 350\n"));
	        //assertThat(actualOutput, containsString("  \n\tCS 350\t1\t\t1"));
	       // assertThat(actualOutput, containsString("  \n\tCS 350\t1\t\t1"));
	        
	        assertTrue(actualOutput.equals(expectedOutput));
	    }
	 
	 
	 
}

