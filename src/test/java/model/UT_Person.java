package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class UT_Person {

    @Test
    public void createPerson() {
        Person person = new Person("Foo");
        assertNotNull(person);
    }

    @Test
    public void getPersonName() {
        Person person = new Person("Bar");
        assertEquals("Bar", person.getName());
    }

    @Test
    public void testEquals() {
        assertEquals(new Person("Foo"), new Person("Foo"));
        assertEquals(new Person("FooBar"), new Person("FooBar"));

        assertNotEquals(new Person("FooBar"), new Person("Foobar"));
        assertNotEquals(new Person("FooBar"), new Person("foobar"));
    }
}
