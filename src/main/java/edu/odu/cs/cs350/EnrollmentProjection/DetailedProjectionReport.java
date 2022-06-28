package edu.odu.cs.cs350.EnrollmentProjection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;


/**
 * Detailed Projection Report
 * outputs the projected data from projection
 * into an excel workbook.
 * Each sheet will be given to each individual
 * course while also displaying the different
 * data per historical semester at a given date
 * 
 */
public class DetailedProjectionReport { 
	
	/**
	 * 	Stores the course name
	 */
	private String courseName;
	/**
	 * Saves the semester type
	 */
	private char type;
	/**
	 *  Saves the row for every object
	 */
	private int row;
	/**
	 * Saves the column for every object
	 */
	private int col;
	/**
	 *  Saves the semester ID
	 */
	private String semesterID;
	/**
	 * Saves the normalized date
	 */
	private double normalizedDate;
	/**
	 * 	SAves the enrollment
	 */
	private int enrollment;
	
	/**
	 * Default Constructor - initialize all data
	 * set courseName to an empty string
	 * set type to an empty character
	 * set row to 0
	 * set col to 0
	 * set semesterID to an empty string
	 * set normalizedDate to 0
	 * set normalizeDate to 0
	 * set enrollment to 0
	 */
	
    DetailedProjectionReport()
    {
    	this.setCourseName(" ");
        this.setType(' ');
        this.setRow(0);
        this.setCol(0);
        this.semesterID = " ";
        this.setNormalizedDate(0);
        this.enrollment = 0;
    }
    
    /**
     * 
     * Construct a DetailedProjectionRreport object from
     * given parameters
     * 
     * @param courseName desired courseName
     * @param type desired type
     * @param row desired row
     * @param col desired col
     * @param semesterID desired semesterID
     * @param normalizedDate desired normalizedDate
     * @param enrollment desired enrollment
     */
    
    DetailedProjectionReport(String courseName, char type, int row, int col,String semesterID, double normalizedDate, int enrollment)
    {
        this.setCourseName(courseName);
        this.setType(type);
        this.setRow(row);
        this.setCol(col);
        this.semesterID = semesterID;
        this.setNormalizedDate(normalizedDate);
        this.enrollment = enrollment;
        
    }
    
 	
    /**
     * 
     * This method takes a list of DetailedProjectionReport objects, creates an Excel Workbook 
     * and inserts them into the excel file EnrollmentProjections.xlsx.  The Excel Workbook
     * is written to the path defined by outputDir.
     * 
     * @param reportList a list of objects formatted for printing to the excel file
     * @param outputDir the location of the excel file upon closing
     */
    
