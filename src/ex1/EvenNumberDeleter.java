package ex1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvenNumberDeleter {
    public static void main(String[] args) {
        File file = new File("src/ex1/Numbers.txt");
        deleteEven(file);
    }
    public static void deleteEven(File file){
        List<Integer> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new FileReader(file))){
            while(scanner.hasNext()){
                int i = scanner.nextInt();
                if (i%2!=0) list.add(i);
            }
        }catch (IOException e){
            System.out.println("Ошибка чтения из файла");
        }
        try(PrintWriter writer = new PrintWriter(file)){
            for (int i : list)
                writer.print(i+" ");
        } catch (IOException e){
            System.out.println("Ошибка записи в файл");
        }
    }
}
