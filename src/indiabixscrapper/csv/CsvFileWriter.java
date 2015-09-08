package indiabixscrapper.csv;

import indiabixscapper.form.IndiabixForm;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GLB-130
 */
public class CsvFileWriter {
    
//Delimiter used in CSV file

	    private static final String COMMA_DELIMITER = ",";

	    private static final String NEW_LINE_SEPARATOR = "\n";

	    //CSV file header

	    private static final String FILE_HEADER = "QUESTION_NO,QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,OPTION5,ANSWER";

	 

	    public void writeCsvFile(String fileName, List<IndiabixForm> listIndiabixForm) {

	          FileWriter fileWriter = null;
           
              try {

	            fileWriter = new FileWriter("ashthegreat.csv");
               
               //Write the CSV file header

	            fileWriter.append(FILE_HEADER.toString());

                //Add a new line separator after the header

	            fileWriter.append(NEW_LINE_SEPARATOR);

	             

	            //Write a new student object list to the CSV file

	            for (IndiabixForm listIndiabix : listIndiabixForm) {

	                fileWriter.append(listIndiabix.getQuestionNo()+"");

	                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(listIndiabix.getQuestion()+"");

	                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(listIndiabix.getOption1()+"");

	                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(listIndiabix.getOption2()+"");

	                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(listIndiabix.getOption3()+"");

	                fileWriter.append(COMMA_DELIMITER);
                    
                    fileWriter.append(listIndiabix.getOption4()+"");

	                fileWriter.append(COMMA_DELIMITER);
                    
                    fileWriter.append(listIndiabix.getOption5()+"");

	                fileWriter.append(COMMA_DELIMITER);
                    
                    fileWriter.append(listIndiabix.getAnswer()+"");

	                fileWriter.append(NEW_LINE_SEPARATOR);

	            }

	 

	             

	             

	            System.out.println("CSV file was created successfully !!!");

	             

	        } catch (Exception e) {

	            System.out.println("Error in CsvFileWriter !!!");

	            e.printStackTrace();

	        } finally {

	             

	            try {

	                fileWriter.flush();

	                fileWriter.close();

	            } catch (IOException e) {

	                System.out.println("Error while flushing/closing fileWriter !!!");

	                e.printStackTrace();

	            }

	             

	        }

	    }

	}
