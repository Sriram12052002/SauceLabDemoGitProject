package reportPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class reportClass {
	public WebDriver driver;
	ExtentSparkReporter reporter;
	ExtentReports spark;
	 ExtentTest test;
	 Document doc;
	 PdfWriter writer;
	 Image img;
	 String reportDestination;
	 String ScreenShotpath;
	 
	public reportClass(WebDriver driver) throws FileNotFoundException, DocumentException {
		this.driver = driver;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
		String formatteddate = sdf.format(date);
		reportDestination = "reports"+formatteddate;
		 reporter = new ExtentSparkReporter(new File("./"+reportDestination+"/HTMLReport/SauceLabReport.html"));
		 spark = new ExtentReports();
		 spark.attachReporter(reporter);
		test = spark.createTest("Sauce Lab Demo Project");
		 test.assignAuthor("Sriram R");
		 test.assignCategory("Smoke test");
		 doc = new Document();
		 File f = new File("./"+reportDestination+"/PDFFolder/");
		 if(!f.exists()) {
		 f.mkdirs();}
		 writer = PdfWriter.getInstance(doc, new FileOutputStream("./"+reportDestination + "/PDFFolder/SauceLabPDF.pdf"));
		  ScreenShotpath =reportDestination+"/HTMLReport/";
		  File s = new File(ScreenShotpath);
		  /*if(!s.exists()) {
			  s.mkdirs() ;
		  }*/
		
		 doc.open();
		 doc.addAuthor("Sriram R");
		 doc.addTitle("Sauce Demolabs testing");
		 writer.open();
		 
		 
		
	}
	
	public void generateReportsMethod(String screenShotName,String msg) throws IOException, DocumentException {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File actual =  tss.getScreenshotAs(OutputType.FILE);
		String destination =ScreenShotpath+screenShotName+".png" ;
		File dest = new File(destination);		
		FileUtils.copyFile(actual, dest);
		System.out.println(destination);
		test.pass(msg,MediaEntityBuilder.createScreenCaptureFromPath(destination).build());
		doc.add(new Paragraph(msg));
		img = Image.getInstance(destination);
		img.scaleAbsolute(500,300);
		doc.add(img);
		doc.newPage();
		
		
	}
	
	public void closeAllReports() {
		spark.flush();
		doc.close();
		writer.close();
		
	}
}
