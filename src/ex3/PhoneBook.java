package ex3;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    static List<Long> getPhoneNumber(String lastName) {

        List<Long> list = new ArrayList<>(); // To save phone numbers

        Pattern p = Pattern.compile("\\d{11}"); // To find phone number

        try (Scanner scanner = new Scanner(new FileReader("src/ex3/PhoneBook.txt"))) {
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                if (s.startsWith(lastName)) {    // Finding lines that starts with Last Name
                    Matcher m = p.matcher(s);
                    if (m.find())
                        list.add(Long.parseLong(m.group()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    static int NumberOfPhones(String lastName, int year){
        int count=0; // Number of phones
        Pattern p = Pattern.compile("\\d{4}"); // To find year

        try (Scanner scanner = new Scanner(new FileReader("src/ex3/PhoneBook.txt"))) {
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                if (s.startsWith(lastName)){
                    Matcher m = p.matcher(s);
                    if (m.find()){
                        if (Integer.parseInt(m.group()) >= year)
                            count++;
                    }
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return count;
    }
}
