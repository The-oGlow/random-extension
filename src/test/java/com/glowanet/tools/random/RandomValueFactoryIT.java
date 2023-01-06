package com.glowanet.tools.random;

import com.glowanet.tools.random.impl.RandomStrategyBigDecimal;
import com.glowanet.tools.random.impl.RandomStrategyBoolean;
import com.glowanet.tools.random.impl.RandomStrategyDateTime;
import com.glowanet.tools.random.impl.RandomStrategyPrimitive;
import org.hamcrest.MatcherAssert;
import org.hamcrest.MatchersExtend;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(Parameterized.class)
public class RandomValueFactoryIT {

    @Parameterized.Parameters(name = "{index} valueClazz={0}, exepctedStrategy={1}, withFallback={2}")
    public static List<Object[]> data() {
        //List<Object[]> testData = new ArrayList<>();
        Map<Class<?>, Object[]> testData = new HashMap<>();
        for (Class<?> aClass : RandomStrategyPrimitive.SUPP_TYPES) {
            testData.put(aClass, new Object[]{aClass, RandomStrategyPrimitive.class, true});
        }
        for (Class<?> aClass : RandomStrategyBoolean.SUPP_TYPES) {
            testData.put(aClass, new Object[]{aClass, RandomStrategyBoolean.class, false});
        }
        for (Class<?> aClass : RandomStrategyBigDecimal.SUPP_TYPES) {
            testData.put(aClass, new Object[]{aClass, RandomStrategyBigDecimal.class, false});
        }
        for (Class<?> aClass : RandomStrategyDateTime.SUPP_TYPES) {
            testData.put(aClass, new Object[]{aClass, RandomStrategyDateTime.class, true});
        }
        return new ArrayList<>(testData.values());
    }

    @Parameterized.Parameter
    public Class<?> valueClazz;

    @Parameterized.Parameter(1)
    public Class<?> exepctedStrategy;

    @Parameterized.Parameter(2)
    public boolean withFallback;

    @BeforeClass
    public static void setupBeforeClass() {
        RandomValueFactory.getInstance().setSilent(false);
    }

    @Before
    public void setup() {
        RandomValueFactory.getInstance().setFallback(withFallback);
    }

    @Test
    public void testGetProvider() {
        IRandomStrategy<?> actual = RandomValueFactory.getInstance().getProvider(valueClazz);
        MatcherAssert.assertThat(actual, MatchersExtend.isInstanceOfExact(exepctedStrategy));
    }
}