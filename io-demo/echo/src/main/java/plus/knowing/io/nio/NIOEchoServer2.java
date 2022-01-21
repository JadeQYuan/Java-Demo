package plus.knowing.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOEchoServer2 {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9999));
        for (;;) {
            SocketChannel socketChannel = channel.accept();
            new Thread(new Handler(socketChannel)).start();
        }
    }

    static class Handler implements Runnable {

        final SocketChannel socketChannel;
        boolean flag = true;

        public Handler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(50);
            try {
                while (flag) {
                    byteBuffer.clear();
                    int readCount = socketChannel.read(byteBuffer);
                    String msg = new String(byteBuffer.array(), 0, readCount).trim();
                    System.out.println(msg);
                    byteBuffer.clear();
                    byteBuffer.put(("[ECHO] " + msg + "\n").getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                }
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
