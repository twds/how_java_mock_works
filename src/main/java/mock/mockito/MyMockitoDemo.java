package mock.mockito;

import mock.mockito.impl.MyMockito;
import mock.model.Person;
import org.junit.Before;
import org.junit.Test;

public class MyMockitoDemo {

    private Person person;

    @Before
    public void setUp() {
        person = MyMockito.mock(Person.class);
    }

    @Test
    public void testWhen() {
        person.printName();
        MyMockito.when(person.getName()).thenReturn("My Mockito test");
        System.out.println(person.getName());
        person.printName();
    }
}
