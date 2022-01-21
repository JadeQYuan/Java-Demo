package plus.knowing.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOEchoClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        boolean flag = true;
        while (flag) {
            byteBuffer.clear();
            String input = scanner.next().trim();
            byteBuffer.put(input.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
            int readCount = socketChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array(), 0, readCount));
            byteBuffer.flip();
            if("byebye".equalsIgnoreCase(input)) {
                flag = false ;
            }
        }
    }
}
