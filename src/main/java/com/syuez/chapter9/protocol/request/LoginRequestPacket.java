package com.syuez.chapter9.protocol.request;

import com.syuez.chapter9.protocol.Packet;
import lombok.Getter;
import lombok.Setter;

import static com.syuez.chapter9.protocol.command.Command.LOGIN_REQUEST;

@Getter
@Setter
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
