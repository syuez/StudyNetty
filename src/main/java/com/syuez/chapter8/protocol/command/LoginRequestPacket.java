package com.syuez.chapter8.protocol.command;

import lombok.Getter;
import lombok.Setter;

import static com.syuez.chapter8.protocol.command.Command.LOGIN_REQUEST;

/**
 * 登录请求数据包
 */
@Getter
@Setter
public class LoginRequestPacket extends Packet{
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
