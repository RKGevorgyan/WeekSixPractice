package ex3;

import java.util.ArrayList;
import java.util.List;

public class Info {
    public static void main(String[] args) {
        String lastName = "Valeev";
        int year = 2013;

        List<Long> list = new ArrayList<>(PhoneBook.getPhoneNumber(lastName));
        System.out.print("Phone numbers: ");
        for (long l : list){
            System.out.print(l+" ");
        }
        System.out.println();
        int i = PhoneBook.NumberOfPhones(lastName,year);
        System.out.println("Number of Phones since "+year+" : "+i);

    }
}
