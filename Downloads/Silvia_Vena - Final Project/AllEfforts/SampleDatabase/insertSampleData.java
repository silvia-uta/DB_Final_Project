import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class insertSampleData {

	// establish connections to the database
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Midterm";
	static final String USER = "root";
	static final String PASSWORD = "password";
	// save these information into a dbparam.txt
	

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

	

	public static void insertTreasure() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// try to register the JDBC driver
			Class.forName(JDBC_DRIVER);
			// open connection
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		

			// format the string from reading in data from csv
			// execute that
			

			// read in a csv
			List<List<String>> treasureRecords = new ArrayList<>();
			try (Scanner scan = new Scanner (new File("treasure.csv"));) {
				while(scan.hasNextLine()) {
					//List<String> treasureRecords;
					treasureRecords.add(getRecordFromLine(scan.nextLine()));					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(treasureRecords);


			System.out.println("Insert into the treasure table ..");
			
			//loop through records to create prepared statements
			for (int i = 0; i < treasureRecords.size(); i++) {
				String insertTableSQL = "INSERT INTO treasure VALUES (?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(insertTableSQL);

				ps.setInt(1, Integer.parseInt(treasureRecords.get(i).get(0)));
				ps.setString(2, treasureRecords.get(i).get(1));
				ps.setString(3, treasureRecords.get(i).get(2));
				ps.setString(4, treasureRecords.get(i).get(3));
				ps.setString(5, treasureRecords.get(i).get(4));
				ps.setString(6, treasureRecords.get(i).get(5));
				ps.setString(7, treasureRecords.get(i).get(6));
				ps.setInt(8, Integer.parseInt(treasureRecords.get(i).get(7)));

				

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
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			// read in a csv
			List<List<String>> containRecords = new ArrayList<>();
			try (Scanner scan = new Scanner (new File("contain.csv"));) {
				while(scan.hasNextLine()) {
					containRecords.add(getRecordFromLine(scan.nextLine()));					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// System.out.printLn(containRecords);
			

			System.out.println("Insert into the contain table ..");

			for (int i = 0; i < containRecords.size(); i++) {
				String insertTableSQL = "INSERT INTO contain VALUES (?,?)";
				ps = conn.prepareStatement(insertTableSQL);

				ps.setInt(1, Integer.parseInt(containRecords.get(i).get(0)));
				ps.setInt(2, Integer.parseInt(containRecords.get(i).get(1)));

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


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertTreasure();
		//insertContain();
	}

}
