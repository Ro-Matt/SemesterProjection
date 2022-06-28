package edu.odu.cs.cs350.EnrollmentProjection;

/**
 * Snapshot will hold
 * the important parsed data from
 * semester parser getting it
 * ready for the Semester level.
 *
 */

public class Snapshot {

	/**
	 * CRN for a given section of a course.
	 */
	private int courseCRN;
	/*
	 * The subject of a course.
	 * i.e. MATH, STAT, CS... etc.
	 */
	private String courseSUBJ;
	/**
	 *  The course number or level of a subjects course.
	 *  i.e. CS 361, MATH 101, etc.
	 */
	private String courseCRSE;
	/**
	 * The concatenation of courseSUBJ and courseCRSE,
	 * used in report printing and processing
	 * in the data structure.
	 */
	private String courseName;
	/**
	 * The number of students currently enrolled in a CRN.
	 */
	private int courseENR;
	/**
	 * The total student cap for all CRNs in a given course.
	 */
	private int overallCap;
	/**
	 * The total overall students enrolled in a given course.
	 */
	private int overallENR;
	/**
	 * The date of the snapshot.
	 */
	private String calendarDate;
	/**
     * Default Constructor - initialize all data.
     * set the courseCRN to 0
     * set the courseSUBJ to null
     * set the courseCRSE to null
     * set the courseName to null
     * set the courseENR to 0
     * set the overallCap to 0
     * set the overallENR to 0
     * set the calendarDate to "0000-00-00"
     */
	Snapshot()
	{
		courseCRN = 0;
		courseSUBJ = null;
		courseCRSE = null;
		courseName= null;
		courseENR = 0;
		overallCap = 0;
		overallENR = 0;
		calendarDate = "0000-00-00";
	}
	 /**
     * Construct a Snapshot from a provided
     * courseCRN, courseSUBJ, courseCRSE,
     * courseENR, overallCap, overallENR,
     * calendarDate.
     *
     * set the courseName to courseSUBJ +" "+ CRSE
     *
     * @param courseCRN desired courseCRN
     * @param courseSUBJ desired courseSUBJ
     * @param courseCRSE desired courseCRSE
     * @param courseENR desired courseENR
     * @param overallCap desired overallCap
     * @param overallENR desired overallENR
     * @param calendarDate desired calendarDate
     */	 
	Snapshot(int courseCRN, String courseSUBJ, String courseCRSE,
            int courseENR, int overallCap, int overallENR,
            String calendarDate)
   {
       this.courseCRN = courseCRN;
       this.courseSUBJ = courseSUBJ;
       this.courseCRSE = courseCRSE;
       //courseName = courseSUBJ + " " + courseCRSE;
       setCourseName(courseSUBJ,courseCRSE);
       this.courseENR = courseENR;
       this.overallCap = overallCap;
       this.overallENR = overallENR;
       this.calendarDate = calendarDate;
   }
	/**
     * Update the courseCRN.
     *
     * @param courseCRN desired courseCRN
     */
	public void setCRN(int courseCRN)
	{
		this.courseCRN = courseCRN;
	}
	/**
     * Update the courseSUBJ.
     *
     * @param courseSUBJ desired courseSUBJ
     */
	public void setSUBJ(String courseSUBJ)
	{
		this.courseSUBJ = courseSUBJ;
	}
	/**
     * Update the courseCRSE.
     *
     * @param courseCRSE desired courseCRSE
     */
	public void setCRSE(String courseCRSE)
	{
		this.courseCRSE = courseCRSE;
	}
	/**
     * Update the courseName.
     *
     * @param courseSUBJ subject of the course
     * @param courseCRSE course number
     *
     * use the concatenation of   courseSUBJ +" "+
     * courseCRSE to update the desired  courseName
     */
	public void setCourseName(String courseSUBJ, String courseCRSE)
    {
        if (courseCRSE.contains("/"))
        {
            String replaceString=courseCRSE.replace('/','|');
            this.courseName = courseSUBJ + " " + replaceString;
        }
        else {
            this.courseName = courseSUBJ + " " + courseCRSE;
        }

    }
	/**
     * Update the courseENR.
     *
     * @param courseENR desired courseENR
     */
	public void setENR(int courseENR)
	{
		this.courseENR = courseENR;
	}
	 /**
     * Update the overallCap.
     *
     * @param overallCap desired overallCap
     */ 
	public void setCap(int overallCap)
	{
		this.overallCap = overallCap;
	}
	 /**
     * Update the overallENR.
     *
     * @param overallENR desired overallENR
     */
	public void setOverallEnrollment(int overallENR)
	{
		this.overallENR = overallENR;
	}
    /**
     * Update the calendarDate.
     *
     * @param calendarDate desired calendarDate
     */
	public void setcalendarDate(String calendarDate)
    {
	  this.calendarDate = calendarDate;
    }
	 /**
     * Retrieve the courseCRN.
     *
     * @return current courseCRN
     */
	public int getCRN()
	{
		return courseCRN;
	}
	 /**
     * Retrieve the courseSUBJ.
     *
     * @return current courseSUBJ
     */
	public String getSUBJ()
	{
		return courseSUBJ;
	}
	 /**
     * Retrieve the courseCRSE.
     *
     * @return current courseCRSE
     */
	public String getCRSE()
	{
		return courseCRSE;
	}
	 /**
     * Retrieve the courseName.
     *
     * @return current courseName
     */
	public String getCourseName()
	{
		return courseName;
	}
	 /**
     * Retrieve the courseENR.
     *
     * @return current courseENR
     */
	public int getENR()
	{
		return courseENR;
	}
	 /**
     * Retrieve the overallCap.
     *
     * @return current overallCap
     */
	public int getCap()
	{
		return overallCap;
	}
	 /**
     * Retrieve the overallENR.
     *
     * @return current overallENR
     */
	public int getOverallEnrollment()
	{
		return overallENR;
	}
	 /**
     * Retrieve the calendarDate.
     *
     * @return current calendarDate
     */
	public String getCalendarDate()
    {
        return calendarDate;
    }
	  /**
     * "Print" Snapshot.
     *
     * @return String containing \n	courseName
     * overallENR overallCap.
     */
	@Override
	public String toString(){
	      /*
	   	  * Quoted code Begain
	   	  * Kennedy, Thomas J. 2.2 The C++ Composite::Display
	   	  * and Java Composite.toString Methods.
	   	  * Creating Polyhedra (Java) Hints, Old Dominion Univ.,
	   	  * 2020,
	   	  * www.cs.odu.edu/~tkennedy/cs330/f20/Assts/polyhedra_java_2/hints.mmd.html. 
	   	  */
	        return ("\n\t" + courseName + "\t" + overallENR + "\t\t" + overallCap);
	    }


}