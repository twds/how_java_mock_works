package mock.proxy;

import mock.model.Counter;
import mock.model.ICountable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CounterInvocationHandler implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("value")) {
            return 123;
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        CounterInvocationHandler handler = new CounterInvocationHandler();
//        Counter counter = (Counter)Proxy.newProxyInstance(Counter.class.getClassLoader(), new Class[] { Counter.class }, handler);
        ICountable counter = (ICountable)Proxy.newProxyInstance(Counter.class.getClassLoader(), new Class[] { ICountable.class }, handler);
        System.out.println(counter.value());
    }
}
