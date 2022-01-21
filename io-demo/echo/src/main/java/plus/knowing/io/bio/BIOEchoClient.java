package plus.knowing.io.bio;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class BIOEchoClient {

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 9999);
        Scanner scanner = new Scanner(client.getInputStream());
        scanner.useDelimiter("\n");
        PrintStream out = new PrintStream(client.getOutputStream());
        boolean flag = true;
        while (flag) {
            String msg = input.next().trim();
            out.print(msg);
            String s = scanner.next().trim();
            System.out.println(s);
            if ("exit".equalsIgnoreCase(msg)) {
                flag = false;
            }
        }
        client.close();
        out.close();
    }
}
