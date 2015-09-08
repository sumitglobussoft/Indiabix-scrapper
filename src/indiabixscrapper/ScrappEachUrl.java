/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indiabixscrapper;

import indiabixscapper.form.IndiabixForm;
import indiabixscrapper.csv.XlsGeneration;
import indiabixscrapper.utility.FetchSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ashwini Mendon
 */
public class ScrappEachUrl implements Runnable {

    FetchSource objFetchSource = new FetchSource();
    public String url;

    ScrappEachUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        List<IndiabixForm> listIndiabixForm = new ArrayList<>();
        dataPagination(url, listIndiabixForm, url);

    }

    public void dataPagination(String urlPage, List<IndiabixForm> listIndiabixForm, String mainURL) {

        System.out.println("urlPage" + urlPage);
        String questionNo="NA";
        String question="NA";
        String option1="NA";
        String option2="NA";
        String option3="NA";
        String option4="NA";
        String option5="NA";
        String answer="NA";
        String explanation;
        String answerKey;
        int ansCount;
        char answers[]=null;
        String urlResponse = null;
        Document objDocument = null;
        try {
            urlResponse = objFetchSource.fetchPageSourceWithProxy(urlPage);
            objDocument = Jsoup.parse(urlResponse);
           // System.out.println(""+objDocument);
            System.out.println("url::::" + url);
        } catch (IOException ex) {
            Logger.getLogger(ScrappEachUrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ScrappEachUrl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            // </div><input id="hdnAjaxImageCacheKey" type="hidden" value="cbadebeabcabdcdaecbcbdbcbeeddbbcadabceaa"> </td> 
            
            answerKey = objDocument.select("input[id=hdnAjaxImageCacheKey]").attr("value");
            System.out.println("answerKey:::"+answerKey);
             
            Elements ele = objDocument.select("div[class=bix-div-container]");
            ansCount=ele.size();
            try {
                if(answerKey.length()>0)
                {
                    System.out.println("ansCount:"+ansCount);
                    answerKey=answerKey.substring(18, 18+ansCount);
                    answers=answerKey.toCharArray();
                }
                System.out.println("answerKey:::::"+answerKey);
            } catch (Exception e) {
              //  e.printStackTrace();
            }
            
            try {
                for (Element ele1 : ele) {
                    ansCount--;
//                System.out.println(ele1);
                    
                    IndiabixForm objIndiabixForm = new IndiabixForm();
                    
                    questionNo = ele1.select("tr td").first().text();
                    objIndiabixForm.setQuestionNo(questionNo);
                    System.out.println("=============>" + questionNo);
                    
                    question = ele1.select("tr td p").first().text();
                    objIndiabixForm.setQuestion(question);
                    System.out.println("question::" + question);
                    
                   // Elements optionNo = ele1.select("tr td table[class=bix-tbl-options] tbody td[width=1%]");
                    Elements options = ele1.select("tr td table[class=bix-tbl-options] tbody td[width=49%]");
                    try {
                        option1 =options.get(0).text();
                        objIndiabixForm.setOption1(option1);
                        System.out.println("option1" + option1);
                        
                        option2 =options.get(1).text();
                        objIndiabixForm.setOption2(option2);
                        System.out.println("option2" + option2);
                        
                        option3 = options.get(2).text();
                        objIndiabixForm.setOption3(option3);
                        System.out.println("option3" + option3);
                        
                        option4 = options.get(3).text();
                        objIndiabixForm.setOption4(option4);
                        System.out.println("option4" + option4);
                        
                        option5 = options.get(4).text();
                        objIndiabixForm.setOption5(option5);
                        System.out.println("option5" + option5);
                        
                    } catch (Exception e) {
                    }
                    // answer = ele1.select("tr td div[class=bix-div-answer] div[class=div-spacer] p b[class=jq-hdnakqb]").text();
                    try {
                        answer = new String(answers[ansCount] + "");
                        System.out.println("answer::" + answer);
                        objIndiabixForm.setAnswer(answer);
                    } catch (Exception e) {
                    }

//                 explanation=ele1.select("div[class=div-spacer] p").text();
//                 System.out.println("explanation:::"+explanation);
                    
                    listIndiabixForm.add(objIndiabixForm);
                    
                }
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
        try {
            Elements ele = objDocument.select("div p[class=ib-pager]");
            if(ele.isEmpty())
            {
                System.out.println("generate CSV");
              
              XlsGeneration objxsl=new XlsGeneration();
              objxsl.generateXLS(mainURL, listIndiabixForm);
            }
            
            else {                
                String urlnextpage;
                String checkText = ele.select("a").last().text();
                
                System.out.println("checkText:::" + checkText);
                System.out.println("checkText.contains(\"Next\")::::::" + checkText.contains("Next"));
                if (checkText.contains("Next")) {
                    
                    urlnextpage = ele.select("a").last().attr("href");
                    urlnextpage = "http://www.indiabix.com" + urlnextpage;
                    System.out.println("urlp::" + urlnextpage);
                    dataPagination(urlnextpage, listIndiabixForm, mainURL);
                    
                } else {
                    System.out.println("generate CSV");
                    
                    XlsGeneration objxsl = new XlsGeneration();
                    objxsl.generateXLS(mainURL, listIndiabixForm);
//              CsvFileWriter objcsv=new CsvFileWriter();
//              objcsv.writeCsvFile(mainURL, listIndiabixForm);
                }
            } 

        } catch (Exception e) {
         //   e.printStackTrace();
        }

    }

}
