package plus.knowing.io.bio;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOEchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        boolean flag = true;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (flag) {
            Socket client = serverSocket.accept();
            executorService.submit(new Handler(client));
        }
    }

    static class Handler implements Runnable {

        final Socket client;
        Scanner scanner;
        PrintStream out;
        boolean flag = true;

        public Handler(Socket client) throws IOException {
            this.client = client;
            scanner = new Scanner(client.getInputStream());
//            scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            out = new PrintStream(client.getOutputStream());
        }


        @Override
        public void run() {
            while (flag) {
                if (scanner.hasNext()) {
                    String s = scanner.next().trim();
                    System.out.println("server 接收 " + s);
                    if ("exit".equalsIgnoreCase(s)) {
                        flag = false;
                        out.println(s);
                    } else {
                        out.println("[ECHO] " + s);
                    }
                }
            }
            try {
                client.close();
                scanner.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
