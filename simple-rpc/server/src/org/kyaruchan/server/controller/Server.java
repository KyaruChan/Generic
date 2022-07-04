package org.kyaruchan.server.controller;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/* 接受Client的请求，调用指定服务并反馈结果 */
public abstract class Server {
    private Selector selector;
    private final ByteBuffer K4_BUFFER = ByteBuffer.allocate(1024 * 4);

    public Server(int port){
        try{
            selector = Selector.open();
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress("127.0.0.0", port));
            server.register(selector, SelectionKey.OP_ACCEPT); // 将通道注册到选择器中
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /* 开启监听服务 */
    public void service(){
        while(true){
            try {
                /* 当有通道就绪时，获取就绪通道所在的Key */
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> keyIter = keySet.iterator();

                /* 遍历获取的key，按通道的就绪状态不同分别处理 */
                while(keyIter.hasNext()){
                    SelectionKey key = keyIter.next();
                    keyIter.remove(); //获取后移除key，避免重复通知

                    /* 对状态为ACCEPT的通道进行处理 */
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept(); //获取通道中缓存的socket

                        /* 将socket通道注册到selector中，等待其就绪后进行读取 */
                        client.register(selector, SelectionKey.OP_READ);

                    /* 对状态为READ的通道进行处理 */
                    }else if(key.isReadable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        client.read(K4_BUFFER);
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
