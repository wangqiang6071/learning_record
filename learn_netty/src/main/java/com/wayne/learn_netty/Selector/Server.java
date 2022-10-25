package com.wayne.learn_netty.Selector;

import com.wayne.learn_netty.utils.ByteBufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author wang qiang
 * @date 2022/10/21 0021 16:23
 * @Description: selector服务端
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        //建立selector 管理多个channel
        Selector selector=Selector.open();
        ServerSocketChannel ssc= ServerSocketChannel.open();
        ssc.configureBlocking(false);//切换非阻塞模式：影响的是ssc.accept()方法

        //建立selector与channel的关联（把当前channel注册到selector上）
        //selectorKey:有事件发生时 通过它可以知道当前事件和哪个channel发生的事件
        //accept 有连接请求时 触发
        //connect 客户端 连接建立后触发
        //read 可读事件
        //write 可写事件
        SelectionKey selectorKey = ssc.register(selector, 0, null);
        //关注的事件:连接事件
        selectorKey.interestOps(SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(9999));
        while (true){
            //阻塞方法 有事件发生就会往下运行 没有事件发生就会阻塞在这  方式空运行
            //如果 selector事件不进行处理 在while循环中会重复加入到iterator，一直循环下去(要么处理 要么取消) 直至事件被处理
            selector.select();
            //发生事件时
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                log.debug("SelectionKey.......{}"+next);
//                1 处理selector事件
//                ServerSocketChannel channel = (ServerSocketChannel)next.channel();
//                SocketChannel accept = channel.accept();
//                log.debug("SocketChannel.......{}"+accept);
                //2 处理selector事件
                next.cancel();
            }
        }
    }
}
