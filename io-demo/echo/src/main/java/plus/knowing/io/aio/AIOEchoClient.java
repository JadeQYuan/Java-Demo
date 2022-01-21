package plus.knowing.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class AIOEchoClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        asynchronousSocketChannel.connect(new InetSocketAddress("127.0.0.1", 9999), null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {
                Scanner scanner = new Scanner(System.in);
                ByteBuffer buffer = ByteBuffer.allocate(50);
                while (scanner.hasNext()) {
                    String next = scanner.next();
                    buffer.clear();
                    buffer.put(next.getBytes());
                    buffer.flip();
                    asynchronousSocketChannel.write(buffer, buffer, new WriteHandler(asynchronousSocketChannel));
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });
        countDownLatch.await();
    }

    private static class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {
        AsynchronousSocketChannel socketChannel;

        public WriteHandler(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void completed(Integer result, ByteBuffer byteBuffer) {
            System.out.println(" ==== write ==== ");
            byteBuffer.clear();

            socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer readCount, ByteBuffer buffer) {
                    System.out.println(" ==== read ==== ");
                    String msg = new String(buffer.array(), 0, readCount);
                    System.out.println(msg);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    throw new RuntimeException(exc);
                }
            });
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            throw new RuntimeException(exc);
        }
    }
}
