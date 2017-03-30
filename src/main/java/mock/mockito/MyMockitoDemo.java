package mock.mockito;

import mock.mockito.impl.MyMockito;
import mock.model.Person;
import org.junit.Before;
import org.junit.Test;

public class MyMockitoDemo {

    private Person mock;
    private Person spy;

    @Before
    public void setUp() {
        mock = MyMockito.mock(Person.class);
        spy = MyMockito.spy(Person.class);
    }

    @Test
    public void testSpy() {
        spy.printName();
        MyMockito.when(spy.getName()).thenReturn("Test Spy");
        spy.printName();
    }

    @Test
    public void testMock() {
        MyMockito.when(mock.getName()).thenReturn("No Output");
        mock.printName();
        MyMockito.when(mock.getName()).thenReturn("Test Mock");
        mock.printName();
        MyMockito.when("Whatever points to the last call").thenCallRealMethod();
        mock.printName();
    }

    @Test
    public void testDoCallRealMethod() {
        MyMockito.when(mock.getName()).thenReturn("Call Real PrintName Method");
        MyMockito.doCallRealMethod().when(mock).printName();
        mock.printName();
    }
}
