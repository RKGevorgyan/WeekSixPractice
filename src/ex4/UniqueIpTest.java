package ex4;

import java.io.File;

public class UniqueIpTest {
    public static void main(String[] args) {
        File from = FileCreator.createIPFile();
        File to = new File("src/ex4/Statistics.txt");
        UniqueIP ip = new UniqueIP();
        ip.processFileData(from);
        ip.printToNewFile(to);
    }
}
