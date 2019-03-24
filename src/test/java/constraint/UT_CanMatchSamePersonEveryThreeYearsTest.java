package constraint;

import matcher.MatchResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.MatchResultTrackerService;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static util.TestConstants.PERSON_A;
import static util.TestConstants.PERSON_E;

@RunWith(MockitoJUnitRunner.class)
public class UT_CanMatchSamePersonEveryThreeYearsTest {

    @Mock
    private MatchResultTrackerService matchResultTrackerService;

    private IMatchConstraint constraint;

    private static final MatchResult AE = new MatchResult(PERSON_A, PERSON_E);

    @Before
    public void setUp() {
        constraint = new CanMatchSamePersonEveryThreeYears(matchResultTrackerService);
    }

    @Test
    public void returnsTrueWithNoHistory() {
        // Setup mock to return -1 to indicate that the match hasn't been tracked before
        when(matchResultTrackerService.getLatestMatchedRound(any(MatchResult.class))).thenReturn(-1);

        // Act
        Boolean result = constraint.isSatisfied(AE);
        assertTrue(result);
    }

    @Test
    public void returnsTrueWhenMatchWasAtLeastThreeYearsAgo() {
        // Setup mock such that the match was seen at least 3 years ago
        when(matchResultTrackerService.getCurrentYear()).thenReturn(5);
        when(matchResultTrackerService.getLatestMatchedRound(any(MatchResult.class))).thenReturn(1);

        // Act
        Boolean result = constraint.isSatisfied(AE);
        assertTrue(result);
    }

    @Test
    public void returnsFalseWhenMatchWasLessThanThreeYearsAgo() {
        // Setup mock such that the match was seen less than 3 years ago
        when(matchResultTrackerService.getCurrentYear()).thenReturn(2);
        when(matchResultTrackerService.getLatestMatchedRound(any(MatchResult.class))).thenReturn(1);

        // Act
        Boolean result = constraint.isSatisfied(AE);
        assertFalse(result);
    }

}