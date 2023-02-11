package com.syuez.chapter10.protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Packet {
    byte version = 1;

    public abstract Byte getCommand();
}
