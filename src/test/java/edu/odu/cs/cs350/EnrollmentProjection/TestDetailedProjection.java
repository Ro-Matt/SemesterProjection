package edu.odu.cs.cs350.EnrollmentProjection;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import java.io.FileOutputStream;
import java.util.*; 

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestDetailedProjection {
	
	DetailedProjectionReport testDetailPar;
	DetailedProjectionReport testDetailDef;
	
	@Before
	public void BeforeEach()
	{
		testDetailDef = new DetailedProjectionReport();
		testDetailPar = new DetailedProjectionReport("CS361", 'H', 1,1,"202010", .5, 20);
		
	}
	@Test
	public void testDetailedProjectionReportdef() {
		

	}

	 @Test
	 public void testDetailedProjectionReportpar() {

	 	List<DetailedProjectionReport> list=new ArrayList<DetailedProjectionReport>(); 
		
	 	DetailedProjectionReport row1_1=new DetailedProjectionReport("CS361", 'J',1, 0,"201910", .1, 30); 
	     //assertThat(row1_1.getnormalizedDate(), is  (.1));
	 	DetailedProjectionReport row1_2=new DetailedProjectionReport("CS361", 'J',2, 0,"201910", .2, 35); 
	 	//assertThat(row1_2.getnormalizedDate(), is  (.2));
	 	DetailedProjectionReport row1_3=new DetailedProjectionReport("CS361", 'J',3, 0,"201910", .5, 50);
	 	//assertThat(row1_3.getnormalizedDate(), is  (.5));
	 	DetailedProjectionReport row1_4=new DetailedProjectionReport("CS361", 'H',4, 0,"201910", .75, 52);
	 	//assertThat(row1_4.getnormalizedDate(), is  (.75));
	 	DetailedProjectionReport row2_1=new DetailedProjectionReport("CS361", 'H',1, 2,"202110", .25, 25);  
	 	//assertThat(row2_1.getnormalizedDate(), is  (.25));
	 	DetailedProjectionReport row2_2=new DetailedProjectionReport("CS361", 'H',2, 2,"201110", .2, 35); 
	 	//assertThat(row2_2.getnormalizedDate(), is  (.2));
	 	DetailedProjectionReport row2_3=new DetailedProjectionReport("CS361", 'H',3, 2,"201110", .4, 50);
	 	//assertThat(row2_3.getnormalizedDate(), is  (.4));
	 	DetailedProjectionReport row2_4=new DetailedProjectionReport("CS361", 'H',4, 2,"201110", .6, 52);
	 	//assertThat(row2_4.getnormalizedDate(), is  (.6));
	 	DetailedProjectionReport row1_5=new DetailedProjectionReport("CS361", 'H',1, 4,"202130", 0, 0);
	 	//assertThat(row1_5.getnormalizedDate(), is  (0));
	 	DetailedProjectionReport row1_6=new DetailedProjectionReport("CS361", 'H',2, 4,"202130", .25, 25);
	 	assertThat(row1_6.getnormalizedDate(), is  (.25));
	 	DetailedProjectionReport row1_7=new DetailedProjectionReport("CS361", 'H',3, 4,"202130", .5, 60);
	 	assertThat(row1_7.getnormalizedDate(), is  (.5));
	 	DetailedProjectionReport row1_8=new DetailedProjectionReport("CS361", 'P',1, 6," ", .5, 60);
	 	//assertThat(row1_7.getnormalizedDate(), is  (.5));
	 	DetailedProjectionReport row3_8=new DetailedProjectionReport("CS361", 'P',2, 6," ", 1.0, 80);

	 	DetailedProjectionReport row1_9=new DetailedProjectionReport("CS362", 'H',3, 0,"202010", .5, 20);  
	 	DetailedProjectionReport row1_10=new DetailedProjectionReport("CS362", 'H',4, 0,"202010", .6, 40); 
	 	DetailedProjectionReport row1_11=new DetailedProjectionReport("CS362", 'H',1, 0,"202010", .75, 60);
	 	DetailedProjectionReport row1_12=new DetailedProjectionReport("CS362", 'H',2, 0,"202010", 1.05, 80);
	 	DetailedProjectionReport row1_13=new DetailedProjectionReport("CS362", 'J',2, 2,"202130", .25, 30);
	 	DetailedProjectionReport row1_14=new DetailedProjectionReport("CS362", 'J',3, 2,"202130", .5, 40);
	 	DetailedProjectionReport row1_15=new DetailedProjectionReport("CS362", 'J',1, 2,"202130", .5, 60);
	 	DetailedProjectionReport row1_16=new DetailedProjectionReport("CS362", 'P',2, 4," ", .5, 60);
	 	DetailedProjectionReport row1_17=new DetailedProjectionReport("CS362", 'P',1, 4," ", .7, 90);
	 	DetailedProjectionReport row1_18=new DetailedProjectionReport("CS411", 'H',1, 0,"202010", .5, 10);  
	 	DetailedProjectionReport row1_19=new DetailedProjectionReport("CS411", 'H',2, 0,"202010", .7, 35); 
	 	DetailedProjectionReport row1_20=new DetailedProjectionReport("CS411", 'H',3, 0,"202010", .75, 65);
	 	DetailedProjectionReport row1_21=new DetailedProjectionReport("CS411", 'H',4, 0,"202010", 1.0, 95);
	 	DetailedProjectionReport row1_22=new DetailedProjectionReport("CS411", 'J',1, 2,"202130", .25, 20);
	 	DetailedProjectionReport row1_23=new DetailedProjectionReport("CS411", 'J',2, 2,"202130", .3, 30);
	 	DetailedProjectionReport row1_24=new DetailedProjectionReport("CS411", 'J',3, 2,"202130", .4, 50);
	 	DetailedProjectionReport row1_25=new DetailedProjectionReport("CS411", 'P',1, 4," ", .4, 50);
	 	DetailedProjectionReport row1_26=new DetailedProjectionReport("CS411", 'P',2, 4," ", .75, 80);

		
	 	//Adding Books to list  
	     list.add(row1_1);   
	     list.add(row1_2);  
	     list.add(row1_3); 
	     list.add(row1_4); 
	     list.add(row2_1); 
	     list.add(row2_2);
	     list.add(row2_3);
	     list.add(row2_4);
	 	list.add(row1_5); 
	     list.add(row1_6); 
	     list.add(row1_7); 
	     list.add(row1_8); 
	     list.add(row3_8); 
	     list.add(row1_9); 
	     list.add(row1_10); 
	     list.add(row1_11); 
	     list.add(row1_12); 
	     list.add(row1_13); 
	     list.add(row1_14); 
	     list.add(row1_15); 
	     list.add(row1_16); 
	     list.add(row1_17); 
	     list.add(row1_18); 
	     list.add(row1_19); 
	     list.add(row1_20); 
	     list.add(row1_21);
	     list.add(row1_22);
	     list.add(row1_23);
	     list.add(row1_24);
	     list.add(row1_25);
	     list.add(row1_26);
		
	    
	    
	 	testDetailPar.printDetailReport(list,"projection report.xlsx");


	 }
	@Test
	public void testSemesterID() {
		
		String semester="Fall 2020";
		//testDetailPar.getSemesterId("202010");
		assertThat(testDetailPar.getSemesterId("202010"), containsString (semester));
		String semester2="Spring 2020";
		assertThat(testDetailPar.getSemesterId("202020"), containsString (semester2));
		String semester3="Summer 2020";
		assertThat(testDetailPar.getSemesterId("202030"), containsString (semester3));
	}

}
