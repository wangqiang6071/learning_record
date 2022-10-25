package com.wayne.learn_netty.BufferTest;

import com.wayne.learn_netty.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

/**
 * @author wang qiang
 * @date 2022/10/20 0020 14:45
 * @Description: TODO
 */
public class ByteBufferReadTest {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a','b','c','d'});
        //切换读取
        buffer.flip();
        //把数据重新读取到一个新的byte数组中
        buffer.get(new byte[4]);
        ByteBufferUtils.debugAll(buffer);
        //读完后再重新读取 使用rewind()方法
        buffer.rewind();
        ByteBufferUtils.debugAll(buffer);

        //mark:标记位置坐标
        //reset：重置到标记的位置坐标
        System.out.println((char)buffer.get());//a
        System.out.println((char)buffer.get());//b
        buffer.mark();
        System.out.println((char)buffer.get());//c
        System.out.println((char)buffer.get());//d
        buffer.reset();
        System.out.println((char)buffer.get());//c
        System.out.println((char)buffer.get());//d
    }

}
