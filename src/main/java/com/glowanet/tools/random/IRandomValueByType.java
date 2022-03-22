package com.glowanet.tools.random;

public interface IRandomValueByType<T> extends IRandomValue<T> {

    Object randomValue(Class<?> clazz);
}
