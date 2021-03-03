package org.test;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;


public class ReusableCode {
	static WebDriver driver;
	static Actions a;
	static JavascriptExecutor js;
	static Select se;
	static Alert al;
	
	
	//Browser configuration
	public static void getDriver() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\shano\\eclipse-workspace\\ReusableCode\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	
	}
	
	//Load URL
	public static  void loadUrl(String url) {
		driver.get(url);

	}
	
	//Max Window
	public static void maxWindow() {
        driver.manage().window().maximize();
	}
	// To pass input to text box
	 public static void typeInput(WebElement element , String data) {
	      element.sendKeys(data);
	}
	 
	// To click Button
     public static  void btnClick(WebElement element) {
    element.click();
}
     
  // To fetch text from webpage
     public static void fetchText(WebElement element) {
    String text = element.getText();
    System.out.println(text);
     }
     
     
   //To get Attribute method
     public static void getAttr(WebElement element, String name) {
String attribute = element.getAttribute(name);
System.out.println(attribute);
}
     
     
  // Action class - to perform right click
     
     public static void rightClick(WebElement element) {
Actions a = new Actions(driver);
     a.contextClick(element).perform();;
}
     
  // to perform Double click
     
     public static void doubleClick(WebElement element) {
     a = new Actions(driver);
a.doubleClick(element).perform();
}
//Action -Move to element
     public static void movetoElemnt(WebElement element) {
    a = new Actions(driver);
      a.moveToElement(element).perform(); }

// DragDrop
     public static void dragDrop(WebElement source , WebElement destination) {
a.dragAndDrop(source, destination);
}
// Scroll Up and Scroll Down
     
     public static void Javascript() {
JavascriptExecutor  js = (JavascriptExecutor) driver;
}
// Scroll Down
     public static void scrollDown(WebElement element)
     {   JavascriptExecutor  js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true)", element);
     }
   //scroll UP
     
     public static void scrollUp(WebElement element)
     {   JavascriptExecutor  js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(false)", element);
     }
   
   //Screenshot
     
     public static void Screenshot(String despath) throws IOException {
TakesScreenshot t = (TakesScreenshot) driver;
File src = t.getScreenshotAs(OutputType.FILE);
File des = new File(despath);
Files.copy(src,des);


   
}
    // Alert -- To accept
     public static void alert() {

     al = driver.switchTo().alert();
     al.accept();
}
  // Alert -- To dismiss
     public  static void dismiss() {
     al= driver.switchTo().alert();
    al.dismiss();
}
   
     
 //    drop down
     //select by index

     public static  void selectindex(WebElement element, int s) {
se = new Select(element);
se.selectByIndex(s);
}
     
   // select by value
     public static  void selectValue(WebElement element, String g) {
se = new Select(element);
se.selectByValue(g);
     }
     
     // select by visible text
     
     public static  void selectVisible(WebElement element, String g) {
se = new Select(element);
se.selectByValue(g);
      }
     
     
     // deselect by index
     
     public static  void deselectindex(WebElement element, int y) {
     se = new Select(element);
    se.deselectByIndex(y);
}
     
     // deselect by value
     public static  void deselectValue(WebElement element, String o) {
se = new Select(element);
se.deselectByValue(o);
     }
     
     //deselect by visible text
     
     public static  void deselectVisible(WebElement element, String l) {
se = new Select(element);
se.selectByValue(l);
       }
// to get all the options from dropdown

   public static void getAllOptions(WebElement element)
   {
  Select se = new Select(element);
  List<WebElement> options = se.getOptions();
       for (int i = 0; i < options.size(); i++) {
WebElement op = options.get(i);
String text = op.getText();
System.out.println(text);
}  
   
   }
   
   // to check whether single or multiple selected drop down
    public static void multiple(WebElement element) {
    Select se = new Select(element);
    boolean x = se.isMultiple();
    System.out.println(x);

           
}
   
   public static void ExcelRead()
   {

           
   }
   
   
   
public static void excelWrite (int row, int cell, String value) throws IOException {
File f = new File("C:\\Users\\srira\\eclipse-workspace\\SriRam\\Reusable\\Excel\\SampShee.XISX.xlsx");
FileInputStream fin = new FileInputStream(f);
Workbook w = new XSSFWorkbook(fin);
Sheet s = w.createSheet("orderno");
Row r = s.createRow(row);
Cell c = r.createCell(cell);

c.setCellValue(value);

FileOutputStream fout = new FileOutputStream(f);
w.write(fout);
}


public static String excelRead(int row,int cell) throws IOException
{
File f = new File("C:\\Users\\srira\\eclipse-workspace\\SriRam\\Reusable\\Excel\\SampShee.XISX.xlsx");
FileInputStream fin = new FileInputStream(f);
Workbook w = new XSSFWorkbook(fin);
Sheet s = w.getSheet("Sheet1");
String value ="";
Row r = s.getRow(row);
    Cell c = r.getCell(cell);

int cellType = c.getCellType();
System.out.println(cellType);
{
if(cellType==1)
{

value = c.getStringCellValue();
System.out.println(value);
}
else if (DateUtil.isCellDateFormatted(c)) {


Date dateCellValue = c.getDateCellValue();
SimpleDateFormat simple = new SimpleDateFormat("dd/mm/yyyy");
value = simple.format(dateCellValue);
System.out.println(value);
}
           

else {
double numericCellValue = c.getNumericCellValue();
long l = (long) numericCellValue;
value = String.valueOf(l);
}
return value;

}





           
}
public static void close() {
driver.close();
//

}

public static void quit() {
driver.quit();

}



}


	
	
	
	


