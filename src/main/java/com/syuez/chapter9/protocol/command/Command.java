package com.syuez.chapter9.protocol.command;

/**
 * 指令
 * 在自定义协议中使用一个字节
 * 表示指令
 */
public interface Command {
    /**
     * 登录请求
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登录响应
     */
    Byte LOGIN_RESPONSE = 2;
}
