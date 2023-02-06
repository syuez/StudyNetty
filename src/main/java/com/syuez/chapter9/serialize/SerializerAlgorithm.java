package com.syuez.chapter9.serialize;

/**
 * 序列化算法
 * 在自定义协议中使用一个字节
 * 表示序列化算法
 */
public interface SerializerAlgorithm {
    /**
     * 使用 JSON 序列化
     */
    byte JSON = 1;
}
