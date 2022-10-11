package com.glowanet.tools.random.legacy;

import com.glowanet.tools.random.ILegacyStrategy;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.List;

public abstract class AbstractLegacyStrategy implements ILegacyStrategy {

    public abstract List<Type> supportedTypes();

    @Override
    public boolean isSupported(Class<?> type) {
        return supportedTypes().stream().anyMatch(st -> st.equals(type));
    }

    /**
     * @return new random generator
     */
    protected SecureRandom newRandom() {
        return new SecureRandom();
    }
}
