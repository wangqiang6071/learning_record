package com.wayne.learn_netty.BufferTest;

import java.nio.ByteBuffer;

/**
 * @author wang qiang
 * @date 2022/10/20 0020 14:29
 * @Description: TODO
 */
public class AllocateTest {
    public static void main(String[] args) {
        /**
         * java 堆内存 读写效率低 受到GC垃圾回收的影响
         * class java.nio.HeapByteBuffer
         */
        System.out.println(ByteBuffer.allocate(10).getClass());
        /**
         *  系统 直接内存  读写效率高 减少一次拷贝 不会受到GC垃圾回收的影响 分配内存的效率较低 使用不当会发生内存泄露
         *  class java.nio.DirectByteBuffer
         */
        System.out.println(ByteBuffer.allocateDirect(10).getClass());

    }
}
