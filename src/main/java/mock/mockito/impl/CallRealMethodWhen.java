package mock.mockito.impl;

public class CallRealMethodWhen {

    private MockInterceptor interceptor;

    public CallRealMethodWhen(MockInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public <T> T when(T obj) {
        interceptor.setDoCallRealMethod(true);
        return obj;
    }
}
