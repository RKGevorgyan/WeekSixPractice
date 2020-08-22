package ex2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortUserInput {
    public static void main(String[] args) {
        File userFile = new File("src/ex2/UserFile.txt");
        File sortedUserFile = new File("src/ex2/SortedUserFile.txt");
        sortUserInput(userFile,sortedUserFile);

    }

    public static void sortUserInput(File from, File to){
        List<String> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new FileReader(from));
            PrintWriter pw = new PrintWriter(to))
        {
            while(scanner.hasNext()){
                String s = scanner.next();
                list.add(s);
            }
            Collections.sort(list);
            for (String s : list)
                pw.print(s+" ");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
