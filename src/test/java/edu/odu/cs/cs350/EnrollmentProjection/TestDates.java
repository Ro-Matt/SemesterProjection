package edu.odu.cs.cs350.EnrollmentProjection;

import org.junit.Test;
import java.util.Iterator;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestDates { 
	
	Dates defaultDate;
	@Before
    public void setUp()
    {
	defaultDate = new Dates();
    }
	 @Test
	public void testDefaultConstructor()
	{
		 //fail("Not yet implemented");
		 
		 assertThat(defaultDate.getCalendarDate(), containsString("0000-00-00"));
		 assertThat(defaultDate.size(), is(0));
		 assertThat(defaultDate.getNormalizedDate(), is(-1.0));
		 assertNotNull(defaultDate.getAllCourse());
		 assertEquals(defaultDate.getAllCourse().size(), 0);		 
	    }
	
	 @Test
	public void testAdd()
	{
		// fail("Not yet implemented");
		 Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");
		 Snapshot  cs350Snapshot2= new Snapshot(2, "CS", "350", 1, 1, 1,"2016-02-01");
		 Snapshot  cs250Snapshot= new Snapshot(2, "CS", "250", 1, 1, 1,"2016-02-01");
		 Snapshot  cs250Snapshot2= new Snapshot(2, "CS", "250", 1, 1, 1,"2016-02-02");
		 defaultDate.add(cs350Snapshot);
		
		 assertThat(defaultDate.getCalendarDate(), containsString ("2016-02-01"));
		 assertThat(defaultDate.size(), is(1));
		 
		 defaultDate.add(cs350Snapshot2);
		 assertThat(defaultDate.getCalendarDate(), containsString ("2016-02-01"));
		 assertThat(defaultDate.size(), is(1));
		 
		 defaultDate.add(cs250Snapshot);
		 assertThat(defaultDate.getCalendarDate(), containsString ("2016-02-01"));
		 assertThat(defaultDate.size(), is(2));
		 
		 
		 defaultDate.add(cs250Snapshot2);//it should not add date
		 assertThat(defaultDate.getCalendarDate(), containsString ("2016-02-01"));
		 assertThat(defaultDate.size(), is(2));
		 
		 assertNotNull(defaultDate.getAllCourse());
		 assertEquals(defaultDate.getAllCourse().size(), 2); 
		 
		 Iterator<Course> it = defaultDate.iterator();
		 assertTrue(it.hasNext());
		 assertThat(it.next().getCourseName(), is("CS 350"));
	     assertThat(it.next().getCourseName(), is("CS 250"));
	     assertFalse(it.hasNext());
		 
	   
		 
	    }
	 @Test
	 public void testNormalizedDate()
	    {
		// fail("Not yet implemented");
		 defaultDate.setNormalizedDate(0.5);
		 assertThat(defaultDate.getNormalizedDate(), is(0.5));		 
	    }
	 
	 @Test
	 public void testEquals()
	    {
		// fail("Not yet implemented");
		 Dates defaultDate2=new Dates();
		 Dates defaultDate3=new Dates();
		 Dates defaultDate5=new Dates();
		 
		 String defaultDate4="not Date";
		 assertThat(defaultDate2, equalTo(defaultDate3));
	
		 Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");
	 
		 defaultDate2.add(cs350Snapshot);
		 defaultDate5.add(cs350Snapshot);
			
		 assertThat(defaultDate2.getCalendarDate(), equalTo(defaultDate5.getCalendarDate()));
		 assertThat(defaultDate2.getAllCourse(), equalTo(defaultDate5.getAllCourse()));
		 
		 assertThat(defaultDate2, not(equalTo(defaultDate3)));
		 assertThat(defaultDate2, not(equalTo(defaultDate4)));

	    }
	 @Test
	 public void testsetnewDate()
	 {
		 Dates defaultDate2=new Dates();
		 assertThat(defaultDate2.getCalendarDate(), containsString("0000-00-00"));
		 assertThat(defaultDate2.size(), is(0));
		 assertThat(defaultDate2.getNormalizedDate(), is(-1.0));
		 assertNotNull(defaultDate2.getAllCourse());
		 assertEquals(defaultDate2.getAllCourse().size(), 0);
		 
		 defaultDate2.setCalendarDate("2016-12-01");
		 
		 assertThat(defaultDate2.getCalendarDate(), containsString("2016-12-01"));
		 assertThat(defaultDate2.size(), is(0));
		 assertThat(defaultDate2.getNormalizedDate(), is(-1.0));
		 assertNotNull(defaultDate2.getAllCourse());
		 assertEquals(defaultDate2.getAllCourse().size(), 0);
		 
		 
	 }
	 @Test
	    public void testToStringConstructor()
	    {
		 //Course defualtCourse5=new Course();
		 Dates defaultDate5=new Dates();
	     Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");
	     
	       
	     defaultDate5.add(cs350Snapshot);
			 
	        final String expectedOutput = "Date Level:2016-02-01\n  Course Level:CS 350\n  \n\tCS 350\t1\t\t1";
	        final String actualOutput = defaultDate5.toString();

	        // Check the pieces of the resulting string (machine Floating Point)
	        //assertThat(actualOutput, startsWith("Course Level:CS 350\n"));
	        //assertThat(actualOutput, containsString("  \n\tCS 350\t1\t\t1"));
	       // assertThat(actualOutput, containsString("  \n\tCS 350\t1\t\t1"));
	        
	        assertTrue(actualOutput.equals(expectedOutput));
	    }
	 
}

