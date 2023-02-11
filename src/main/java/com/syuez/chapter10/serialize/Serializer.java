package com.syuez.chapter10.serialize;

import com.syuez.chapter10.serialize.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT  = new JSONSerializer();

    byte getSerializerAlgorithm() ;

    byte[] serialize(Object object);
    /*
    * <T> 表示方法中的参数类型
    * T 表示方法返回值的类型
    * */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
