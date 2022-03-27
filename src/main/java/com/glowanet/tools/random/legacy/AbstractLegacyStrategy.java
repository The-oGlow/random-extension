package com.glowanet.tools.random.legacy;

import com.glowanet.tools.random.ILegacyStrategy;

import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractLegacyStrategy implements ILegacyStrategy {

    public abstract List<Type> supportedTypes();

    @Override
    public boolean isSupported(Class<?> type) {
        return supportedTypes().stream().anyMatch(st -> st.equals(type));
    }

}
