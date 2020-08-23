package ex4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


public class FileCreator {
    static File createIPFile(){
        File file = new File("src/ex4/Visits.txt");
        Calendar calendar = GregorianCalendar.getInstance();

        Random random = new Random(calendar.get(Calendar.SECOND));
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
            for (int i =0; i<100;i++) {
                int ip1 = random.nextInt(101) + 100;
                int ip2 = random.nextInt(90) + 10;
                int ip3 = random.nextInt(101) + 100;
                int ip4 = random.nextInt(101) + 100;
                int k = random.nextInt(10);
                for (int x = 0; x <k; x++) {
                    String hour = String.valueOf(random.nextInt(24) + 1);
                    String min = String.valueOf(random.nextInt(60) + 1);
                    String sec = String.valueOf(random.nextInt(60) + 1);
                    if (Integer.parseInt(hour) < 10) {
                        hour = "0" + hour;
                    }
                    if (Integer.parseInt(min) < 10) {
                        min = "0" + min;
                    }
                    if (Integer.parseInt(sec) < 10) {
                        sec = "0" + sec;
                    }
                    int j = random.nextInt(7);
                    WeekDay[] day = WeekDay.values();
                    WeekDay realDay = day[j];
                    pw.printf("%d.%d.%d.%d %s:%s:%s %s\n", ip1, ip2, ip3, ip4, hour, min, sec, realDay);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }
}
enum WeekDay{
    MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
}
