package mock.mockito;

import mock.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitoDemo {

    private Person person;

    @Before
    public void setUp() {
        person = Mockito.mock(Person.class);
    }

    @Test
    public void testWhen() {
        person.printName();
        Mockito.when("Pointer to last function call").thenCallRealMethod();
        Mockito.when(person.getName()).thenReturn("It shows how when() works");
        person.printName();
        Mockito.verify(person).printName();
    }

    @Test
    public void testVoidReturn() {
        Mockito.when(person.getName()).thenReturn("void return");
        Mockito.doCallRealMethod().when(person).printName();
        person.printName();
        Mockito.verify(person).printName();
    }
}
