package com.framework.utilities;
import java.util.List;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class TestNGListners implements IReporter  {
	SendReports mail = new SendReports();
	

		@Override
		public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {
			mail.Sharereports();
 		    System.out.println("Execution has been Completed"); 			
		}
		
		

}
