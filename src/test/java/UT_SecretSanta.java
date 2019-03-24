import constraint.CannotMatchYourself;
import constraint.IMatchConstraint;
import exception.DuplicateName;
import matcher.IMatchAlgorithm;
import matcher.MatchResult;
import matcher.ShuffledListMatcher;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import util.TestConstants;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class UT_SecretSanta {

    private IMatchAlgorithm matcher;

    private List<IMatchConstraint> constraints;

    @Before
    public void setUp() {
        matcher = new ShuffledListMatcher();

        constraints = Collections.singletonList(new CannotMatchYourself());
    }

    @Test(expected = DuplicateName.class)
    public void testDuplicateNames() throws DuplicateName {
        // Arrange
        List<Person> members = new ArrayList<>();
        members.add(TestConstants.PERSON_A);
        members.add(TestConstants.PERSON_A);

        SecretSanta secretSanta = new SecretSanta(matcher, members, constraints);
        secretSanta.getMatches();
    }

    @Test
    public void testWithOddNumberOfMembers() throws DuplicateName {
        // Arrange
        List<Person> members = TestConstants.oddMembers;
        SecretSanta secretSanta = new SecretSanta(matcher, members, constraints);

        // Act
        List<MatchResult> matches = secretSanta.getMatches();
        verifyMatches(matches, members);
    }

    @Test
    public void testWithEvenNumberOfMembers() throws DuplicateName {
        // Arrange
        List<Person> members = TestConstants.evenMembers;
        SecretSanta secretSanta = new SecretSanta(matcher, members, constraints);

        // Act
        List<MatchResult> matches = secretSanta.getMatches();
        verifyMatches(matches, members);
    }

    private void verifyMatches(List<MatchResult> matches, List<Person> members) {
        assertEquals("Size of matched results does not equal input size", members.size(), matches.size());

        Set<String> senders = matches
                .stream()
                .map(m -> m.getSender().getName())
                .collect(Collectors.toSet());
        assertEquals("Size of senders does not equal input size", members.size(), senders.size());

        Set<String> receivers = matches
                .stream()
                .map(m -> m.getReceiver().getName())
                .collect(Collectors.toSet());
        assertEquals("Size of receivers does not equal input size", members.size(), receivers.size());
    }

}
