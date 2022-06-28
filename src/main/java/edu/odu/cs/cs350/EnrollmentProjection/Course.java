package edu.odu.cs.cs350.EnrollmentProjection;

import java.util.Iterator;
import java.util.List;
//import java.util.Vector;
import java.util.ArrayList;
//import java.util.Formatter;

/**
 * This is the Course class.  This class stores the data that is CRN
 * dependent for a given class.  For example, a Course object with
 * the courseName CS350, will store an ArrayList of Snapshots that
 * pertain to CS350, across all CRNs for that class.
 */

public class Course {

    /**
     * A concatenation of the courseSUBJ and courseCRSE.
     * Used for data parsing and printing.
     */
	String courseName;
	/**
	 * The overall enrollment for a course across all CRNs.
	 */
	private int overallENR;
	/**
	 * The overall cap for a course across all CRNs.
	 */
	private int overallCap;
	/**
	 * A linked list of snapshots belonging to a course in a given snapshot.
	 */
	private List<Snapshot> allSection;
	/**
     * Create a class Course.
     *
     * set the courseName to "none".
     * set the OverallENR to 0.
     * set the OverallCAP to 0.
     * create empty ArrayList of Snapshot.
     */
	 public Course()
	 {
		courseName = "none";
		allSection = new ArrayList<Snapshot>();
		overallENR = 0;
		overallCap = 0;
	 }
	 /**
     * Add a Snapshot to the 'Course' collection.
     *
     * Rule for Adding a Snapshot:
     *  1: the first Snapshot that gets added to
     *  the collection update the desired courseName of this collection.
     *  2: once the name has been update by the first Snapshot
     *  all future must have the same courseName as the collection courseName.
     *  3: any Snapshot that get successfully added to the
     *  collection must Update the OverallENR and OverallCAP.
     *
     * @param toAdd this is the snapshot that will be added to the collection.
     **/
//     *if the list size is zero.
//     **the courseName is set to the snapshot courseName
//     **the OverallENR is update to the toAdd Retrieve value of ENR
//     **the OverallCAP is update to the toAdd Retrieve value of overallCap
//     **the toAdd is added
//     *
//     *if the list is not zero it will check if the list if the snapshot courseName match `Course` courseName
//     **the OverallENR base on the addition of OverallENR and the toAdd Retrieve value of ENR
//     **the OverallCAP base on the addition of OverallCAP and the toAdd Retrieve value of overallCap
//     **the toAdd is added
	 public void add(Snapshot toAdd)
	 {
	    	/*Quoted code Begain
	    	 *
	    	 * add built in function for the arraylist
	    	 * Class ArrayList. ArrayList (Java Platform SE 8 ),
	    	 * Oracle and/or Its Affiliates,
	    	 * 9 July 2020,
	    	 * docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html.
	    	 *
	    	 *Assingment6 Composite.java fucntion add( Polyhedron toAdd)
	    	 */

		  if (this.size() == 0)
		  {
			  //this.setCRSE(toAdd.getCRSE());
			  this.courseName = toAdd.getCourseName();
			  this.allSection.add(toAdd);
			  overallENR = toAdd.getOverallEnrollment();
			  overallCap = toAdd.getCap();
			  }
		  //only the section of the a course should be add
		  else if (this.getCourseName().equals(toAdd.getCourseName()))
		  {
			  overallENR += toAdd.getOverallEnrollment();
			  overallCap += toAdd.getCap();
			  this.allSection.add(toAdd);
			  }
	    	///Quoted code End
		  }
  /**
     * Retrieve the iterator for allSection.
     *
     * @return allSection.iterator().
     */
	 public Iterator<Snapshot> iterator()
	 {
		 return allSection.iterator();
	    }
  /**
     * Retrieve the courseName.
     *
     * @return current courseName.
     */
	 public String getCourseName()
	 {
		 return courseName;
		}
	 /**
     * Retrieve the OverallENR.
     *
     * @return current OverallENR.
     */
	 public int getOverallEnrollment()
	 {
		 return overallENR;
		}
	/**
     * Retrieve the OverallCAP.
     *
     * @return current OverallCAP.
     */
	 public int getOverallCap()
	 {
		 return overallCap;
		}
  /**
     * Retrive the number of allSection.
     *
     * @return the number of allSection that comprise this Course object.
     */
	 public int size()
	 {
		 return allSection.size();
	    }

  /**
     * Compare 2 `Course`s based on collection.
     *
     * @param rhs the other (right-hand-side) Course object.
     */
	 @Override
	 public boolean equals(Object rhs)
	 {
		 if (!(rhs instanceof Course)) {
	            return false;
	        }
		 Course rhsComp = (Course) rhs;
		 return (this.allSection.equals(rhsComp.allSection));
	    }
//	  /**
//	     * Retrieve the allSection list.
//	     *
//	     * @return allSection.
//	     */
//	 public List<Snapshot> getallSection() {
//			return allSection;
//		}
	 /**
	    * "Print" all allSection.
	    *
	    * @return String containing all component Course objects
	    */
	  @Override
	    public String toString()
	    {
	      StringBuilder bld = new StringBuilder();
	      /*
	   	  * Quoted code Begain
	   	  * Kennedy, Thomas J. 2.2 The C++ Composite::Display and Java Composite.toString Methods.
	   	  * Creating Polyhedra (Java) Hints, Old Dominion Univ., 2020,
	   	  * www.cs.odu.edu/~tkennedy/cs330/f20/Assts/polyhedra_java_2/hints.mmd.html.
	   	  */
	     bld.append("Course Level:" + courseName + "\n");
	     for (Snapshot s : this.allSection) {
	            bld.append("  " + s);
	            }
	     	///Quoted code End
	        return bld.toString();
	        }
}