    @SuppressWarnings("deprecation")
	public void printDetailReport(List<DetailedProjectionReport> reportList, String outputDir) 
    {
    	// workbook obj
    	XSSFWorkbook detailedReport = new XSSFWorkbook();
    	
    	String firstCourse = reportList.get(0).getCourseName();
    	// spreadsheet created with first element in list
    	XSSFSheet course = detailedReport.createSheet(firstCourse);
    	//row, set to header
    	XSSFRow row = course.createRow(0);
    	//col
    	XSSFCell cell;

    	int cellId = 0; // row[col]
    	for(DetailedProjectionReport obj : reportList)
    	{
    		// Store the current course name
    		String tempCourseName = obj.courseName;
    		
    		/*
    		 * for every obj in report list, split into three cases
    		 * 
    		 * 1. if the sheet is not created, create it and add the header
    		 * 	a. add all pertinent date for that particular object
    		 * 
    		 * 2. if the sheet is created, and the semesterID is not added, add the header data
    		 *  a.  add the pertinent data
    		 *  
    		 * 	2.1 if the sheet is created and the header data is already in
    		 * 		a. add the data
    		 * 2.1 is a sub case of 2
    		 */
    		
    		// if the course name is not in the workbook, then create the sheet
    		// and create the headers
    		if(detailedReport.getSheet(tempCourseName) == null)
    		{
    			// create the sheet
    			course = detailedReport.createSheet(obj.courseName);
    			// create row for headers
    			row = course.createRow(0);
    			    			
        		if(obj.getType() == 'H')
        		{
        			// cellId is the column
        			cellId = obj.getCol();
        			// create column
        			cell = row.createCell(cellId);
        			// set the header for normalized dates
        			cell.setCellValue("d historical");
        			// increment column
        			cellId++;
        			cell = row.createCell(cellId);
        			cell.setCellValue(obj.getSemesterId(obj.semesterID));
        		}
        		// same method for current
        		else if (obj.getType() == 'C')
        		{
        			// cellId is the column
        			cellId = obj.getCol();
        			// create column
        			cell = row.createCell(cellId);
        			// set the header for normalized dates
        			cell.setCellValue("d current");
        			// increment column
        			cellId++;
        			cell = row.createCell(cellId);
        			cell.setCellValue(obj.getSemesterId(obj.semesterID));
        		}
        		else
        		{
        			// cellId is the column
        			cellId = obj.getCol();
        			// create column
        			cell = row.createCell(cellId);
        			// set the header for normalized dates
        			cell.setCellValue("d projected");
        			// increment column
        			cellId++;
        			cell = row.createCell(cellId);
        			cell.setCellValue("Projected");
        		} 
        		
        		// if the row does not exist, create it and fill cell
				if(course.getRow(obj.getRow()) == null)
				{
					row = course.createRow(obj.getRow());
					cellId = obj.getCol();
					cell = row.createCell(cellId);
					cell.setCellValueImpl(obj.getnormalizedDate());
					cellId++;
					cell = row.createCell(cellId);
					cell.setCellValueImpl(obj.getEnrollment());
				}
					
				// otherwise, set row to obj.row and create / fill cell
				else
				{
					row = course.getRow(obj.getRow());
					cellId = obj.getCol();
					cell = row.createCell(cellId);
					cell.setCellValueImpl(obj.getnormalizedDate());
					cellId++;
					cell = row.createCell(cellId);
					cell.setCellValueImpl(obj.getEnrollment());
					
				}
				
				// reset row 
        		row = course.getRow(0);
        		
    		}// end parent if
    		
    		// if the sheet exists.... 
    		// this is 2 and 2.1
    		else
    		{
    			
    			// set the sheet equal to the current course name
    			course = detailedReport.getSheet(tempCourseName);
    			row = course.getRow(0);
    			
    			// check if the header has already been written
    			boolean headerCreated = false;
    			for(Cell cellObj : row)
    			{
    				cellObj.setCellType(CellType.STRING);
    				if(cellObj.getStringCellValue().equals(obj.getSemesterId(obj.getSemesterId(firstCourse))));
    					headerCreated = true;
    			}
    			// case 2 - sheet is created but not the header for the semester
    			if(!headerCreated)
    			{
    				if(obj.getType() == 'H')
            		{
            			cellId = obj.getCol();
            			cell = row.createCell(cellId);
            			// set the header for normalized dates
            			cell.setCellValue("d historical");
            			// increment column
            			cellId++;
            			// create cell at column
            			cell = row.createCell(cellId);
            			// set data at cell
            			cell.setCellValue(obj.getSemesterId(obj.semesterID));
            		}
            		// same method for current
            		else if (obj.getType() == 'C')
            		{
            			row = course.getRow(obj.getRow());
            			// cellId is the column
            			cellId = obj.getCol();
            			// create column
            			cell = row.createCell(cellId);
            			// set the header for normalized dates
            			cell.setCellValue("d current");
            			// increment column
            			cellId++;
            			cell = row.createCell(cellId);
            			cell.setCellValue(obj.getSemesterId(obj.semesterID));
            		}
            		else
            		{
            			// cellId is the column
            			cellId = obj.getCol();
            			// create column
            			cell = row.createCell(cellId);
            			// set the header for normalized dates
            			cell.setCellValue("d projected");
            			// increment column
            			cellId++;
            			cell = row.createCell(cellId);
            			cell.setCellValue("Projected");
            			
            			// now add the data
            			// if the row does not exist, create it and fill cell
        				if(course.getRow(obj.getRow()) == null)
        				{
        					row = course.createRow(obj.getRow());
        					cellId = obj.getCol();
        					cell = row.createCell(cellId);
        					cell.setCellValueImpl(obj.getnormalizedDate());
        					cellId++;
        					cell = row.createCell(cellId);
        					cell.setCellValueImpl(obj.getEnrollment());
        				}
        					
        				// otherwise, set row to obj.row and create / fill cell
        				else
        				{
        					row = course.getRow(obj.getRow());
        					cellId = obj.getCol();
        					cell = row.createCell(cellId);
        					cell.setCellValueImpl(obj.getnormalizedDate());
        					cellId++;
        					cell = row.createCell(cellId);
        					cell.setCellValueImpl(obj.getEnrollment());
        				}
        				
        				// reset row 
                		row = course.getRow(0);
            		} 
    			}
    			// case 2.1 - sheet and header exist, just add data to cellId and cellId+1
    			else
    			{
    				// if the row does not exist, create it and fill cell
    				if(course.getRow(obj.getRow()) == null)
    				{
    					row = course.createRow(obj.getRow() - 1);
    					cellId = obj.getCol();
    					cell = row.createCell(cellId);
//    					String newNormalDate = String.format("%.2f",obj.getnormalizedDate());
//    					cell.setCellValue(newNormalDate);
    					cell.setCellValueImpl(obj.getnormalizedDate());
    					cellId++;
    					cell = row.createCell(cellId);
    					cell.setCellValueImpl(obj.getEnrollment());
    				}
    					
    				// otherwise, set row to obj.row and create / fill cell
    				else
    				{
    	        		if(obj.getType() == 'H')
    	        		{
    	        			// cellId is the column
    	        			cellId = obj.getCol();
    	        			// create column
    	        			cell = row.createCell(cellId);
    	        			// set the header for normalized dates
    	        			cell.setCellValue("d historical");
    	        			// increment column
    	        			cellId++;
    	        			cell = row.createCell(cellId);
    	        			cell.setCellValue(obj.getSemesterId(obj.semesterID));
    	        		}
    	        		// same method for current
    	        		else if (obj.getType() == 'C')
    	        		{
    	        			// cellId is the column
    	        			cellId = obj.getCol();
    	        			// create column
    	        			cell = row.createCell(cellId);
    	        			// set the header for normalized dates
    	        			cell.setCellValue("d current");
    	        			// increment column
    	        			cellId++;
    	        			cell = row.createCell(cellId);
    	        			cell.setCellValue(obj.getSemesterId(obj.semesterID));
    	        		}
    	        		else
    	        		{
    	        			// cellId is the column
    	        			cellId = obj.getCol();
    	        			// create column
    	        			cell = row.createCell(cellId);
    	        			// set the header for normalized dates
    	        			cell.setCellValue("d projected");
    	        			// increment column
    	        			cellId++;
    	        			cell = row.createCell(cellId);
    	        			cell.setCellValue("Projected");
    	        		} 
    	        		
    					row = course.getRow(obj.getRow());
    					if(obj.getType() == 'P') {
    						row = course.getRow(obj.getRow() + 1);
    	    				if(course.getRow(obj.getRow()) == null)
    	    				{
    	    					row = course.createRow(obj.getRow() - 1);
    	    					cellId = obj.getCol();
    	    					cell = row.createCell(cellId);
    	    					cell.setCellValueImpl(obj.getnormalizedDate());
    	    					cellId++;
    	    					cell = row.createCell(cellId);
    	    					cell.setCellValueImpl(obj.getEnrollment());
    	    				}
    					}
    					cellId = obj.getCol();
    					cell = row.createCell(cellId);
//    					String newNormalDate = String.format("%.2f",obj.getnormalizedDate());
//    					cell.setCellValue(newNormalDate);
    					cell.setCellValueImpl(obj.getnormalizedDate());
    					cellId++;
    					cell = row.createCell(cellId);
    					cell.setCellValueImpl(obj.getEnrollment());
    					
    				}
    				// reset row 
            		row = course.getRow(0);
    			}
    			
    		}	
    	} // end write loop
    
    	/*
    	 * save data and coordinates for graph
    	 * 
    	 * 	could not implement.
    	 * 
    	 
    	ArrayList<Integer> rowNumber = new ArrayList<Integer>();
		
		// the following arrays save the coordinates for data ranges on a sheet
		// by sheet basis.  The data is saved in the arrrays, but the column number of the
		// data is saved in the anchors
		int xAnchor;
		int yAnchor;
		
		ArrayList<Double> histXcoord = new ArrayList<Double>();
		ArrayList<Double> histYcoord = new ArrayList<Double>();
		ArrayList<Double> currXcoord = new ArrayList<Double>();
		ArrayList<Double> currYcoord = new ArrayList<Double>();
		ArrayList<Double> projXcoord = new ArrayList<Double>();
		ArrayList<Double> projYcoord = new ArrayList<Double>();
		
		// workbook.getNumberOfSheets();
		// workbook.getSheetAt(i);
		
		
		
	
		int sheetNum = 1;
		for (Sheet sheet: detailedReport)	
		{ 
			// save the header
			Row headerRow = sheet.getRow(0);
			Cell cellOfRow;
			
			// Start at the end of the row
			for (int i = headerRow.getLastCellNum() - 1; i >= 0; i--)
			{
				// if the cell exists, convert to string for comparison
				if (headerRow.getCell(i) != null)	
				{
					headerRow.getCell(i).setCellType(CellType.STRING);
					// for a matching header, store the data in both columns
					if(headerRow.getCell(i).getStringCellValue().equals("d historical"))
	                {
	    				/// this  my x - all doubles
	    				xAnchor = i;
	    				// this is y - all doubles
	    				yAnchor = i+1;
	    				// store the data in each column
	    				for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++)
	    				{
	    					System.out.println("Sheet " + sheetNum);
	    					System.out.print("(X, Y) = ");
	    					// if the cell has been created
	    					if(sheet.getRow(j).getCell(xAnchor) != null)
	    					{
	    						sheet.getRow(j).getCell(xAnchor).setCellType(CellType.NUMERIC);
	    						histXcoord.add(sheet.getRow(j).getCell(xAnchor).getNumericCellValue());
		    					System.out.print(histXcoord.get(j-1) + ", ");
	    					}
	    					if(sheet.getRow(j).getCell(yAnchor) != null)
	    					{
	    						sheet.getRow(j).getCell(yAnchor).setCellType(CellType.NUMERIC);
	    						histYcoord.add(sheet.getRow(j).getCell(yAnchor).getNumericCellValue());
		    					System.out.print(histYcoord.get(j-1));
	    					}
	    					System.out.print('\n');
	    				}
	    				
	    				
	    			}
	                if(headerRow.getCell(i).getStringCellValue().equals("d current"))
	                {
	                	/// this  my x - all doubles
	    				xAnchor = i;
	    				// this is y - all doubles
	    				yAnchor = i+1;
	    				// store the data in each column
	    				for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++)
	    				{
	    					// if the cell has been created
	    					if(sheet.getRow(j).getCell(xAnchor)!= null)
	    					{
	    						sheet.getRow(j).getCell(xAnchor).setCellType(CellType.NUMERIC);
								currXcoord.add(sheet.getRow(j).getCell(xAnchor).getNumericCellValue());
	    					}
	    						
	    					if(sheet.getRow(j).getCell(yAnchor) != null)
	    					{
	    						sheet.getRow(j).getCell(yAnchor).setCellType(CellType.NUMERIC);
	    						currYcoord.add(sheet.getRow(j).getCell(yAnchor).getNumericCellValue());
	    					}
	    				}
	             			    		             				 
	                }
	                if(headerRow.getCell(i).getStringCellValue().equals("d projected"))
	                {
	                	/// this  my x - all doubles
	    				xAnchor = i;
	    				// this is y - all doubles
	    				yAnchor = i+1;
	    				// store the data in each column
	    				for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++)
	    				{
	    					// if the cell has been created
	    					if(sheet.getRow(j).getCell(xAnchor)!= null)
	    					{
	    						sheet.getRow(j).getCell(xAnchor).setCellType(CellType.NUMERIC);
	    						projXcoord.add(sheet.getRow(j).getCell(xAnchor).getNumericCellValue());
	    					}
	    						
	    					if(sheet.getRow(j).getCell(yAnchor) != null)
	    					{
	    						sheet.getRow(j).getCell(yAnchor).setCellType(CellType.NUMERIC);
	    						projYcoord.add(sheet.getRow(j).getCell(yAnchor).getNumericCellValue());
	    					}
	    				}
	                }
				} // end if row null
			} // end row for
			sheetNum++;
		} // end sheet for
		
		*/
		
    	try 
    	{
    		//FileOutputStream outputStream = new FileOutputStream (new File("Detailed Projection Report.xlsx"));
    		FileOutputStream outputStream = new FileOutputStream (new File(outputDir + "/EnrollmentProjection.xlsx"));
    		detailedReport.write(outputStream);
			outputStream.close();
			detailedReport.close();
		} 
    	catch (IOException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * return the enrollment for a given object
     * @return enrollment
     */
    
    public int getEnrollment() {
		return enrollment;
	}
    
    /**
     * Sets the enrollment for a DetailedReportObject
     * 
     * @param enrollment
     */
	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	/**
	 * return the normalized date for an object
	 * @return normalizedDate
	 */
	
	public double getnormalizedDate()
	{
		return getNormalizedDate();
	}
	
	/**
	 * getSemesterId takes a string YYYYSS where 
	 * SS is a semester identifier, and converts it into 
	 * a string with the semester name and the year
	 *  i.e. 202010 becomes Fall 2020
	 * @param SemesterId string to convert
	 * @return converted string
	 */
	
    public String getSemesterId(String SemesterId)
    {
	  
	  String year=SemesterId.substring(0, 4);
	  String Semester=" ";
	  
       if (SemesterId.charAt(4)=='1')
       {
    	   Semester ="Fall "+year; 
       }
       if (SemesterId.charAt(4)=='2')
       {
    	   Semester= "Spring "+year; 
       }    
       
       if (SemesterId.charAt(4)=='3')
       {
    	   Semester= "Summer "+year; 
       }
       return Semester; 
    }
    
    
    /**
     * getSemesterId returns the semester ID of the object
     * @return semesterID
     */
    public String getSemesterId()
    {
    	return semesterID;
    }
    /**
     * returns the course name of an object
     * @return courseName
     */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * sets the course name of the object
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * returns the column number of the object
	 * @return col
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * sets the column of the object
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * returns the row of the object
	 * @return orw
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * sets the row of the object
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * returns the normalized date of the object
	 * @return
	 */
	public double getNormalizedDate() {
		return normalizedDate;
	}

	/**
	 * sets the normalize date of the object
	 * @param normalizedDate
	 */
	public void setNormalizedDate(double normalizedDate) {
		this.normalizedDate = normalizedDate;
	}

	/**
	 * sets the semester type of the object
	 * @return type
	 */
	public char getType() {
		return type;
	}

	/**
	 * sets the type of the object
	 * @param type
	 */
	public void setType(char type) {
		this.type = type;
	}

}