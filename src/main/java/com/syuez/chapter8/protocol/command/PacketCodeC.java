package com.syuez.chapter8.protocol.command;

import com.syuez.chapter8.serialize.Serializer;
import com.syuez.chapter8.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.syuez.chapter8.protocol.command.Command.LOGIN_REQUEST;

/**
 * 数据包编码
 */
public class PacketCodeC {
    /**
     * 魔数
     */
    private static final int MAGIC_NUMBER = 0x12345678;
    /*
    * static 表示这个字段属于类本身，不属于类的实例（PacketCodeC 对象）
    * final 表示这个字段是只读的
    * Class<? extends Packet> 是上界通配符，表示这个类只能是 Packet 的子类
    * */
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    /*
    * static 代码块只在类加载内存的时候执行一次，而类的构造函数则每次实例化对象时都执行
    * */
    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    /**
     * 编码
     */
    public ByteBuf encode(Packet packet) {
        // 创建 ByteBuf 对象
        // ioBuffer() 方法会返回适配 io 读写相关的内存，它会尽可能创建一个直接内存，直接内存可以理解为不受 jvm 堆管理的内存空间，
        // 写到 IO 缓冲区的效果更高
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 将 Java 对象序列化成二进制数据包
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER); // 魔数，4字节
        byteBuf.writeByte(packet.getVersion()); // 协议版本，1字节
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm()); // 序列化算法，1字节
        byteBuf.writeByte(packet.getCommand()); // 指令，1字节
        byteBuf.writeInt(bytes.length); // 数据长度，4字节
        byteBuf.writeBytes(bytes); // 数据，N字节

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        // 这里 requestType 被隐性的转成了 LoginRequestPacket 类
        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
