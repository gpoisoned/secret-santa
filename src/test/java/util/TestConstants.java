package util;

import model.Person;

import java.util.Arrays;
import java.util.List;

public final class TestConstants {

    public static final Person PERSON_A = new Person("A");

    public static final Person PERSON_B = new Person("B");

    public static final Person PERSON_C = new Person("C");

    public static final Person PERSON_D = new Person("D");

    public static final Person PERSON_E = new Person("E");

    public static final List<Person> oddMembers = Arrays.asList(PERSON_A, PERSON_B, PERSON_C, PERSON_D, PERSON_E);

    public static final List<Person> evenMembers = Arrays.asList(PERSON_A, PERSON_B, PERSON_C, PERSON_D);

}
