package com.wayne.learn_netty.BufferTest;

import com.wayne.learn_netty.utils.ByteBufferUtils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author wang qiang
 * @date 2022/10/20 0020 15:14
 * @Description: ByteBuffer与String相互转换
 */
public class ByteBufferStringTest {
    public static void main(String[] args) {

        //1 字符串
        ByteBuffer buffer=ByteBuffer.allocate(10);
        buffer.put("hello".getBytes(StandardCharsets.UTF_8));
        ByteBufferUtils.debugAll(buffer);

        //2 Charset:自动从初始化的写入模式转化为读取模式 不用手动调用flip方法
        ByteBuffer buffer2=StandardCharsets.UTF_8.encode("hello");
        ByteBufferUtils.debugAll(buffer2);
        //3 wrap
        ByteBuffer buffer3=ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
        ByteBufferUtils.debugAll(buffer3);

        //对应的也有三种 ByteBuffer转字符串
        buffer.flip();
        String str1=StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(str1);

        String str2=StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str2);
    }
}
