/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indiabixscrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Ashwini Mendon
 */
public class IndiabixScrapper {

    public final static void main(String[] args) throws Exception {
        System.out.println("----------------------------------------");
        BufferedReader reader = new BufferedReader(new FileReader("urls.txt"));
        String line = reader.readLine();
        List<String> lines = new ArrayList<String>();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
          }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (String line1 : lines) {
            System.out.println(""+line1);
            try {
                Runnable worker = new ScrappEachUrl(line1);
                executor.execute(worker);
                } catch (Exception e) {
                        System.out.println(e);
                }
            
        }
    }

    
}

