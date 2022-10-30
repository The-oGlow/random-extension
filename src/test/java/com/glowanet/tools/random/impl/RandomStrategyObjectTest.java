package com.glowanet.tools.random.impl;

public class RandomStrategyObjectTest extends
        AbstractRandomStrategyByTypeTest<RandomStrategyObjectTest.RandomStrategyObjectTestObject, RandomStrategyObject> {

    public static class RandomStrategyObjectTestObject {
    }

    public RandomStrategyObjectTest() {
        super(RandomStrategyObject.class, RandomStrategyObjectTestObject.class);
    }
}