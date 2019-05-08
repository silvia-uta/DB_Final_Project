import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class prepareMuseum {

	public static List<List<String>> museumRecords;

	public static void readMuseum() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
		String fileName = "./Datav17/museum.xlsx";
		File excelFile = new File(fileName);
		fis = new FileInputStream(excelFile);

		// we create an XSSF Workbook object for our XLSX Excel File
		workbook = new XSSFWorkbook(fis);      
		XSSFSheet sheet = workbook.getSheetAt(0);

		//iterate on rows
		Iterator<Row> rowIt = sheet.iterator();

		while(rowIt.hasNext()) {
			Row row = rowIt.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			//Data for Table Treasure
			//museum(id,name,street_adress ,district,city,zipcode,province,website,`description`,theme_color,year_founded 
			//iterating one row on cells
			List<String> values = new ArrayList<String>();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				values.add(cell.toString().replace("\"", ""));
			}
			museumRecords.add(values);
			System.out.println();

			//treasureList.add(treasureAtt);
			printArray(values);	
			//System.out.println(treasureAtt[1]);
		}
		workbook.close();
		fis.close();
	}
	private static void printArray(List<String> values){
		for(int i = 0; i < values.size();i++) {
			System.out.print(values.get(i)+" , ");
		}
	}

	public static void createSQl() {
		Writer writer = null;

		List<List<String>> treasure = new ArrayList<>();

		// VALUES (treasure_id, )
		String prefix = "INSERT INTO Museum VALUES (";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("insertMuseum.sql"), "utf-8"));

			for (int i = 0; i < museumRecords.size(); i++) {

				String p1 = museumRecords.get(i).get(0);
				String p2 = museumRecords.get(i).get(1);
				String p3 = museumRecords.get(i).get(2);
				String p4 = museumRecords.get(i).get(3);
				String p5 = museumRecords.get(i).get(4);
				String p6 = museumRecords.get(i).get(5);
				String p7 = museumRecords.get(i).get(6);
				String p8 = museumRecords.get(i).get(7);
				String p9 = museumRecords.get(i).get(8);
				String p10 = museumRecords.get(i).get(9);
				String p11 = museumRecords.get(i).get(10);

				String query = prefix + p1 + ", \"" + p2 + "\",\"" + p3 + "\",\"" + p4 + "\",\"" 
				+ p5 + "\",\"" + p6 + "\",\""+p7+"\",\"" +p8
				+ "\",\"" + p9 + "\",\""+p10+"\"," +p11+"); \n";
				System.out.println(query);

				writer.write(query);
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			try {writer.close();} catch (Exception ex) {//ignore}
			}

			System.out.println("Print finished");
		}
	}
	public static void main(String[] args) {
		museumRecords = new ArrayList<>();
		try {
			readMuseum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createSQl();
		// TODO Auto-generated method stub

	}

}
