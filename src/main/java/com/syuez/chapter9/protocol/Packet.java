package com.syuez.chapter9.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据包结构
 */
@Getter
@Setter
public  abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
