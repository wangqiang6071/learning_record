package com.wayne.learn_netty.NIO;

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
 * @Description: nio阻塞模式:服务端
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        //建立缓冲区
        ByteBuffer buffer=ByteBuffer.allocate(10);
        ServerSocketChannel ssc= ServerSocketChannel.open();
        ssc.configureBlocking(false);//切换非阻塞模式：影响的是ssc.accept()方法
        ssc.bind(new InetSocketAddress(9999));
        List<SocketChannel> channelList=new ArrayList<>();
        while (true){
            log.debug("connecting.......");
            SocketChannel channels = ssc.accept();//非阻塞线程运行：在没有客户端连接返回的是null
            if(channels!=null){
                log.debug("connecting.......{}",channels);
                channels.configureBlocking(false);//设置channel为非阻塞线程运行：
                channelList.add(channels);
            }
            for (SocketChannel channel:channelList){
                log.debug("before read.......{}",channel);
               int size= channel.read(buffer);//非阻塞线程运行：在没有数据发送时 read方法 返回0 一直会空运行
               if(size>0){
                   buffer.flip();
                   ByteBufferUtils.debugAll(buffer);
                   buffer.clear();
                   log.debug("after read.......{}",channel);
               }
            }

        }
    }
}
