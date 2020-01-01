package com.hrz.util;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * @author Administrator
 * @since 2019-12-29 18:34
 */
public class CsvUtil {

   public static  List<CSVRecord> read(File file){

       try {
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
//            FileReader filereader = new FileReader(csv);
//            BufferedReader bufferedReader = new BufferedReader(filereader);
           bufferedReader.readLine();// try-catch omitted
           CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',');
           CSVParser parser = new CSVParser(bufferedReader, format);
           List<CSVRecord> records = parser.getRecords();//跳过第一行所有行的记录
           return records;

       } catch (IOException e) {
           System.out.print("please check your upload");
           e.printStackTrace();
           return null;
       }

   }

   public static void write(File csv,List<String> lines){
       BufferedWriter bw = null;
       try{
           bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csv),"UTF-8"));
           if(!CollectionUtils.isEmpty(lines)){
               for(String line : lines){
                   bw.write(line);
                   bw.newLine();
               }
           }
       }catch(Exception e){
           e.printStackTrace();
       }finally {
           try{
               if(null != bw){
                   bw.close();
               }
           }catch (Exception e){
               e.printStackTrace();
           }

       }

   }

}
