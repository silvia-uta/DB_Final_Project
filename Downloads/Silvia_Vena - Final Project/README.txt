A. 
Our code is an Android Studio Project and a folder that contain our efforts for this project. 
	1. Main Project: MyTreasures
	2. Additional Stuff: The AllEfforts folder includes all codes of effort for the project, including:
		- CreateDatabase: Java files that clean and read in data and .sql files for creating the real database
		- SampleDatabase: Java files and in creating the sample databases
		- Query: List of .sql files of mySQL queries designed for the App (some including test queries)

B. Using our System:
	1. Download and unzip the project.
	2. Open MyTreasures Folder in Android Studio.
	3. Change the URL into your phpMyAdmin server IP.
	4. Build the App
	5. Run it with an emulator.
	6. Click on buttons to search for treasures, check museum, and save to lists

C. Limitation of our current app:
	1. Our app is designed for local instance only, so data for list, searches are all shared by different users. For users to see their own searches and lists, we need to create an User identity/table with the login information and user_id, and the identities Lists, Search,and Clicked all can only be identified by User.
	2. We need to catch potential sql query errors resulted by an user's action and respond with approprate messages in situations such as: 
		- when the user tried to insert treasure to a list when the treasure already exists 
		- when the user tried to create a list with the same name as an existing list
	3. Some image url are not compatible, so the image cannot be shown.
	4. Did not finish implement all designs
