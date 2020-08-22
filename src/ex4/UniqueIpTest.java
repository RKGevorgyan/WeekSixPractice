package ex4;

import java.io.File;

public class UniqueIpTest {
    public static void main(String[] args) {
        File from = new File("src/ex4/visit.txt");
        File to = new File("src/ex4/uniqueIP.txt");
        UniqueIP ip = new UniqueIP();
        ip.processFileData(from);
        ip.printToNewFile(to);
    }
}
