package plus.knowing.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOEchoServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9999));
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        for (; ; )  {
            if (selector.select() == 0) {
                continue;
            }
            for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {
                SelectionKey selectedKey = iterator.next();
                if (selectedKey.isAcceptable()) {
                    System.out.println("==== accept ====");
                    SocketChannel clientChannel = channel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                } else if (selectedKey.isReadable()) {
                    System.out.println("==== read ====");
                    SocketChannel clientChannel = (SocketChannel) selectedKey.channel();
                    byteBuffer.clear();
                    int readCount = clientChannel.read(byteBuffer);
                    String msg = new String(byteBuffer.array(), 0, readCount).trim();
                    System.out.println(msg);
                    byteBuffer.clear();
                    byteBuffer.put(("[ECHO] " + msg + "\n").getBytes());
                    byteBuffer.flip();
                    clientChannel.write(byteBuffer);
                }
                iterator.remove();
            }
        }
    }
}
