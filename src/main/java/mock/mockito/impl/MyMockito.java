package mock.mockito.impl;

import org.mockito.cglib.proxy.Enhancer;

public class MyMockito {
    private static MockInterceptor interceptor = new MockInterceptor();

    public static <T> T mock(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(interceptor);
        return (T) enhancer.create();
    }

    public static MockInterceptor.When when(Object object) {
        return new MockInterceptor.When(interceptor);
    }
}
