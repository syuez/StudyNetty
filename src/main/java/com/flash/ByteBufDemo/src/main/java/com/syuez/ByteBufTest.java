package com.syuez;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("allocate ByteBuf(9, 100)", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍可写。
        buffer.writeBytes(new byte[]{1,2,3,4});
        print("writeBytes(1,2,3,4)", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写，
        // 写完 int 类型之后，写指针增加 4 。
        buffer.writeInt(12); // java 中 int 占 4个字节
        print("writeInt(12)", buffer);

        // write 改变写指针，写完之后写指针等于 capacity 的时候，buffer 不可写。
        buffer.writeBytes(new byte[]{5});
        print("writeBytes(5)", buffer);

        // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变。
        buffer.writeBytes(new byte[]{6});
        print("writeBytes(6)", buffer);

        // get 方法不改变读指针
        System.out.println("getByte(3) return: " + buffer.getByte(3));
        System.out.println("getShort(3) return: " + buffer.getShort(3));
        System.out.println("getInt(3) return: " + buffer.getInt(3));
        print("getByte()", buffer);

        // set 方法不改变读写指针
        buffer.setByte(buffer.readableBytes() + 1, 0);
        print("setByte()", buffer);

        // read 方法改变读指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        print("readBytes(" + dst.length + ")", buffer);
    }

    private static void print(String action, ByteBuf buffer) {
        System.out.println("after ===========" + action + "============");
        System.out.println("容量 —— capacity(): " + buffer.capacity());
        System.out.println("最大容量 —— maxCapacity(): " + buffer.maxCapacity());
        System.out.println("读指针位置 —— readerIndex(): " + buffer.readerIndex());
        System.out.println("可读字节数 —— readableBytes(): " + buffer.readableBytes());
        System.out.println("是否可读 —— isReadable(): " + buffer.isReadable());
        System.out.println("写指针位置 —— writerIndex(): " + buffer.writerIndex());
        System.out.println("可写字节数 —— writableBytes(): " + buffer.writableBytes());
        System.out.println("是否可写 —— isWritable(): " + buffer.isWritable());
        System.out.println("可写最大字节数 —— maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println();
    }
}
