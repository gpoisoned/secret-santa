package constraint;

import matcher.MatchResult;
import org.junit.Test;

import static org.junit.Assert.*;
import static util.TestConstants.PERSON_A;
import static util.TestConstants.PERSON_E;

public class UT_CannotMatchYourselfTest {

    private IMatchConstraint constraint = new CannotMatchYourself();

    @Test
    public void isSatisfiedWhenMatchedWithDifferentName() {
        MatchResult match = new MatchResult(PERSON_A, PERSON_E);
        assertTrue(constraint.isSatisfied(match));
    }


    @Test
    public void isNotSatisfiedWhenMatchedWithSameName() {
        MatchResult match = new MatchResult(PERSON_E, PERSON_E);
        assertFalse(constraint.isSatisfied(match));
    }
}