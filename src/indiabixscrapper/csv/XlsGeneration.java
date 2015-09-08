/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indiabixscrapper.csv;

import indiabixscapper.form.IndiabixForm;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author GLB-130
 */
public class XlsGeneration {
    
    public void generateXLS(String fileName, List<IndiabixForm> listIndiabixForm) {

        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet;
        try {
            fileName=fileName.replace("/", "_");
            fileName=fileName.replace(":", "");
            sheet = hwb.createSheet(fileName);
        } catch (Exception e) {
            fileName="data";
            sheet = hwb.createSheet("Details");
            
        }
        HSSFRow rowhead = sheet.createRow((int) 0);
        rowhead.createCell((int) 0).setCellValue("QUESTION_NO");
        rowhead.createCell((int) 1).setCellValue("QUESTION");
        rowhead.createCell((int) 2).setCellValue("OPTION1");
        rowhead.createCell((int) 3).setCellValue("OPTION2");
        rowhead.createCell((int) 4).setCellValue("OPTION3");
        rowhead.createCell((int) 5).setCellValue("OPTION4");
        rowhead.createCell((int) 6).setCellValue("OPTION5");
        rowhead.createCell((int) 7).setCellValue("ANSWER");

        int k = -1;
        for (IndiabixForm listIndiabix: listIndiabixForm) {
            k++;
            HSSFRow row = sheet.createRow((int) k + 2);
            try {
                row.createCell((int) 0).setCellValue(listIndiabix.getQuestionNo()+"");
            } catch (Exception sd) {
            }

            try {
                row.createCell((int) 1).setCellValue(listIndiabix.getQuestion() + "");
            } catch (Exception sd) {
            }

            try {
                row.createCell((int) 2).setCellValue(listIndiabix.getOption1() + "");
            } catch (Exception sd) {
            }

            try {
                row.createCell((int) 3).setCellValue(listIndiabix.getOption2() + "");
            } catch (Exception sd) {
            }

            try {
                row.createCell((int) 4).setCellValue(listIndiabix.getOption3() + "");
            } catch (Exception sd) {
            }

            try {
                row.createCell((int) 5).setCellValue(listIndiabix.getOption4() + "");
            } catch (Exception sd) {
            }

            try {
                row.createCell((int) 6).setCellValue(listIndiabix.getOption5() + "");
            } catch (Exception sd) {
            }

            try {
                row.createCell((int) 7).setCellValue(listIndiabix.getAnswer()+ "");
            } catch (Exception sd) {
            }
        }

        try {

            String filename = "indiaBixData";
            if (filename.contains("nullnull")) {
                return;
            }

            filename = fileName + ".xls";
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");


        } catch (Exception qq) {
            System.out.println(qq);
        }

    }
    
}
