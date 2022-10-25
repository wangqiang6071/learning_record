package com.wayne.learn_netty.BIO;

import com.wayne.learn_netty.utils.ByteBufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang qiang
 * @date 2022/10/21 0021 16:23
 * @Description: bio阻塞模式:服务端
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        //建立缓冲区
        ByteBuffer buffer=ByteBuffer.allocate(10);
        ServerSocketChannel ssc= ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9999));
        List<SocketChannel> channels=new ArrayList<>();
        while (true){
            log.debug("connecting.......");
            SocketChannel accept = ssc.accept();//阻塞线程运行
            log.debug("connecting.......{}",accept);
            channels.add(accept);
            for (SocketChannel channel:channels){
                log.debug("before read.......{}",channel);
                channel.read(buffer);//在没有数据发送时 线程等待运行
                buffer.flip();
                ByteBufferUtils.debugAll(buffer);
                buffer.clear();
                log.debug("after read.......{}",channel);
            }

        }
    }
}
