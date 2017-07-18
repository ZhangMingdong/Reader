import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class XlsxFileReader {
	/*
	 * string values
	 */
	ArrayList<ArrayList<String>> _arrString=new ArrayList<ArrayList<String>>();
	
	/*
	 * numeric values
	 */
	ArrayList<ArrayList<Double>> _arrDouble=new ArrayList<ArrayList<Double>>();
	
	/*
	 * this function is copied from the web, and changed the file name, latter print the index of each line to locate the target values
	 */
	public void readMyFile() throws IOException{
		File myFile = new File("C:/Project/Reader/data/test.xlsx");
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
       
        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
       
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
       
        // Traversing over each row of XLSX file
        int rowIndex=0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println(rowIndex++);
            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    System.out.print(cell.getStringCellValue() + "\t");
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.print(cell.getNumericCellValue() + "\t");
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.print(cell.getBooleanCellValue() + "\t");
                    break;
                default :
             
                }
            }
            System.out.println("");
        }
	}
	
	/*
	 * read the target values in the files into the array
	 */
	public void readExcelFiles(String strFullPath,String strFileName) throws IOException{
		ArrayList<String> arrString=new ArrayList<String>();
		arrString.add(strFileName);
		
		File myFile = new File(strFullPath);
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
       
        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
       
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
       
        // Traversing over each row of XLSX file
        int rowIndex=0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            if(rowIndex==3){
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        arrString.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                    	Double value=cell.getNumericCellValue();
                    	arrString.add(value.toString());
                        break;
                    default :
                    	break;                 
                    }
                }
            }
            else if(rowIndex==9||rowIndex==10||rowIndex==11){
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        arrString.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                    	Double value=cell.getNumericCellValue();
                    	arrString.add(value.toString());
                        break;
                    default :
                    	break;
                 
                    }
                }
            }            
//            System.out.println(rowIndex);
            rowIndex++;
        }
//        for(String str:arrString){
//        	System.out.print(str);
//        	System.out.print("\t");
//        }
//        for(Double db:arrDouble){
//        	System.out.print(db);
//        	System.out.print("\t");
//        }
//        System.out.println();
        _arrString.add(arrString);
//        _arrDouble.add(arrDouble);
//        System.out.println(_arrDouble.size());
	}
	
	public void writeFile(){
		try {
		    //create a temporary file

		    File myFile = new File("E:/From LiangZhe/20170707/codes/newFile.txt");

		    BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
		    for(int i=0;i<_arrString.size();i++){
		    	ArrayList<String> arrString=_arrString.get(i);
//		    	ArrayList<Double> arrDouble=_arrDouble.get(i);
		        for(String str:arrString){
		        	writer.write(str);
		        	writer.write(",");
		        }
//		        for(Double db:arrDouble){
//		        	writer.write(db.toString());
//		        	writer.write(",");
//		        }
		        writer.write("\n");
		    }

		    //Close writer
		    writer.close();
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
	
	// test read a docx file
	public void testReadDocxFile(){
		 try {
             File file = new File("C:/Project/Reader/data/test2.docx");
             FileInputStream fis = new FileInputStream(file.getAbsolutePath());

             XWPFDocument document = new XWPFDocument(fis);

             List<XWPFParagraph> paragraphs = document.getParagraphs();


             for (XWPFParagraph para : paragraphs) {
                 System.out.println(para.getText());
             }
             fis.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
	}
	
	public static void main(String[] args){
		try {
			XlsxFileReader reader=new XlsxFileReader();
			reader.readMyFile();
			reader.testReadDocxFile();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hehe");
	}
}
