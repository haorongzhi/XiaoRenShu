package com.hrz.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Administrator
 * @since 2019-12-29 18:34
 */
public class CsvUtil {

     try {
        BufferedReader reader = null;//换成你的文件名   
        try {
            reader = new BufferedReader(new FileReader("*.csv"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String line = null;
        while(true){
            try {
                if (!((line=reader.readLine())!=null)) break;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   

            String last = item[item.length-1];//这就是你要的数据了   
            //int value = Integer.parseInt(last);//如果是数值，可以转化为数值   
            System.out.println(last);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

}
