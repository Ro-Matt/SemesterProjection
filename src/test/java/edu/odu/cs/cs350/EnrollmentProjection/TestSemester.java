package edu.odu.cs.cs350.EnrollmentProjection;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSemester {
	Semester testSemDef;
	Semester testSemPar;
	
	
	@Before
	public void BeforeEach()
	{
		testSemDef = new Semester();
		testSemPar = new Semester("1", "2020-01-01", "2020-01-05");
	}

	@Test
	public void testDefaultConstructor()
	{
		
		assertNotNull(testSemDef);
		
		// test default semesterId
		assertEquals(testSemDef.getSemesterId()," ");
		
		// Test setSemesterID
		testSemDef.setSemesterId("1");
		assertEquals(testSemDef.getSemesterId(),"1");
		
		// Test default semesterId not equal par setSemesterID
		testSemDef.setSemesterId("2");
		assertNotEquals(testSemDef.getSemesterId(),testSemPar.getSemesterId());
		
		// test default startDate
		assertEquals(testSemDef.getStartDate(), " ");
		
		// test default endDate
		assertEquals(testSemDef.getEndDate(), " ");
		
		// make sure dateList was allocated
		assertNotNull(testSemDef.dateList);
		
		// test to see if dateList is empty
		assertTrue(testSemDef.dateList.isEmpty());
		
		

	}
	@Test
	public void testParameterizedConstructor()
	{
		assertNotNull(testSemPar);
		
		// Check parameterized construction
		assertEquals(testSemPar.getSemesterId(), "1");
		
		// Test setSemesterID
		testSemDef.setSemesterId("3");
		assertEquals(testSemDef.getSemesterId(),"3");
		
		// Test default semesterId not equal par setSemesterID
		testSemDef.setSemesterId("2");
		assertNotEquals(testSemDef.getSemesterId(),testSemPar.getSemesterId());
		
		// test default startDate
		assertThat(testSemPar.getStartDate(), containsString("2020-01-01"));
		
		// test default endDate
		assertThat(testSemPar.getEndDate(), containsString("2020-01-05"));
		
		// make sure dateList was allocated
		assertNotNull(testSemPar.dateList);
		
		// test to see if dateList is empty
		assertTrue(testSemPar.dateList.isEmpty());
		
	}

	@Test
	public void testCopyConstructor()
	{
		// create Semester obj. using copy constructor
		Semester testSemCpy = new Semester(testSemPar);
		
		// test all data members
		assertEquals(testSemCpy.getSemesterId(), testSemPar.getSemesterId());
		assertEquals(testSemCpy.getStartDate(), testSemPar.getStartDate());
		assertEquals(testSemCpy.getEndDate(), testSemPar.getEndDate());
		assertEquals(testSemCpy.dateList, testSemPar.dateList);
	}
	@Test
	public void testSetSemesterId()
	{
		assertNotNull(testSemPar);
		
		// Check default construction
		assertEquals(testSemDef.getSemesterId(), " ");
		
		// Check parameterized construction
		assertEquals(testSemPar.getSemesterId(), "1");
		
		// Test setSemesterID
		testSemDef.setSemesterId("2");
		assertEquals(testSemDef.getSemesterId(),"2");
		
		// Test default semesterId not equal par setSemesterID
		testSemDef.setSemesterId("2");
		assertNotEquals(testSemDef.getSemesterId(),testSemPar.getSemesterId());
		
	}
	
	@Test 
	public void testAdd()
	{
		Snapshot  Snapshot1 = new Snapshot(1, "CS", "120", 1, 1, 1, "2016-02-01");
		Snapshot  Snapshot2 = new Snapshot(2, "CS", "121G", 1, 1, 1, "2016-02-02");
		Snapshot  Snapshot3 = new Snapshot(2, "CS", "250", 1, 1, 1, "2016-02-01");
		
		// Since Semester both creates Dates objects and inserts them into
		// the list, we need to make sure they are being created properly, as well
		// as inserted into the list correctly. These are for comparison.
		Dates date1 = new Dates();
		date1.add(Snapshot1);
		
		Dates date2 = new Dates();
		date2.add(Snapshot2);
		
		// First test the default Semester object
		
		// Make sure it is empty
		assertTrue(testSemDef.dateList.isEmpty());
		
		//Add a snapshot
		testSemDef.add(Snapshot1);
		
		// Test to see if the list is no longer empty, and check its size
		assertTrue(!testSemDef.dateList.isEmpty());
		assertEquals(testSemDef.dateList.size(), 1);

		// Test to see if the object was created, in the correct spot in the list,
		// and is equal to the test object date1
		assertEquals(testSemDef.dateList.element(), date1);
		
		// test getLastDate
		assertEquals(testSemDef.getLastDate(), date1);
		
		// Add next snapshot
		testSemDef.add(Snapshot2);
		
		// make sure a new date node is created and added to the end of the linked list,
		// and that it is equal to the test object date2
		assertEquals(testSemDef.dateList.size(), 2);
		assertEquals(testSemDef.getLastDate(), date2);

		// Add third snapshot
		testSemDef.add(Snapshot3);
		
		// make sure a third node was NOT created, since the corresponding date was
		// already added to the linked list
		assertEquals(testSemDef.dateList.size(), 2);
		
	}
	
	@Test
	public void testCalcTimeElapsed()
	{
		// For the start and end dates in testSemPar, the following
		// dates should give the corresponding normalized dates
		// Snapshot1 = 01/02/2020 -> 25%  = 0.25
		// Snapshot2 = 01/03/2020 -> 50%  = 0.50
		// Snapshot3 = 01/04/2020 -> 75%  = 0.75
		// Snapshot4 = 12/31/2019 -> -25% = -0.25
		
		// snapshot4 should = 0.0 when the normalized date is calculated
		Snapshot Snapshot1 = new Snapshot(1, "CS", "120", 1, 1, 1, "2020-01-02");
		Snapshot Snapshot2 = new Snapshot(2, "CS", "121G", 1, 1, 1, "2020-01-03");
		Snapshot Snapshot3 = new Snapshot(2, "CS", "250", 1, 1, 1, "2020-01-04");
		Snapshot Snapshot4 = new Snapshot(2, "CS", "669", 1, 1, 1, "2019-12-31");  
		
		// delta between an actual double and an expected double in assertEquals
		double delta = 0.009;
		
		// Add snapshots to testSemPar to create four nodes of Dates objects
		testSemPar.add(Snapshot1);
		testSemPar.add(Snapshot2);
		testSemPar.add(Snapshot3);
		testSemPar.add(Snapshot4);
		// calculate the normalized dates
		testSemPar.calcTimeElapsed();
		
		// test the dates
		assertEquals(testSemPar.dateList.get(0).getNormalizedDate(), 0.25, delta);
		assertEquals(testSemPar.dateList.get(1).getNormalizedDate(), 0.50, delta);
		assertEquals(testSemPar.dateList.get(2).getNormalizedDate(), 0.75, delta);
		// make sure no negative dates were added
		assertEquals(testSemPar.dateList.get(3).getNormalizedDate(), 0.0, delta);
		
	}
	 @Test
	    public void testToStringConstructor()
	    {
		 //Course defualtCourse5=new Course();
		 Semester defaultSemester5=new Semester("202030","2020-10-19","2021-05-19");
		
	     Snapshot  cs350Snapshot= new Snapshot(1, "CS", "350", 1, 1, 1,"2016-02-01");
	     
	       
	     defaultSemester5.add(cs350Snapshot);
			 
	        final String expectedOutput = "Semester level\n202030\n  Date Level:2016-02-01\n  Course Level:CS 350\n  \n\tCS 350\t1\t\t1";
	        final String actualOutput = defaultSemester5.toString();

	        // Check the pieces of the resulting string (machine Floating Point)
	        //assertThat(actualOutput, startsWith("Course Level:CS 350\n"));
	        //assertThat(actualOutput, containsString("  \n\tCS 350\t1\t\t1"));
	       // assertThat(actualOutput, containsString("  \n\tCS 350\t1\t\t1"));
	        
	        assertTrue(actualOutput.equals(expectedOutput));
	    }
	
	
}
