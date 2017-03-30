package mock.mockito.impl;

import com.google.common.collect.Maps;
import org.mockito.cglib.proxy.Enhancer;

import java.util.Map;

public class MyMockito {
    private static MockInterceptor interceptor = new MockInterceptor();

    public static <T> T mock(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(interceptor);
        T obj = (T) enhancer.create();
        return obj;
    }

    public static <T> T spy(Class<T> clazz) {
        T obj = mock(clazz);
        interceptor.callRealMethodsOnMissing(obj);
        return obj;
    }

    public static CallRealMethodWhen doCallRealMethod() {
        return new CallRealMethodWhen(interceptor);
    }

    public static When when(Object object) {
        return new When(interceptor);
    }
}
