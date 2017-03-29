package mock.mockito.impl;

import com.google.common.collect.Maps;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class MockInterceptor implements MethodInterceptor {

    private Map<Invocation, Object> result = Maps.newHashMap();
    private Invocation lastInvocation;

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Invocation invocation = new Invocation(o, method, objects, methodProxy);

        lastInvocation = invocation;

        if (result.containsKey(invocation)) {
            return result.get(invocation);
        } else {
            return methodProxy.invokeSuper(o, objects);
        }
    }

    protected void thenReturn(Object returnValue) {
        result.put(lastInvocation, returnValue);
    }

    public static class When<T> {
        private final MockInterceptor interceptor;
        When(MockInterceptor interceptor) {
            this.interceptor = interceptor;
        }
        public void thenReturn(T returnValue) {
            interceptor.thenReturn(returnValue);
        }
    }
}

class Invocation {
    private final Object mock;

    private final Method method;

    private final Object[] arguments;

    private final MethodProxy proxy;

    public Invocation(Object mock, Method method, Object[] arguments, MethodProxy proxy) {
        this.mock = mock;
        this.method = method;
        this.arguments = arguments;
        this.proxy = proxy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        }
        Invocation other = (Invocation) obj;
        return this.method.equals(other.method) && this.proxy.equals((other).proxy)
                && Arrays.deepEquals(arguments, other.arguments);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
