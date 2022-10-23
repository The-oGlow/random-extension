package com.glowanet.tools.random.impl;

public class AbstractRandomStrategyByTypeTest {

//public class AbstractRandomStrategyByTypeTest<T, ST extends AbstractRandomStrategyByType>
//        extends AbstractRandomStrategyTest<T, ST> {

//    protected AbstractRandomStrategyByTypeTest(Class<ST> strategyClazz, Class<T> valueClazz) {
//        super(strategyClazz, valueClazz);
//    }
//
//    @Before
//    public void setUp() {
//        super.setUp();
//    }
//
//    @Test
//    public void testGetTypeOfT() {
//        TestResultHelper.verifyException(() -> o2ST.getTypeOfT(), UnsupportedOperationException.class);
//    }
//
//    @Test
//    public void testNext() {
//        TestResultHelper.verifyException(() -> o2ST.getTypeOfT(), UnsupportedOperationException.class);
//    }
//
//    @Test
//    public void testNextWithClazz() {
//        Object actual = o2ST.next(valueClazz);
//
//        assertThat(actual, instanceOf(valueClazz));
//    }
//
//    @Test
//    public void testValueByStaticDefinition() {
//        Object actual = o2ST.valueByStaticDefinition(valueClazz);
//
//        assertThat(actual, instanceOf(valueClazz));
//    }
//
//    @Test
//    public void testGetProviders() {
//        List<Class<? extends AbstractLegacyStrategy>> actual = o2ST.getProviders();
//
//        assertThat(actual, notNullValue());
//        assertThat(actual, hasSize(greaterThan(0)));
//    }
//
//    @Test
//    public void testLoopThruProvider() {
//        Object actual = o2ST.loopThruProvider(valueClazz);
//
//        assertThat(actual, instanceOf(valueClazz));
//    }
}