package com.syuez.chapter9.serialize;

import com.syuez.chapter9.serialize.impl.JSONSerializer;

/**
 * 序列化
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 获取序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
