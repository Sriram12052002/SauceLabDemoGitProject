package DataPackage;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class UserDetailsClass {

	@DataProvider(name = "userDataProviderMethod", parallel = true)
	public String [][] userDetailsData() throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook("./Utilities/UserDetails.xlsx");
		XSSFSheet sh = wb.getSheetAt(0);
		String userData [][] = null;
		
		int rowSize = sh.getPhysicalNumberOfRows();
		
		for(int i = 1;i<rowSize;i++) {
			XSSFRow row = 	sh.getRow(1);
			
			int columnSize = row.getPhysicalNumberOfCells();
			 userData  =  new String [rowSize-1][columnSize];
 		for(int j=0;j<columnSize;j++) {
 		XSSFCell cell= 	row.getCell(j);
 		DataFormatter df = new DataFormatter();
 		String data = df.formatCellValue(cell);
 		userData[i-1][j]=data;
 		}
		}
		return userData;
	}
}
