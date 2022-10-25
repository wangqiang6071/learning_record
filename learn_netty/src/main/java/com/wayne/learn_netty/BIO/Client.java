package com.wayne.learn_netty.BIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author wang qiang
 * @date 2022/10/21 0021 16:33
 * @Description: bio阻塞模式:客户端
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel channel=SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost",9999));
        log.debug("waiting.....");
        //使用断点的评估工具 发送表达式 向服务端发送数据
        //channel.write(Charset.defaultCharset().encode("hello!"))
    }
}
