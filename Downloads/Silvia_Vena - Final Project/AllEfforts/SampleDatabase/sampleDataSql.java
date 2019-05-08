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

public class sampleDataSql {

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
		try (Scanner scan = new Scanner (new File("treasure.csv"));) {
			while(scan.hasNextLine()) {
				//List<String> read;
				treasure.add(getRecordFromLine(scan.nextLine()));
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//System.out.println(players);
		
		// FIX INPUT
		// INSERT INTO Players (player_id, tag, real_name, nationality, birthday, game_race)
		// VALUES (treasure_id, )
		String prefix = "INSERT INTO Treasure VALUES (";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("insertSampleTreasure2.sql"), "utf-8"));
		
			for (int i = 0; i < treasure.size(); i++) {
				
				String p1 = treasure.get(i).get(0);
				String p2 = treasure.get(i).get(1);
				String p3 = treasure.get(i).get(2);
				String p4 = treasure.get(i).get(3);
				String p5 = treasure.get(i).get(4);
				String p6 = treasure.get(i).get(5);
				String p7 = treasure.get(i).get(6);
				String p8 = treasure.get(i).get(7);
				
				String query = prefix + p1 + ", \"" + p2 + "\",\"" + p3 + "\",\"" + p4 + "\",\"" + p5 + "\",\"" + p6 + "\",\""+p7+"\"," +p8 +"); \n";
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

