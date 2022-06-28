package edu.odu.cs.cs350.EnrollmentProjection;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *This is the Semester class.  It's main role
 *is to store the semesterID, which breaks down into
 *the semester's year and term.  Semester also contains the
 *start and end date of the semester.  The main feature of
 *Semester is the LinkedList
 *
 * Semester is the beginning of the date structure that organizes and stores
 * the data required for the projection, summary report, and detailed report.
 */

public class Semester {
	/**
	 * The year and the term of a semester.
	 */



	String semesterId;
	/**
	 * The start of the add/drop deadline.
	 */
	String startDate;
	/**
	 *  The end of the add/drop deadline.
	 */
	String endDate;
	/**
	 * A temporary Dates object used for comparison in the add method.
	 */


	Dates tempDate;
	/**
	 * A linked list of Date objects.
	 * Sorts the snapshots of a semester by date.
	 */
	LinkedList<Dates> dateList;
	/**
     * Default Constructor - initialize all data.
     * set the semesterId to " ".
     * set the startDate to " ".
     * set the endDate to " ".
     * create empty ArrayList of Dates.
     */

	Semester()
	{
		semesterId = " ";
		startDate = " ";
		endDate = " ";
		dateList = new LinkedList<Dates>();
	}
	 /**
     * Construct a Semester from a provided semesterId, startDate, endDate.
     * @param semesterId desired semesterId.
     * @param startDate desired startDate.
     * @param endDate desired endDate.
     * create empty ArrayList of Dates.
     */
	Semester(String semesterId, String startDate, String endDate) {
		this.semesterId = semesterId;
		this.startDate = startDate;
		this.endDate = endDate;
		dateList = new LinkedList<Dates>();
	}
	/**
	 * Construct a Semester from another Semester object.
	 * @param Semester object to be copied
	 *
	 */
	Semester(Semester obj)
	{
		this.semesterId = obj.getSemesterId();
		this.startDate = obj.getStartDate();
		this.endDate = obj.getEndDate();
		this.dateList = new LinkedList<Dates>(obj.dateList);
	}
	/**
     * Add a Snapshot to the 'Dates' collection.
     * Rule for Adding a Snapshot:
     *
     *  1: the first Snapshot that gets added to new Dates
     *  object and then this get added to the collection.
     *
     *  2: after the first Snapshot this function will
     *  iterate and check if the Snapshot CalendarDate
     *  matches any other Dates that already exist in
     *  in the collection.
     *
     *  If the CalendarDate is the same, Snapshot
     *  get added to that object inside the collection
     *  but it is not add to this collection.
     *
     *  If the CalendarDate are different the snapshot
     *  get added to a new object Date and then this
     *  get add to this collection.
     * @param toAdd this is the snapshot that will be
     * added to the collection once this has been add
     * to the Dates.
	 */
	public void add(Snapshot toAdd)
	{
		/*
		 * If dateList is empty, create anew Dates
		 * obj and set it as the head of the list.
		 *
		 * allocate a new date obj
		 * initialize Date obj snapshot
		 * add to front of list
		 */
		if (dateList.isEmpty())
		{
			tempDate = new Dates();
			tempDate.add(toAdd);
			dateList.addFirst(tempDate);
		}
		else
		{
			/*
			 * iterate through the linked list and look for
			 * a place to either insert a new Dates object, or
			 * edit another
			 */
				boolean dateMatch = false;
				for (Dates obj : dateList)
				{
					// snapshot date is already read in, just need data
					if (obj.getCalendarDate().equals(toAdd.getCalendarDate()))
					{
						obj.add(toAdd); // add snapshot to allCourse in Dates class
						dateMatch = true;
					}
				} // end For

				// date does not exist in datesList, add to list.
				if (!dateMatch)
				{
					tempDate = new Dates(); // allocate new dates object
					tempDate.add(toAdd);		  // add snapshot to dates object
					dateList.addLast(tempDate); // add dates object to datesList as a node
				}

		} //end Else


	}
	/**
     * calcTimeElapsed
     * Calculate the semester length in days between
     * the start date to the end date.
     *  It iterates through the entire collection
     *  and updates the NormalizedDate all the objects.
     *   If the object time flame false between the
     *   semester start date and end date, the NormalizedDate
     *   is updated based on the days elapsed /semester Length.
     *   If the object does not fall between the semester
     *   start date and end date, the NormalizedDate is
     *   updated to 0.0
     */

	public void calcTimeElapsed()
	{
		// Format of the date for LocalDate objects
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// start and end dates converted to local date objects
		LocalDate actuStart = LocalDate.parse(startDate, format);
		LocalDate actuEnd = LocalDate.parse(endDate, format);

		// calculate the semester length in days
		double semLen = ChronoUnit.DAYS.between(actuStart, actuEnd);

		for (Dates obj : dateList)
		{
			// store current date from this node in a string
			String tempCurrent = obj.getCalendarDate();

			// convert to LocalDate obj
			LocalDate current = LocalDate.parse(tempCurrent, format);

			// get the difference between the current date and the end date
			double daysElapsed = ChronoUnit.DAYS.between(actuStart, current);
			// set normalized date

			// do not set negative dates
			if (daysElapsed > -0.0)
			{
				// calculate percentage of time elapsed
				double timeElapsed = (daysElapsed / semLen);
				// truncate timeElapsed
				timeElapsed = Math.floor(timeElapsed * 100) / 100;
				obj.setNormalizedDate(timeElapsed);
			}
			else
				obj.setNormalizedDate(0.0);
		}
	}

	/**
	 * getLastDate
	 *
	 * This function returns the last Dates for
	 * the given semester.  Required for summary report
	 * @return Dates object
	 */
	public Dates getLastDate()
	{
		int lastDateIndex = dateList.size() - 1;
		return dateList.get(lastDateIndex);
	}
	/**
	 * Updates the SemesterId
	 * @param semesterId desired
	 */
	public void setSemesterId(String semesterId)
	{
		this.semesterId = semesterId;
	}
	/**
	 * Returns the SemesterId of the Semester Object
	 * @return semesterId
	 */
	public String getSemesterId()
	{
		return semesterId;
	}
	/**
	 * Returns the startDate of the Semester Object
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * Returns the endDate of the Semester Object
	 * @return endDate
	 */
	public String getEndDate() {
		return endDate;
	}
//	  /**
//     * Retrieve the dateList list.
//     *
//     * @return dateList.
//     */
// public List<Dates> getalldateList() {
//		return dateList;
//	}
	/**
     * Retrive the number of dateList.
     *
     * @return the number of dateList that comprise this Semester object.
     */
	 public int size()
	 {
		 return dateList.size();
	    }
	/**
    * "Print" all dateList.
    *
    * @return String containing all component Semester objects
    */
	 @Override
	 public String toString()
	  {
	      StringBuilder bld = new StringBuilder();
	      /*
	   	  * Quoted code Begain
	   	  * Kennedy, Thomas J. 2.2 The C++ Composite::Display and
	   	  * Java Composite.toString Methods.
	   	  * Creating Polyhedra (Java) Hints, Old Dominion Univ.,
	   	  * 2020,
	   	  * www.cs.odu.edu/~tkennedy/cs330/f20/Assts/polyhedra_java_2/hints.mmd.html.
	   	  */
	        bld.append("Semester level\n" + semesterId + "\n");
	     	for (Dates s : this.dateList) {
	            bld.append("  " + s);
	        }
	     	///Quoted code End

	        return bld.toString();
	    }
}