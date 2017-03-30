package mock.mockito.impl;

public class When<T> {
    private final MockInterceptor interceptor;

    public When(MockInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void thenReturn(T returnValue) {
        interceptor.thenReturn(returnValue);
    }

    public void thenCallRealMethod() {
        interceptor.thenCallRealMethod();
    }
}