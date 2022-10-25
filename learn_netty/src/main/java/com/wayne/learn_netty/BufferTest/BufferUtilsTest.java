package com.wayne.learn_netty.BufferTest;

import com.wayne.learn_netty.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

/**
 * @author wang qiang
 * @date 2022/10/20 0020 11:07
 * @Description: TODO
 */
public class BufferUtilsTest {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(10);
        //写入(16进制)
        buffer.put((byte)0x61);// a
        ByteBufferUtils.debugAll(buffer);
        buffer.put(new byte[]{0x62,0x63,0x64});//b c d
        ByteBufferUtils.debugAll(buffer);
        //从写入模式转为读取模式(10进制)
        buffer.flip();
        System.out.println("===>"+buffer.get());
        ByteBufferUtils.debugAll(buffer);

        //从读取转为写入模式
        buffer.compact();
        ByteBufferUtils.debugAll(buffer);
    }
}
