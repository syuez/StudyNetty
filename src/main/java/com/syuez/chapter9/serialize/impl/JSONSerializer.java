package com.syuez.chapter9.serialize.impl;

import com.alibaba.fastjson2.JSON;
import com.syuez.chapter9.serialize.Serializer;
import com.syuez.chapter9.serialize.SerializerAlgorithm;

/**
 * JSON 序列化
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
