package com.syuez.chapter9.protocol.response;

import com.syuez.chapter9.protocol.Packet;
import lombok.Getter;
import lombok.Setter;

import static com.syuez.chapter9.protocol.command.Command.LOGIN_RESPONSE;

@Getter
@Setter
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
