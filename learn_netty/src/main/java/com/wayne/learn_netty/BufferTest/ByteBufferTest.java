package com.wayne.learn_netty.BufferTest;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wang qiang
 * @date 2022/10/20 0020 9:41
 * @Description: TODO
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        try {
            FileChannel channel=new FileInputStream(new File("E:\\projectHome\\learning_record\\learn_netty\\src\\main\\resources\\files\\data.txt")).getChannel();
            //buffer初始化的时 是处于写入模式
            ByteBuffer buffer=ByteBuffer.allocate(10);
            //从channel读取数据，往buffer写入,如果channel中的没有数据了 就会返回-1
            while (true){
                int read = channel.read(buffer);
                System.out.println("实际读取到的字节数===》"+read);
                if(read==-1){
                    break;
                }
                //获取buffer中的数据==buffer切换读取模式
                buffer.flip();
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    System.out.println("实际字节值===》"+(char)b);
                }
                //buffer切换写入模式
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
