package mock.mockito.impl;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class MockInterceptor implements MethodInterceptor {

    private Map<Invocation, Object> result = Maps.newHashMap();
    private Set<Object> defaultCallRealMethods = Sets.newHashSet();
    private Set<Invocation> invokeRealMethods = Sets.newHashSet();
    private Set<String> builtInMethods = Sets.newHashSet("clone", "equals", "finalize", "getClass", "hashCode",
            "notify", "notifyAll", "toString", "wait");
    private boolean doCallRealMethod;
    private Invocation lastInvocation;

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (builtInMethods.contains(method.getName())) {
            return methodProxy.invokeSuper(o, objects);
        }

        Invocation invocation = new Invocation(o, method, objects, methodProxy);

        try {
            if (result.containsKey(invocation)) {
                return result.get(invocation);
            } else if (invokeRealMethods.contains(invocation) || defaultCallRealMethods.contains(o)) {
                return methodProxy.invokeSuper(o, objects);
            } else {
                return null;
            }
        } finally {
            lastInvocation = invocation;
            if (doCallRealMethod) {
                doCallRealMethod = false;
                thenCallRealMethod();
            }
        }
    }

    protected void callRealMethodsOnMissing(Object mock) {
        defaultCallRealMethods.add(mock);
    }

    protected void thenReturn(Object returnValue) {
        invokeRealMethods.remove(lastInvocation);
        result.put(lastInvocation, returnValue);
    }

    protected void thenCallRealMethod() {
        result.remove(lastInvocation);
        invokeRealMethods.add(lastInvocation);
    }

    protected void setDoCallRealMethod(boolean doCallRealMethod) {
        this.doCallRealMethod = doCallRealMethod;
    }
}
