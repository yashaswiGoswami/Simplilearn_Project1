package com.simplilearn.project.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileOperator {

	String dir="dir/files";
	String meta="MetaList.txt";
	InputStream in;
	FileWriter fw; 
	PrintWriter pw;
	Scanner sc;
	boolean b=false;
	static int PROGRAM_BEGIN=0;
	public void createFile(String str) throws IOException {
		// TODO Auto-generated method stub
		//System.out.println("Parameter passed to createFile() " +str);
		boolean b=false;
		File f= new File(dir);
		b=f.mkdirs();
		PROGRAM_BEGIN++;
		if(PROGRAM_BEGIN==0) {
			if(b)
			System.out.println("directory created with the name "+dir);
		else 
			System.out.println("dircetory exists with the name "+dir);
			}
		File file= new File(dir+"/"+str+".txt");
		//System.out.println("The file to be deleted "+file.getCanonicalPath());
		try {
			b=file.createNewFile();
			if(b & (str!=meta))
			System.out.println("File created with name " + str);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		finally {
			//final code to execute
		}
		//code for registering the filename to MetaList.txt. 
		//------------------code start----------------------
		try {
			fw=new FileWriter(dir+"/"+meta,true);
			pw=new PrintWriter(fw);
			while(!((str+".txt").equalsIgnoreCase(meta))) {
				pw.println(str);
				System.out.println("File "+str+" registered to the system");
				break;

			}
												
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {

			try {
				if(fw!=null)
				fw.close();
				if(pw!=null)
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//------------------code ends----------------------
		
		if(b)
		System.out.println("file created.");
		else
		System.out.println("File already exists");
	}
	public void readMetaList() throws IOException {
		// TODO Auto-generated method stub
		//System.out.println("ReadMetaList() reached");using file input stream, though reader is faster
		ArrayList<String> filelist=new ArrayList<String>();
		
	
		try {
			in=new FileInputStream(dir+"/"+meta);
			sc=new Scanner(in, "UTF-8");
			//System.out.println(in.available());
			int i=0;
			while(sc.hasNext()) {
				String s=sc.nextLine();
				filelist.add(s);
				//System.out.println(i+". "+s);i++;
			}
			
			//--------------------Sorting the arraylist through converting to  String
			
			int len= filelist.size();
			String list[]= new String[len];
			
			for(int j=0; j<len;j++) {
				list[j]=filelist.get(j);
			}
			String temp;
			// sorting
			for(int k=0;k<len-1; k++) {
				for(int l=k+1; l<len;l++) {
					if(list[k].compareTo(list[l])>0) {
						temp=list[k];
						list[k]=list[l];
						list[l]=temp;
					}
				}
			}
			
			//printing array
			
			for(int a=0;a<list.length;a++) {
				System.out.println(list[a] );
			}

		
			
			
			//------------------------------------------code ends------------------------------------------------------
			//Collections.sort(filelist);
			//for(String a:filelist)
				//System.out.println(a);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException ne) {
			ne.printStackTrace();
		}
	}
	public void deleteFile(String del) {
		// TODO Auto-generated method stub
		/*---------------Code starts--------------*/
		
		try {
			b=false;
			File myObj = new File(dir+"/"+del+".txt");
			System.out.println(myObj.getCanonicalPath());
			if(myObj.exists()) {
	
				//check the file if it exist.
				/*code for case sensitive checking
				 * 
				 * if(file.exists() && file.getCanonicalPath().equals(filePath)){
				 *
				 *---------code ends */
			if(myObj.delete()) {
				b=true;
			 // System.out.println("Deleted the file: " + myObj.getName());
			  editMetaList(myObj);
			} else {
			  System.out.println("Failed to delete the file.");
			}
			}
			else 
			{
				System.out.println("No such file exists. Try different option");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if(b==true) {
				
			}else{
				
			}
				
		}
	   
	    /*---------------Code ends--------------*/
		
		
	}
	private void editMetaList(File myObj) {
		// TODO code to edit the MetaList.txt
		String line = null;
		ArrayList<String> al=new ArrayList<String> ();
		try {
			
			FileReader file = new FileReader(dir+"/"+meta);
		    BufferedReader br = new BufferedReader(file);// Using buffered reader to read the file metalist.txt
		    while ((line = br.readLine()) != null){
		        if (!line.trim().equals(myObj.getName())) {
		        al.add(line);
		        }
		        }
		    String filetobedeleted=myObj.getName();
		    
		    int pos=filetobedeleted.lastIndexOf(".");
		    //WOverwriting on the file.
		    File f=new File(dir+"/"+meta);
		    PrintWriter pw = new PrintWriter(new FileWriter(f)); //using print writer to create the file metalist.tmp
		    for(String filename: al) {
		    	if(pos==-1) {
		    	if(!(filename.equalsIgnoreCase(filetobedeleted)))
		    	{
		    	pw.println(filename);
		    	pw.flush();
		    	}
		    	}
		    	else {
		    		if(!(filename.equalsIgnoreCase(filetobedeleted.substring(0, pos))))
			    	{
			    	pw.println(filename);
			    	pw.flush();
			    	}	
		    	}
		    }

		    
		
		    pw.close();br.close();
		    }

		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }finally {}
		
		  }
	public void loadAllFiles() {
		// TODO Auto-generated method stub
		File f= new File(dir);
		String listoffiles[];
		listoffiles=f.list();
		for(String filename: listoffiles) {
			System.out.println(filename);
		}
		
	}
		
		
	}


