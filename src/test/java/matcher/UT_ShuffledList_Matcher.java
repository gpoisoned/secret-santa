package matcher;

import constraint.IMatchConstraint;
import exception.MatchNotFound;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import util.TestConstants;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UT_ShuffledList_Matcher {

    @Mock
    private
    IMatchConstraint trueConstraint;

    @Mock
    private
    IMatchConstraint falseConstraint;

    private IMatchAlgorithm matcher;

    @Before
    public void setUp() {
        when(trueConstraint.isSatisfied(any())).thenReturn(true);
        when(falseConstraint.isSatisfied(any())).thenReturn(false);

        matcher = new ShuffledListMatcher();
    }

    @Test
    public void findMatches() throws MatchNotFound {
        List<Person> participants = TestConstants.evenMembers;
        List<IMatchConstraint> allValidConstraints = Arrays.asList(trueConstraint, trueConstraint);

        List<MatchResult> matches = matcher.findMatches(participants, allValidConstraints);
        assertEquals(participants.size(), matches.size());
    }


    @Test(expected = MatchNotFound.class)
    public void throwsWhenConstraintFailsUntilMaxRetryCount() throws MatchNotFound {
        List<Person> participants = TestConstants.evenMembers;
        List<IMatchConstraint> invalidConstraint = Arrays.asList(trueConstraint, falseConstraint);

        matcher.findMatches(participants, invalidConstraint);
    }
}
