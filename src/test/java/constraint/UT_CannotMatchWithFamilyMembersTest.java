package constraint;

import matcher.MatchResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.RelationshipService;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;
import static util.TestConstants.PERSON_A;
import static util.TestConstants.PERSON_E;

@RunWith(MockitoJUnitRunner.class)
public class UT_CannotMatchWithFamilyMembersTest {

    @Mock
    private RelationshipService relationshipService;

    private IMatchConstraint constraint;

    private static final MatchResult AE = new MatchResult(PERSON_A, PERSON_E);

    @Before
    public void setUp() {
        constraint = new CannotMatchWithFamilyMembers(relationshipService);
    }

    @Test
    public void returnsTrueWhenTwoPeopleAreNotRelated() {
        when(relationshipService.areRelated(PERSON_A, PERSON_E)).thenReturn(false);

        // Act
        Boolean result = constraint.isSatisfied(AE);
        assertTrue(result);
    }

    @Test
    public void returnsFalseWhenTwoPeopleAreRelated() {
        when(relationshipService.areRelated(PERSON_A, PERSON_E)).thenReturn(true);

        // Act
        Boolean result = constraint.isSatisfied(AE);
        assertFalse(result);
    }

}