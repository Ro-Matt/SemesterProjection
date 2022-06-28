package edu.odu.cs.cs350.EnrollmentProjection;
import java.util.List;
import java.io.IOException;
//import java.util.Vector;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;


/**
 * This is the Dates class.  The dates class
 * holds a list of courses
 * and their snapshots, for a given date.
 * The calendarDate is determined by
 * the Snapshot file name yyyy-mm-dd.csv.
 * Date also holds the normalized date,
 * where calendarDate is normalized between 0 and 1,
 * which is defined by the startDate and endDate
 * variables of the Semester object above it.
 * 
 */

public class Dates  {
	
	/**
	 * The date of the snapshot from which this was pulled.
	 * Formatted yyyy-mm-dd.
	 */
	private String calendarDate;
	/**
	 * The calendarDate normalized between 0 and 1, as defined by the 
	 * startDate and endDate members of the Semester object above it.
	 */
	private double normalizedDate; // a double between 0 and 1, maybe even greater than 1
    /**
     * A linked list of courses that fall under this snapshot date.
     */
	private List<Course> allCourse;
	/**
     * Create a class Dates. 
     * 
     *  set the calendarDate to "0000-00-00".
     *  set the normalizedDate to -1.
     *  create empty ArrayList of Course.
     */	
	 public Dates()
	 {
		calendarDate = "0000-00-00";
		allCourse = new ArrayList<Course>();
		normalizedDate = -1.0;
	    }	
	 /**
	     * Add a Snapshot to the 'Dates' collection.
	     * Rule for Adding a Snapshot:
	     *  1: the first Snapshot that gets added to the collection update the desired calendarDate of this collection.
	     *  2: once the calendarDate has been update by the first Snapshot all future must have the same calendarDate as the collection calendarDate.
	     *   
	     * @param toAdd this is the snapshot that will be added to the collection once this has been add to the new Course object.
	     */
//	     *if the list size is zero
//	     **the calendarDate is set to the snapshot calendarDate
//	     **create a new Course object name tempCourse 
//	     **add the toAdd to tempCourse
//	     **add the tempCourse to allCourse
//	     **
//	     *
//	     *if the list is not zero it will check if the list if the toAdd calendarDate match `Dates` calendarDate
//	     **create a local boolean variable name sameCourse and it is set the the default value of false
//	     **it iterate through the entire collection and search if the if the toAdd courseName match any of the Course courseName inside collection
//	     ***if the name matches the toAdd  is added to the course the has the same name
//	     ***update sameCourse to true
//	     *
//	     **if the sameCourse is still false after it has check the entire collection
//	     ***create a new Course object name tempCourse 
//	     ***add the toAdd to tempCourse
//	     ***add the tempCourse to allCourse
	     

	 public void add(Snapshot toAdd)
	 {
	    	/*Quoted code Begain
	    	 * 
	    	 * add built in function for the arraylist
	    	 * Class ArrayList. ArrayList (Java Platform SE 8 ), Oracle and/or Its Affiliates,
	    	 *  9 July 2020, docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html. 
	    	 
	    	 *
	    	 *Assingment6 Composite.java fucntion add( Polyhedron toAdd)
	    	 */
		  if (this.size() == 0)
		  {
			  this.calendarDate = toAdd.getCalendarDate();
			  Course tempCourse = new Course();
			  tempCourse.add(toAdd);
			  this.getAllCourse().add(tempCourse);
		  }
		 else if(this.getCalendarDate().equals(toAdd.getCalendarDate()))///add only snapshot of the same date
		 {
			 boolean sameCourse = false;
		 
			 for (Course s : this.getAllCourse()) {
				 if (s.getCourseName().equals(toAdd.getCourseName()))
				 {
					 s.add(toAdd);
					 sameCourse = true;
				 }
			 }
		 if (sameCourse == false)
		 {
	    	Course tempCourse = new Course();
	    	tempCourse.add(toAdd);
		    this.getAllCourse().add(tempCourse);
		 }
		  }
	    		
	    	///Quoted code End
	    }
  /**
     * Retrieve the iterator for allCourse.
     *
     * @return allCourse.iterator().
     */	
	 public Iterator<Course> iterator()
	 {
		 return getAllCourse().iterator();
	    }
  /**
     * Retrieve the calendarDate.
     *
     * @return current calendarDate.
     */
	 public String getCalendarDate()
	 {
		 return calendarDate;//month and day
	    }
	  /**
	     * Updates the calendarDate of this object.
	     *
	     * @param calendarDate desired calendarDate.
	     */ 
	 public void setCalendarDate(String calendarDate)
	 {
		 this.calendarDate = calendarDate;
	    }
  /**
	 * Retrive the number of allCourse.
	 *
	 * @return the number of allCourse that comprise this Dates object.
	 */	
	 public int size()
	 {
		 return getAllCourse().size();
	    }
  /**
     * Compare 2 `Dates`s based on collection and calendarDate.
     *
     * @param rhs the other (right-hand-side) Dates object.
     */ 

	 @Override
	 public boolean equals(Object rhs)
	 {
		 if (!(rhs instanceof Dates)) {
	            return false;
	        }
		 Dates rhsComp = (Dates) rhs;
		 return (this.calendarDate.equals(rhsComp.calendarDate))&&(this.getAllCourse().equals(rhsComp.getAllCourse()));
	    }
	    /**
	     * Update the normalizedDate.
	     *
	     * @param normalizedDate desired normalizedDate.
	     */
	 public void setNormalizedDate(double normalizedDate)
	 {
		 this.normalizedDate = normalizedDate;
	    }
	    /**
	     * Retrieve the normalizedDate.
	     *
	     * @return current normalizedDate
	     */
	 public double getNormalizedDate()
	 {
		 return normalizedDate;
	    }
	    /**
	     * Retrieve the allCourse list.
	     *
	     * @return allCourse.
	     */
	 public List<Course> getAllCourse() {
			return allCourse;
		}
		/**
	    * "Print" all allCourse.
	    *
	    * @return String containing all component Dates objects
	    */
	 @Override
	 public String toString()
	  {
	     StringBuilder bld = new StringBuilder();
	      /*
	   	  * Quoted code Begain
	   	  * Kennedy, Thomas J. 2.2 The C++ Composite::Display and Java Composite.toString Methods. 
	   	  * Creating Polyhedra (Java) Hints, Old Dominion Univ., 2020, www.cs.odu.edu/~tkennedy/cs330/f20/Assts/polyhedra_java_2/hints.mmd.html. 
	   	  */
	        bld.append("Date Level:" + calendarDate + "\n");
	     for (Course s : this.allCourse) {
	            
	            bld.append( "  " + s);	
	        }
	     	///Quoted code End
	        
	        return bld.toString();
	    }
}
