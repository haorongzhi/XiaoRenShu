package com.hrz.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * @author Administrator
 * @since 2020-01-01 13:48
 */
public class DealCSV {

    public static void main(String[] args) {

        // 第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File("E:\\code\\xiaorenshu\\tools\\uservodtest.csv");

        List<CSVRecord> records = CsvUtil.read(csv);

        Map<String, Map<String,List<User>> > map = new HashMap<>();
        for(int i=0;i<records.size();i++){
            //能拿到每一个单元格的内容了
            CSVRecord record = records.get(i);
//                System.out.println(record.toString());
            //取到每一行的人的数据
            User user = dealRecord(record);
            //把同一个人的数据拿到
            String id = user.getId();
            if(map.containsKey(id)){

                Map<String,List<User>> oneMonthUserMap = map.get(id);
                String key = user.getMonthKey();
                if(oneMonthUserMap.containsKey(key)){
                    oneMonthUserMap.get(key).add(user);
                }else{
                    List<User> oneMonthList = new ArrayList<>();
                    oneMonthList.add(user);
                    oneMonthUserMap.put(key,oneMonthList);
                }
                map.put(id,oneMonthUserMap);

            }else{
                Map<String,List<User>> oneMonthUserMap = new HashMap<>();
                List<User> userList = new ArrayList<>();
                userList.add(user);
                oneMonthUserMap.put(user.getMonthKey(),userList);

                map.put(id,oneMonthUserMap);
            }
        }


        File rcsv = new File("E:/code/xiaorenshu/tools/writers.csv"); // CSV数据文件

        List<String> lines = new ArrayList<>();
        StringBuffer  head = new StringBuffer("省份").append(",").append("id").append(",").append("日期").append(",").append("渠道").append(",");
        for(int m = 1;m<8;m++){

            String h ="";
            for(int n=0;n<24;n++){

                if(n == 23){
                    h = h + n + "-" + (n+1);

                }else{
                    h = h + n + "-" + (n+1) + ",";

                }
            }
            System.out.println(h);
            if(m==7){
                head.append(h);
            }else{
                head.append(h).append(",");
            }

        }

        lines.add(head.toString());

        //map里放的是同一个人的数据
        Iterator<Map.Entry<String,Map<String,List<User>> >> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Map<String,List<User>>> entry = it.next();
            String id = entry.getKey() ;
            Map<String,List<User>> usersMap  = entry.getValue();

            Iterator<Map.Entry<String,List<User> >> it1 = usersMap.entrySet().iterator();
            while (it1.hasNext()) {
                Map.Entry<String, List<User>> entry1 = it1.next();
                List<User> users = entry1.getValue();


                //同一个人的数据
                if(!CollectionUtils.isEmpty(users)){

                    User aUser =users.get(0);
                    //省份，id，
                    StringBuffer preLine = new StringBuffer("");
                    preLine.append(aUser.getProvince()).append(",").append(aUser.getId()).append(",");
                    String type = aUser.getType();
                    LocalDate mydate = aUser.getDate();
                    StringBuffer oneWeekHours = new StringBuffer("").append(preLine)
                            .append(DateUtil.firstMonday2Sunday(mydate)).append(",").append(type).append(",");
                    StringBuffer secWeekHours = new StringBuffer("").append(preLine)
                            .append(DateUtil.secondMonday2Sunday(mydate)).append(",").append(type).append(",");
                    StringBuffer thrWeekHours = new StringBuffer("").append(preLine)
                            .append(DateUtil.thirdMonday2Sunday(mydate)).append(",").append(type).append(",");
                    StringBuffer fouWeekHours = new StringBuffer("").append(preLine)
                            .append(DateUtil.fourMonday2Sunday(mydate)).append(",").append(type).append(",");

                    boolean hasOne = false;
                    boolean hasTwo = false;
                    boolean hasThr = false;
                    boolean hasFor = false;

                    for(User user: users){
                        LocalDate date = user.getDate();
                        //第一周
                        //周一-周天,渠道，
                        int whichDay = DateUtil.weekOfMonth(date);

                        switch (whichDay){
                            case 1 :
                                oneWeekHours = oneWeekHours.append(user.getHours()).append(",");
                                hasOne = true;
                                break;
                            case 2:
                                secWeekHours = secWeekHours
                                        .append(user.getHours()).append(",");
                                hasTwo = true;
                                break;
                            case 3:
                                thrWeekHours = thrWeekHours
                                        .append(user.getHours()).append(",");
                                hasThr = true;
                                break;
                            case 4:
                                fouWeekHours = fouWeekHours
                                        .append(user.getHours()).append(",");
                                hasFor = true;
                                break;
                            default:;
                        }

                    }
                    //一个人几周的数据
                    if(hasOne){
                        lines.add(oneWeekHours.toString().substring(0,oneWeekHours.length()-1));
                    }else if(hasTwo){
                        lines.add(secWeekHours.toString().substring(secWeekHours.length()-1));
                    }else if(hasThr){
                        lines.add(thrWeekHours.toString().substring(0,thrWeekHours.length()-1));
                    }else if(hasFor){
                        lines.add(fouWeekHours.toString().substring(0,fouWeekHours.length()-1));
                    }

                }
            }

        }

        CsvUtil.write(rcsv,lines);
    }

    private static User dealRecord(CSVRecord record) {
        String provindce = record.get(0);
        String id = record.get(1);
        String date = record.get(2).replaceAll("\n","");
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 =   LocalDate.parse(date,formatter);


        String type = record.get(3);
        StringBuffer buffer = new StringBuffer("");

        for(int i = 4;i<28;i++){
            String hour = StringUtils.isEmpty(record.get(i)) ? "0" : record.get(i);
            buffer.append(hour).append(",");
        }

        String hours = buffer.toString().substring(0,buffer.length()-1);

        User user = new User(provindce,id,date1,type,hours);
        user.setMonthKey();

        System.out.println(user);



        return user;


    }

}
