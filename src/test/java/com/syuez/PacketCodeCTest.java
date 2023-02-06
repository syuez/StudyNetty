package com.syuez;

import com.syuez.chapter8.protocol.command.LoginRequestPacket;
import com.syuez.chapter8.protocol.command.Packet;
import com.syuez.chapter8.protocol.command.PacketCodeC;
import com.syuez.chapter8.serialize.Serializer;
import com.syuez.chapter8.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodeCTest {
    @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setVersion((byte) 1);
        loginRequestPacket.setUserId(123);
        loginRequestPacket.setUsername("loser");
        loginRequestPacket.setPassword("123456");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}
