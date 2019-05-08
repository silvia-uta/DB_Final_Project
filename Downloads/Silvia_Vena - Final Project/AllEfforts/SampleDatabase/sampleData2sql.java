import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class sampleData2sql {

	private static  List<String>  getRecordFromLine(String line) {

	    List<String> values = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	    }
	    return values;
	}
	
	public static void main (String args[]) {
		
		Writer writer = null;
		
		List<List<String>> treasure = new ArrayList<>();
		try (Scanner scan = new Scanner (new File("contain.csv"));) {
			while(scan.hasNextLine()) {
				//List<String> read;
				treasure.add(getRecordFromLine(scan.nextLine()));
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// FIX INPUT
		// INSERT INTO tableName
		// VALUES 
		String prefix = "INSERT INTO Contain VALUES (";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("insertSampleContain2.sql"), "utf-8"));
		
			for (int i = 0; i < treasure.size(); i++) {
				
				String p1 = treasure.get(i).get(0);
				String p2 = treasure.get(i).get(1);
				
				String query = prefix + p1 +","+ p2 +"); \n";
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
	
/*	public static void main(String[] args) throws FileNotFoundException {    
	    File file=new File("C:\\Users\\Administrator\\Documents\\Oxy\\Acadamic\\2018-2019\\COMP373\\Project\\SampleData\\treasure.csv");
	    System.out.println(file.exists());
	    Scanner scan=new Scanner(file);
	    file.close();
	}*/
}
}

