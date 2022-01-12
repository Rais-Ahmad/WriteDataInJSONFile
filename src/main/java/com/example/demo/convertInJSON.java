package com.example.demo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader; 

public class convertInJSON {
	
	private static final String crunchifyFileLocation = "People/ListOfPeopleInJSON-Format.txt";
	private static final Gson gson = new Gson();
	  public static void main(String[] a)  
      {  	  
		  		  
		   try {
			      File myObj = new File("data.txt");
			      Scanner myReader = new Scanner(myObj);
			      int j=1;
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        People people = new People(); 

			          people.setId(j);
			  		  people.setName(String.format("%S", data));
			            j++;          

			        crunchifyWriteToFile(gson.toJson(people));
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		  		  
		  crunchifyReadFromFile();

      }  
	  
	  
	  
	  
	  private static void crunchifyWriteToFile(String myData) {
			
			File crunchifyFile = new File(crunchifyFileLocation);
			
			if (!crunchifyFile.exists()) {
			
				try {
					File directory = new File(crunchifyFile.getParent());
					if (!directory.exists()) {
						directory.mkdirs();
					}
					
					
					crunchifyFile.createNewFile();
				} catch (IOException e) {
					System.out.print("Exception Occurred: " + e.toString());
				}
			}
			
			try {
				
				FileWriter crunchifyWriter;
				crunchifyWriter = new FileWriter(crunchifyFile.getAbsoluteFile(), true);
				
				BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
				bufferWriter.write(myData.toString());
				bufferWriter.close();
				
				System.out.print("Company data saved at file location: " + crunchifyFileLocation + " Data: " + myData + "\n");
			} catch (IOException e) {
				
				System.out.print("Hmm.. Got an error while saving Company data to file " + e.toString());
			}
		}
	  
		public static void crunchifyReadFromFile() {
			
			File crunchifyFile = new File(crunchifyFileLocation);
			
			if (!crunchifyFile.exists())
				System.out.print("File doesn't exist");
			
			InputStreamReader isReader;
			try {
				isReader = new InputStreamReader(new FileInputStream(crunchifyFile), StandardCharsets.UTF_8);
				
				JsonReader myReader = new JsonReader(isReader);
				People company = gson.fromJson(myReader, People.class);
				
				System.out.print("Company Name: " + company.getName());
				int id = company.getId();
				System.out.print("# of Employees: " + Integer.toString(id));
				
			} catch (Exception e) {
				System.out.print("error load cache from file " + e.toString());
			}
			
			System.out.print("\nCompany Data loaded successfully from file " + crunchifyFileLocation);
			
		}
		 
}
