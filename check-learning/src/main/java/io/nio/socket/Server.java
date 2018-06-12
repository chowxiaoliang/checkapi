package io.nio.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhouliang
 * @since 2018-06-11 9:59
 **/
public class Server {

    private final static Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private Selector selector ;

    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    public void start() throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(inetSocketAddress);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (!Thread.currentThread().isInterrupted()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                } else if (key.isWritable()) {
//                    write(key);
                }
                keyIterator.remove(); //该事件已经处理，可以丢弃
            }
        }
    }

//    private void write(SelectionKey key) throws IOException, ClosedChannelException {
//        SocketChannel channel = (SocketChannel) key.channel();
//
//        System.out.println("write:"+str);
//
//        sendBuffer.clear();
//        sendBuffer.put(str.getBytes());
//        sendBuffer.flip();
//        channel.write(sendBuffer);
//        channel.register(selector, SelectionKey.OP_READ);
//    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // Clear out our read buffer so it's ready for new data
        this.readBuffer.clear();
//        readBuffer.flip();
        // Attempt to read off the channel
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            key.cancel();
            socketChannel.close();

            return;
        }

        String str = new String(readBuffer.array(), 0, numRead);
        System.out.println(str);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected "+clientChannel.getRemoteAddress());
    }

    public static void main(String[] args) throws IOException {
        System.out.println("server started...");
        new Server().start();
    }

}
