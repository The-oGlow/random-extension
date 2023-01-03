package com.glowanet.exception;

import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public abstract class AbstractExceptionTest {

    protected static final String                   ERR_MSG   = "MSG_TEST";
    protected static final IllegalArgumentException ERR_CAUSE = new IllegalArgumentException(ERR_MSG);

    private final Class<?> exceptionType;

    protected AbstractExceptionTest(Class<?> exceptionType) {
        this.exceptionType = exceptionType;
    }

    protected Class<?> getExceptionType() {
        return exceptionType;
    }

    protected void verifyException(Throwable throwable) {
        verifyException(throwable, null, null);
    }

    protected void verifyException(Throwable throwable, String errMsg) {
        verifyException(throwable, errMsg, null);
    }

    protected void verifyException(Throwable throwable, Throwable errCause) {
        verifyException(throwable, null, errCause);
    }

    protected void verifyException(Throwable throwable, String errMsg, Throwable errCause) {
        assertThat(throwable, instanceOf(getExceptionType()));
        if (errMsg != null) {
            assertThat(throwable.getMessage(), equalTo(errMsg));
        }
        if (errCause != null) {
            assertThat(throwable.getCause(), equalTo(errCause));
        }
    }

    @Test
    public void testCreateNoParameter() {
        Throwable throwable = ReflectionHelper.newInstance(getExceptionType());
        verifyException(throwable);
    }

    @Test
    public void testCreateWithCause() {
        Throwable throwable = ReflectionHelper.newInstance(getExceptionType(), new Class[]{Throwable.class}, new Object[]{ERR_CAUSE});
        verifyException(throwable, ERR_CAUSE);
    }

    @Test
    public void testCreateWithMsg() {
        Throwable throwable = ReflectionHelper.newInstance(getExceptionType(), new Class[]{String.class}, new Object[]{ERR_MSG});
        verifyException(throwable, ERR_MSG);
    }
}
