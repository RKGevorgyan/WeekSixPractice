package ex4;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueIP {
    private Set<String> setIp = new HashSet<>();                // to store unique ip
    String popularWeekDay;
    String popularHour;
    String sitePopularHour;
    Map<String,List<String>> mapIPWeekDay=new HashMap<>();      // key: unique Ip -> value: {list(weekDay)},...,{list(weekDay)}
    Map<String,List<String>> mapIpPopularHour =new HashMap<>(); // unique Ip -> {list(time)},...,{time}
    Map<String,String> mapPopularWeekDay = new HashMap<>();     // unique Ip -> popularWeekDay
    Map<String,String> mapPopularHourString = new HashMap<>();  // unique Ip ->  popularHour
    Map<String, Integer> mapVisits;                             // unique Ip ->  number of visits
    Map<String, Integer> mapHour;                               // key hour -> value: number of repetitions



    void processFileData(File file){
        List<String> listIp = new ArrayList<>();
        List<String> listHour = new ArrayList<>();

        Pattern ip = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+"); // to find Ip
        Pattern weekDay = Pattern.compile("\\D{2,}");              // to find Week Day
        Pattern time = Pattern.compile("\\s\\d{2}");               // to find hour

        try(Scanner scanner = new Scanner(new FileReader(file))){

            while(scanner.hasNext()) {
                String s = scanner.nextLine();
                Matcher mIp = ip.matcher(s);
                Matcher mTime = time.matcher(s);
                if (mIp.find()) {
                    setIp.add(mIp.group());  // getting unique IP
                    listIp.add(mIp.group());
                }
                if (mTime.find()){
                    listHour.add(mTime.group());
                }

            }
            // Getting for each unique IP list of days of the week and list of visit hours
            for (String setIp : setIp){

                List<String> listWeekDay = new ArrayList<>(0);
                List<String> listPopularHour = new ArrayList<>(0);

                try(Scanner scanner2 = new Scanner(new FileReader(file))){
                    while(scanner2.hasNext()){
                        String line = scanner2.nextLine();
                        Matcher mWeekDay = weekDay.matcher(line);
                        Matcher mTime = time.matcher(line);
                        if (line.startsWith(setIp)) {
                            if (mWeekDay.find()) {
                                listWeekDay.add(mWeekDay.group());
                            }
                            if (mTime.find()){
                                listPopularHour.add(mTime.group());
                            }
                        }
                    }
                }catch (IOException exp){
                    exp.printStackTrace();
                }
                mapIPWeekDay.put(setIp,listWeekDay);
                mapIpPopularHour.put(setIp,listPopularHour);
            }
            // Getting for each unique Ip most popular day and hour
            Map<String,Integer> mapWeekDay;
            Map<String,Integer> mapPopularHour;
            for (String ipSet : setIp) {
                mapWeekDay =  listToMap(mapIPWeekDay.get(ipSet));
                mapPopularHour = listToMap(mapIpPopularHour.get(ipSet));
                // to get popular week day
                Set<String> setKeysWeekDay = mapWeekDay.keySet();
                for (String y : setKeysWeekDay){
                    if (mapWeekDay.get(y).equals(Collections.max(mapWeekDay.values()))){
                        popularWeekDay=y;
                        mapPopularWeekDay.put(ipSet,popularWeekDay);
                    }
                }
                // to get popular hour
                Set<String> setKeysPopularHour = mapPopularHour.keySet();
                for (String y : setKeysPopularHour){
                    if (mapPopularHour.get(y).equals(Collections.max(mapPopularHour.values()))){
                        popularHour=y;
                        mapPopularHourString.put(ipSet,popularHour);
                    }
                }
            }


            mapVisits = listToMap(listIp);
            mapHour = listToMap(listHour);
            for (String y : mapHour.keySet()){
                if (mapHour.get(y).equals(Collections.max(mapHour.values()))){
                    sitePopularHour=y;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    void printToNewFile(File file){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(file))){
            for(String s : setIp){
                pw.print("IP: "+s+
                        " Number of visits: " + mapVisits.get(s)+
                        " Most popular day: " + mapPopularWeekDay.get(s)+
                        " Most popular time: "+mapPopularHourString.get(s)+"\n");
            }
            pw.print("Most popular visit Hour: " + sitePopularHour);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private <K> Map<K, Integer> listToMap(List<K> ks){
        Map<K,Integer> map = new HashMap<>();
        for(K k : ks){
            Integer count = map.get(k);
            map.put(k,count==null ? 1 : count+1);
        }
        return map;
    }
}