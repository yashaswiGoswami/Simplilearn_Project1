package com.simplilearn.project.code;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainInterface {

	static Scanner keyboard;
	static FileOperator fo;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//MainInterface mi= new MainInterface();
		fo= new FileOperator();
		try {
			fo.createFile("MetaList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Calls createFile() to create a file with the given name.
		
		/*
		 * 1. Load listed files
		 * 2. Add Files[You need to create a .txt file manually. and choose (4. Add files) to load it manually.]
		 * 3. Show all files in folder()
		 * 4. Add existing files
		 * 5. Delete Files [system will give list of all files and ask for the file number to delete.]
		 * */
		
		
		System.out.println("------------------Welcome to the Simplilearn File management project.------------------------");
		System.out.println("--------------------------------------Developer Name : Yashaswi Goswami--------------------------------------");
		System.out.println("This interface lets you view the file existing in the project directory/files./n");
		System.out.println("You can create a new file in the same directry and load file by choosing add files option./n");
		System.out.println("Also you can delete the files from the list\n Remember the files are only visible oonce they are added.");
		start();
		showOptions();
		
		
		
		//loadFiles();
		
	}

	private static void loadFiles() {
		// TODO Auto-generated method stub
		//Load files existing in the metalist.txt or shows empty if the metalist.text is empty.
	   fo=new FileOperator();
	   try {
		fo.readMetaList();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   showAllOptions();
	}

	private static void start() {
		// TODO Auto-generated method stub
		
		System.out.println("--------------Press Enter key to start------------");
		keyboard= new Scanner(System.in);
		String s= keyboard.nextLine();
		System.out.println(s);
		if(s!=null) {
			showOptions();
		}
		else {
			keyboard.close();

			start();

		}

	}
	public static void showAllOptions() {
		String fname;
		int s=0;
		System.out.println("1. Press '1' to Load listed Files.");
		System.out.println("2. Show all listed/unlisted files in directory.");
		System.out.println("3. Press 3 to create a .txt file.");
		System.out.println("4. Press 4 to delete a File.");
		keyboard= new Scanner(System.in);		
		
		String choice=keyboard.nextLine();
		while(!(choice.contentEquals("")) && choice!=null) {
		s = Integer.parseInt(choice);
		if (s==0) {
			System.out.println("Wrong option, Kimdly choose one option");
			showAllOptions();
		}
		switch (s)
		{
		case 1:
			//Load files existing in the metalist.txt or shows empty if the metalist.text is empty.
			loadFiles();
		case 2:
			//Shows all files existing in directory.
			loadAllFiles();
		case 3:
			System.out.println("Enter the file name to create a file. /n Example: NewFile.txt");
			fname= validateFileName(keyboard.nextLine());
			if(fname!=null) {
				try {
					fo.createFile(fname);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}				
			else {
				System.out.println("Filename cannot be empty");
				showAllOptions();
			}
			
			//System.out.println("File "+fname+" created.");
			showAllOptions();
		case 4: 
			System.out.println("Enter the file name to Delete a file. /n Example: NewFile.txt");
			fname= validateFileName(keyboard.nextLine());
			if(fname!=null) {
				fo.deleteFile(fname);
				showAllOptions();

			}else {
				System.out.println("Filename cannot be empty");
				showAllOptions();

			}
			
		}
	}

	}

	private static void loadAllFiles() {
		// TODO Auto-generated method stub
		
		fo.loadAllFiles();
		showAllOptions();
	}

	private static String validateFileName(String nextLine) {
		// TODO Method for validating the filename
		if(nextLine!=null) {
		if(nextLine.contains(".")) {
			String name[];

			name=nextLine.split("[.]");
			if(name.length>2)
			{
				System.out.println("The file name is wrong.Returning to main menu");
				showAllOptions();
			}
			Pattern my_pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		    Matcher my_match = my_pattern.matcher(name[0]);
		    boolean check = my_match.find();
		    if(check)
		    {
		    	System.out.println("The filename cannot contain special characters.");
		    	showAllOptions();
		    }
			//System.out.println("creating file with the file name "+name[0]);
			return name[0];
		}
		else
		{
			System.out.println("File name is wrong. Use file extension .txt");
			showAllOptions();
				
		}
		return null;
		}
		else {
			System.out.println("File name cannot be empty");
			showAllOptions();
		}
		return null; 
	}

	public static void showOptions() {
		
		int s=0;
		System.out.println("1. Press '1' to Load listed Files.");
		//System.out.println("2. Show all files.");
		keyboard= new Scanner(System.in);
		String choice=keyboard.nextLine();
		if(choice==null || choice.contentEquals("")) {
			System.out.println("Wrong choice! Press 1 followed by enter to load files.");
			showOptions();
		}
		try {
			s =  Integer.parseInt(choice);
		}catch(NumberFormatException ne){ne.printStackTrace();}finally {
			if(s==0)
			{
				System.out.println("Some error occured! Press 1 followed by enter to load files.");
				showOptions();
			}
			
		}

		switch (s)
		{
		case 1:
			//Load files existing in the metalist.txt or shows empty if the metalist.text is empty.
			loadFiles();
		//case 2:
			//Shows all files existing in directory.
			//loadAllFiles();
		}
	}

	}
