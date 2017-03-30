package mock.mockito.impl;

import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Invocation {
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
        return this.mock.equals(other.mock) && this.method.equals(other.method) && this.proxy.equals((other).proxy)
                && Arrays.deepEquals(arguments, other.arguments);
    }

    @Override
    public int hashCode() {
        int result = mock.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + Arrays.hashCode(arguments);
        result = 31 * result + proxy.hashCode();
        return result;
    }
}
