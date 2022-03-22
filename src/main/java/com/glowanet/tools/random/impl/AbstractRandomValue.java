package com.glowanet.tools.random.impl;

import com.glowanet.tools.random.IRandomValue;

public abstract class AbstractRandomValue<T> implements IRandomValue<T> {

    private final Class<T> typeOfT;

    protected AbstractRandomValue(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    public Class<T> getTypeOfT() {
        return typeOfT;
    }

    @Override
    public T randomValue(T rangeStart, T rangeEnd) {
        return randomValue();
    }

}
