import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class prepareTreasureData {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/FinalProject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	static final String USER = "root";
	static final String PASSWORD = "Cl&Ysx13";
	//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
	//String[] treasrueAtt = new String[8];

	//Intermediate Lists
	//public static List<String[]> shanghaiList;
	//public static List<String[]> guGongList;
	
	public static List<String[]> treasureList;
	public static List<int[]> containList;
	
	public static List<String[]> artistList;

	public static int treasure_id = 1; 

	public static void readGuGong() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
		for(int i = 1; i< 9; i++) {
			String fileName = "./Datav17/gugong"+i+".xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);

			String category = "";
			switch(i){
			case 1:
				category = "Bronze";
				break;
			case 2:
				category = "Calligraphy";
				break;
			case 3:
				category = "Ceramic";
				break;
			case 4:
				category = "Enamel";//Enamel
				break;
			case 5:
				category = "Various Material";//Glass
				break;
			case 6:
				category = "Jade";
				break;
			case 7:
				category = "Lacquer"; //Lacquer
				break;
			case 8:
				category = "Painting";
				break;
			}
			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext()&& rowCount<575) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 1;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;


				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString().replaceAll("\"", "\\\"");
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure category
				treasureAtt[3] = category;
				//treasure image link
				Cell thirdCell = cellIterator.next();
				treasureAtt[4] = thirdCell.toString();
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells
				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();

					// Should look something like: [attribute, value];
					//example:["Period", "Ming"]
					String[] att = cell.toString().split(":",2);

					if(att.length<2) {
						continue;
					}else {
						//test code
						//System.out.print("Current cell: ");
						//System.out.println(Arrays.toString(att));

						//removing linebreak, tabs
						String attName = att[0].replaceAll("[\n\t\\?]", "");
						String attValue = att[1].replaceAll("[\n\t]", "");
						String[] artist;

						//Update attributes of one treasure based on attribute name
						switch(attName)
						{
						case "Kiln":
							treasureAtt[6] = attValue.replace(" ", "");
							//addArtist(attValue);
							break;
						case "Period":
							//TODO: extract some data, or clean it further
							treasureAtt[5] = attValue;
							//addEra(attValue);
							break;
						case "Calligrapher(s)":
							artist = attValue.split("\\(",2);
							artist[0] = capitalize(artist[0]).replace(" ", "");
							treasureAtt[6] = artist[0];
							//addArtist(attValue);
							break;
						case "Artist(s)":
							//TODO: extract data(maybe)
							artist = attValue.split("\\(",2);
							artist[0] = capitalize(artist[0]).replace(" ","");
							treasureAtt[6] = artist[0];
							//addArtist(attValue);
							break;
							/*					case "Dimensions":
						treasureAtt[7] = attValue;
						break;*/
						}	
					}	
				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
		}

	}


	public static void readShanghai() throws Exception {
		File excelFile = new File("./Datav17/shanghai.xlsx");
		FileInputStream fis;
		fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);    
		XSSFSheet sheet = workbook.getSheetAt(0);

		//test var
		int rowCount = 0;

		//iterate on rows
		Iterator<Row> rowIt = sheet.iterator();
		int totalRow = 494;
		while(rowIt.hasNext() && rowCount < totalRow) {
			rowCount++;
			Row row = rowIt.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			//Data for Table Contain 
			int[] contain = new int[2];
			//museum id
			contain[0] = 2;				
			//treasure id
			contain[1] = treasure_id;
			containList.add(contain);

			//Data for Table Treasure
			String[] treasureAtt = new String[8];
			//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
			treasureAtt[0]= ""+treasure_id++;


			Cell firstCell = cellIterator.next();
			//treasure name
			treasureAtt[1] = firstCell.toString();
			//treasure link
			Cell secondCell = cellIterator.next();
			treasureAtt[2] = secondCell.toString();
			//treasure category
			Cell thirdCell = cellIterator.next();
			switch(thirdCell.toString()) {
			case "1":
				treasureAtt[3]="Bronze";
				break;
			case "2":
				treasureAtt[3]="Sculpture";
				break;
			case "3":
				treasureAtt[3]="Seal";
				break;
			case "4":
				treasureAtt[3]="Coin";
				break;
			case "5":
				treasureAtt[3]="Ceramic";
				break;
			case "6":
				treasureAtt[3]="Calligraphy";
				break;
			case "7":
				treasureAtt[3]="Painting";
				break;
			case "8":
				treasureAtt[3]="Jade";
				break;
			case "9":
				treasureAtt[3]="Others";
				break;
			}
			//treasure image link
			Cell forthCell = cellIterator.next();
			treasureAtt[4] = forthCell.toString();
			//is_centerpiece; default0, update separately
			treasureAtt[7] = ""+0;

			while (cellIterator.hasNext()) {
				//get current cell
				Cell cell = cellIterator.next();
				//test
				//cellCount++;

				String[] att = cell.toString().split(":",2);
				if(att.length<2) {
					continue;
				}else {
					//test code
					//System.out.print("Current cell: ");
					//System.out.println(Arrays.toString(att));

					//removing linebreak, tabs
					String attName = att[0].replaceAll("[\n\t]", "");
					String attValue = att[1].replaceAll("[\n\t]", "");

					//Update attributes of one treasure based on attribute name
					switch(attName)
					{
					case "Artist":
						String[] artist = attValue.split("\\(",2);
						artist[0] = capitalize(artist[0]).replace(" ", "");
						treasureAtt[6] = artist[0];
						break;
					case "Date":
						treasureAtt[5] = attValue;
						break;

					}	
				}
			}//end iterating row	
			treasureList.add(treasureAtt);
			//System.out.print("Printing row: "+ rowCount + " ");
			//System.out.println(Arrays.toString(treasureAtt));
			//System.out.println(treasureAtt[1]);
		}
		workbook.close();
		fis.close();
	}

	public static void readHunan() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/hunan.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext()) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 3;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;


				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString();			
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				treasureAtt[4] = thirdCell.toString();
				//treasure category
				Cell forthCell = cellIterator.next();
				switch(forthCell.toString()) {
				case "Modern and Contemporary":
					treasureAtt[3]="Others";
					break;
				case "Paintings":
					treasureAtt[3]="Painting";
					break;
				case "Calligraphy Rubbings":
					treasureAtt[3]="Calligraphy";
					break;
				case "Ceramics":
					treasureAtt[3]="Ceramic";
					break;
				case "He Shaoji":
					treasureAtt[3]="Others";
					break;
				case "Others":
					treasureAtt[3]="Others";
					break;
				}		
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells

				int attCount = 0;
				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					if(!cell.toString().equals(" ")) {
						if(attCount ==0) {
						treasureAtt[5] = cell.toString();
						}else {
							String[] artist = cell.toString().split("\\(",2);
							if(artist[0]!="") {
							artist[0] = capitalize(artist[0]);
							treasureAtt[6] = artist[0];		
							}else {
								treasureAtt[6] = artist[0];	
							}
						}
					}
					attCount++;
					//treasureAtt[5] = cell.toString().replace(" ", "");	

				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}

	public static void readHenan() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/henan.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext() && rowCount<59) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 4;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;

				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString();			
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				if(!thirdCell.toString().equals(" ")) {
				treasureAtt[4] = thirdCell.toString();
				}
				//treasure category
				Cell forthCell = cellIterator.next();
				switch(forthCell.toString()) {
				case "Jade":
					treasureAtt[3]="Jade";
					break;
				case "Bronze":
					treasureAtt[3]="Bronze";
					break;
				case "Stone graving":
					treasureAtt[3]="Craving";
					break;
				case "Porcelain":
					treasureAtt[3]="Ceramic";
					break;
				case "Pottery":
					treasureAtt[3]="Ceramic";
					break;
				case "Others":
					treasureAtt[3]="Others";
					break;
				}		
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells

				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					if(!cell.toString().equals(" ")) {
						treasureAtt[5] = cell.toString().replace(" ", "");
					}else {
					}				
					//treasureAtt[5] = cell.toString().replace(" ", "");	
				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}

	
	public static void readShanxi() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/shanxi.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext() && rowCount<12) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 5;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;

				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString();			
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				if(!thirdCell.toString().equals(" ")) {
				treasureAtt[4] = thirdCell.toString();
				}
				//treasure category
				Cell forthCell = cellIterator.next();
				treasureAtt[3] = forthCell.toString();
				
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells

				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					if(!cell.toString().equals(" ")) {
						treasureAtt[5] = cell.toString();
					}else {
					}				
					//treasureAtt[5] = cell.toString().replace(" ", "");	

				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}
	
	public static void readHubei() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/hubei.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext()) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 6;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;


				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString();			
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				treasureAtt[4] = thirdCell.toString();
				//treasure category
				Cell forthCell = cellIterator.next();
				switch(forthCell.toString()) {
				case "Bamboo slips":
					treasureAtt[3]="Others";
					break;
				case "Painting":
					treasureAtt[3]="Painting";
					break;
				case "Calligraphy":
					treasureAtt[3]="Calligraphy";
					break;
				case "Chinaware":
					treasureAtt[3]="Ceramic";
					break;
				case "Gold and Silver Ware":
					treasureAtt[3]="Various Material";
					break;
				case "Jadeware":
					treasureAtt[3] = "Jade";
					break;
				case "lacquer ware":
					treasureAtt[3] = "Lacquer"; //Lacquer
					break;
				case "Bronze Ware":
					treasureAtt[3] = "Bronze"; //Lacquer
					break;
				case "Others":
					treasureAtt[3]="Others";
					break;
				}		
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells

				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					if(!cell.toString().equals(" ")) {
						treasureAtt[5] = cell.toString();
					}else {
					}				
					//treasureAtt[5] = cell.toString().replace(" ", "");	

					Cell cell2 = cellIterator.next();
					if(!cell2.toString().equals(" ")) {
						String[] artist = cell2.toString().split("\\(",2);
						artist[0] = capitalize(artist[0]).replace(" ", "");
						treasureAtt[6] = artist[0];
					}else {
					}
				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}

	public static void readZhejiang() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/zhejiang.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);
			String category = "";

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext()) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 7;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;

				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString();
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				treasureAtt[4] = thirdCell.toString();
				//treasure category
				Cell forthCell = cellIterator.next();
				switch(forthCell.toString()) {
				case "Jade Ware":
					treasureAtt[3]="Jade";
					break;
				case "Lacquer Ware":
					treasureAtt[3]="Lacquer";
					break;
				case "Buddhist scriptures":
					treasureAtt[3]="Buddhist Statues";
					break;
				case "Buddhist statues":
					treasureAtt[3]="Buddhist Statues";
					break;
				case "Gold and silver ware":
					treasureAtt[3]="Various Material";
					break;
				case "Ceramics":
					treasureAtt[3]="Ceramic";
					break;
				case "Paintings":
					treasureAtt[3]="Painting";
					break;
				case "Artefacts of bamboo, wood, ivory or horn":
					treasureAtt[3]="Various Material";
					break;
				case "Seals":
					treasureAtt[3]="Seal";
					break;
				case "Miscellaneous":
					treasureAtt[3]="Others";
					break;
				}
				
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells
				int attCount = 0;
				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					if(!cell.toString().equals(" ")) {
						if(attCount ==0) {
						treasureAtt[5] = cell.toString();
						}else {
							String[] artist = cell.toString().split("\\(",2);
							artist[0] = capitalize(artist[0]);
							treasureAtt[6] = artist[0];							
						}
					}
					attCount++;
					//treasureAtt[5] = cell.toString().replace(" ", "");	

				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}
	
	
	public static void readLiaoning() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/liaoning.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);
			String category = "";

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext()) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 8;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;

				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString();				
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				treasureAtt[4] = thirdCell.toString();
				//treasure category
				Cell forthCell = cellIterator.next();
				switch(forthCell.toString()) {
				case "Jade Ware":
					treasureAtt[3]="Jade";
					break;
				case "Lacquer Ware":
					treasureAtt[3]="Lacquer";
					break;
				case "Painting":
					treasureAtt[3]="Painting";
					break;
				case "Currency":
					treasureAtt[3]="Coins";
					break;
				case "Ceramics":
					treasureAtt[3]="Ceramic";
					break;
				case "Enamel ware":
					treasureAtt[3]="Enamel";
					break;
				case "Gold and silver ware":
					treasureAtt[3]="Various Material";
					break;
				case "Bamboo, wood and ivo":
					treasureAtt[3]="Various Material";
					break;
				case "Others":
					treasureAtt[3]="Others";
					break;
				case "Epitaph":
					treasureAtt[3]="Others";
					break;
				case "Kesi and Embroidery":
					treasureAtt[3]="Others";
					break;
				case "Buddha statues":
					treasureAtt[3]="Buddha Statues";
					break;
				}
				
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				int attCount = 0;
				//iterating one row on cells
				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					if(!cell.toString().equals(" ")) {
						if(attCount ==0) {
						treasureAtt[5] = cell.toString();
						}else {
							String[] artist = cell.toString().split("\\(",2);
							artist[0] = capitalize(artist[0]);
							treasureAtt[6] = artist[0];							
						}
					}
					attCount++;
					//treasureAtt[5] = cell.toString().replace(" ", "");	

				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}
	
	
	public static void readSuzhou() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/suzhou.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext()) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 9;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;

				Cell firstCell = cellIterator.next();
				//treasure name
				treasureAtt[1] = firstCell.toString();				
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				treasureAtt[4] = thirdCell.toString();
				//treasure category
				Cell forthCell = cellIterator.next();
				//category
				treasureAtt[3]= forthCell.toString();
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells
				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					treasureAtt[5] = cell.toString();					
				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}
	
	public static void readAnhui() throws Exception {
		XSSFWorkbook workbook;
		FileInputStream fis;
			String fileName = "./Datav17/anhui.xlsx";
			File excelFile = new File(fileName);
			fis = new FileInputStream(excelFile);

			// we create an XSSF Workbook object for our XLSX Excel File
			workbook = new XSSFWorkbook(fis);      
			XSSFSheet sheet = workbook.getSheetAt(0);
			String category = "";

			//test var
			int rowCount = 0;

			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while(rowIt.hasNext()) {
				rowCount++;
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				//Data for Table Contain 
				int[] contain = new int[2];
				//museum id
				contain[0] = 10;				
				//treasure id
				contain[1] = treasure_id;
				containList.add(contain);

				//Data for Table Treasure
				String[] treasureAtt = new String[8];
				//treasure(id, name, link,category, img-link, era, artist, is_centerpiece)
				treasureAtt[0]= ""+treasure_id++;

				Cell firstCell = cellIterator.next();
				//treasure name
				String[] info = firstCell.toString().split("by", 2);
				if(info.length ==2) {
					treasureAtt[1] = info[0];
					treasureAtt[6]=info[1];
				}else {
					treasureAtt[1] = info[0];
				}
				
				//treasure link
				Cell secondCell = cellIterator.next();
				treasureAtt[2] = secondCell.toString();
				//treasure image link
				Cell thirdCell = cellIterator.next();
				//no image
				//treasureAtt[4] = thirdCell.toString();
				//treasure category
				switch(thirdCell.toString()) {
				case "Bronze Ware":
					treasureAtt[3]="Bronze";
					break;
				case "Pan Yuliang��s Paintings":
					treasureAtt[3]="Painting";
					break;
				case "��Three Carvings�� of Brick, Stone and Wood Carvings":
					treasureAtt[3]="Carving";
					break;
				case "Ceramics":
					treasureAtt[3]="Ceramic";
					break;
				case "Calligraphy and Paintings":
					treasureAtt[3]="Painting";
					break;
				case "Gold, Silver and Jade Ware":
					treasureAtt[3]="Various Material";
					break;
				case "Four Treasures of the Study":
					treasureAtt[3]="Others";
					break;
				case "huizhou carvings":
					treasureAtt[3]="Carving";
					break;
				}
				
				//is_centerpiece; default0, update separately
				treasureAtt[7] = ""+0;

				//iterating one row on cells
				while (cellIterator.hasNext()) {
					//get current cell
					Cell cell = cellIterator.next();
					treasureAtt[5] = cell.toString();					
				}
				treasureList.add(treasureAtt);
				//System.out.print("Printing row: "+ rowCount + " ");
				//System.out.println(Arrays.toString(treasureAtt));	
				//System.out.println(treasureAtt[1]);
			}
			workbook.close();
			fis.close();
	}
	
	
	public static void insertTreasure() {
		//TODO: Write
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// try to register the JDBC driver
			Class.forName(JDBC_DRIVER);
			// open connection
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		


			System.out.println("Inserting into the treasure table ..");
			
			//loop through records to create prepared statements
			for (int i = 0; i < treasureList.size(); i++) {
				String insertTableSQL = "INSERT INTO treasure VALUES (?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(insertTableSQL);
				
				String[] treasure = treasureList.get(i);
				
				ps.setInt(1, Integer.parseInt(treasure[0]));
				ps.setString(2, treasure[1]);
				ps.setString(3, treasure[2]);
				ps.setString(4, treasure[3]);
				ps.setString(5, treasure[4]);
				ps.setString(6, treasure[5]);
				ps.setString(7, treasure[6]);
				ps.setInt(8, Integer.parseInt(treasure[7]));
		
				System.out.println(ps);
				ps.executeUpdate();		
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertContain() {
		//TODO: Write
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			System.out.println("Inserting into the contain table ..");

			for (int i = 0; i < containList.size(); i++) {
				String insertTableSQL = "INSERT INTO Contain VALUES (?,?)";
				ps = conn.prepareStatement(insertTableSQL);

				ps.setInt(1, containList.get(i)[0]);
				ps.setInt(2, containList.get(i)[1]);

				System.out.println(ps);
				ps.executeUpdate();		
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void insertTreasureSQL()
	{
		Writer writer = null;
		
		// FIX INPUT
		// INSERT INTO Players (player_id, tag, real_name, nationality, birthday, game_race)
		// VALUES (treasure_id, )
		String prefix = "INSERT INTO Treasure VALUES (";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("insertTreasure.sql"), "utf-8"));
		
			for (int i = 0; i < treasureList.size(); i++) {
				
				String p1 = treasureList.get(i)[0];
				String p2 = treasureList.get(i)[1];
				String p3 = treasureList.get(i)[2];
				String p4 = treasureList.get(i)[3];
				String p5 = treasureList.get(i)[4];
				String p6 = treasureList.get(i)[5];
				if(p6!=null) {
					p6=p6.trim();
				}
				String p7 = treasureList.get(i)[6];
				if(p7!=null) {
					p7=p7.trim();
				}
				String p8 = treasureList.get(i)[7];
				
				String query = prefix + p1 + ",\"" + p2 + "\",\"" + p3 + "\",\"" + p4 + "\",\"" + p5 + "\",\"" + p6 + "\",\""+p7+"\"," +p8 +"); \n";
				System.out.println(p7);
				
				writer.write(query);
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			try {writer.close();} catch (Exception ex) {//ignore 
				}
			}
		System.out.println("Print finished");
	}
	
	public static void insertContainSQL() {
		Writer writer = null;
		String prefix = "INSERT INTO Contain VALUES (";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("insertContain.sql"), "utf-8"));
		
			for (int i = 0; i < containList.size(); i++) {
				
				int p1 = containList.get(i)[0];
				int p2 = containList.get(i)[1];
				
				String query = prefix + p1 +","+ p2 +"); \n";
				//System.out.println(query);
				
				writer.write(query);
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			try {writer.close();} catch (Exception ex) {
				//ignore
				}
			}
		System.out.println("Print finished");
	}
	
	private static int getCurrentId() {
		return treasure_id;
	}
	
	private static String capitalize(String line) {
		   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
		}
	
	private static void addArtist(String artistInfo) {
		// TODO Auto-generated method stub
		//split by , for with more than 1 artist 
		//for each artist
			//Gugoong:
			//split be ( ->have [name, lifespan]
			//remove all but digits,-, "th",
		//then add to artistList if not exist:
		//if(!contains(String[]){list.add(String[]);}
		String[] artists = artistInfo.split(",");
		for(int i = 0; i < artists.length;i++) {
			String[] artist = artists[i].split("(",2);
			artist[0] = capitalize(artist[0]);
			artist[1]= artist[1].replaceAll("[^\\d-]", "");
			String[] lifespan = artist[1].split("-",2);
			String[] artistRow = new String[3];
			artistRow[0] = artist[0];
			if(lifespan.length ==2) {
				artistRow[1] = lifespan[0];
				artistRow[2] = lifespan[1];
			}else {
				artistRow[1] = "";
				artistRow[2] = "";
			}
			
			if(!artistList.contains(artistRow)) {
				artistList.add(artistRow);
			}
		}
	}
	public static void main(String[] args) {
		try {
			treasureList = new ArrayList<>();
			containList = new ArrayList<>();
			readGuGong();
			System.out.println("Done reading in GuGongs");
			
			readShanghai();
			System.out.println("Done reading in shanghai");
			
			readHunan();
			System.out.println("Done reading in Hunan");
			
			readHenan();
			System.out.println("Done reading in Henan");
			
			readShanxi();
			System.out.println("Done reading in Shanxi");
			
			readHubei();
			System.out.println("Done reading in Hubei");
			
			readZhejiang();
			System.out.println("Done reading in Zhejian");
			
			readLiaoning();
			System.out.println("Done reading in Liaoning");
			
			readSuzhou();
			System.out.println("Done reading in Suzhou");
			
			readAnhui();
			System.out.println("Done reading in Anhui");
			
			insertTreasureSQL();
			System.out.print("Done inserting treasure list");
			
			//insertContainSQL();
			//System.out.print("Done reading in contain list");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
