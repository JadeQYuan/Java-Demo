package plus.knowing.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOEchoServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress(9999));
        CountDownLatch countDownLatch = new CountDownLatch(5);
        asynchronousServerSocketChannel.accept(null, new AcceptHandler(asynchronousServerSocketChannel));
        countDownLatch.await();
    }

    private static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
        AsynchronousServerSocketChannel asynchronousServerSocketChannel;
        ByteBuffer byteBuffer = ByteBuffer.allocate(50);
        public AcceptHandler(AsynchronousServerSocketChannel asynchronousServerSocketChannel) {
            this.asynchronousServerSocketChannel = asynchronousServerSocketChannel;
        }

        @Override
        public void completed(AsynchronousSocketChannel socketChannel, Object object) {
            System.out.println(" ==== accept ==== ");
            asynchronousServerSocketChannel.accept(null, this);
            socketChannel.read(byteBuffer, byteBuffer, new ReadHandler(socketChannel));
        }

        @Override
        public void failed(Throwable exc, Object object) {
            throw new RuntimeException(exc);
        }
    }

    private static class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
        AsynchronousSocketChannel socketChannel;

        public ReadHandler(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void completed(Integer readCount, ByteBuffer byteBuffer) {
            System.out.println(" ==== read ==== ");
            String msg = new String(byteBuffer.array(), 0, readCount);
            System.out.println(msg);

            byteBuffer.clear();
            byteBuffer.put(("[ECHO] " + msg + "\n").getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println(" ==== write ==== ");
                    byteBuffer.clear();
                    socketChannel.read(byteBuffer, byteBuffer, ReadHandler.this);
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
