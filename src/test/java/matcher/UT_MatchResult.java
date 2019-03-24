package matcher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static util.TestConstants.PERSON_A;
import static util.TestConstants.PERSON_B;

public class UT_MatchResult {

    @Test
    public void createMatchResult() {
        MatchResult matchResult = new MatchResult(PERSON_A, PERSON_B);
        assertNotNull(matchResult);
    }

    @Test
    public void getSender() {
        MatchResult matchResult = new MatchResult(PERSON_A, PERSON_B);

        assertEquals(PERSON_A, matchResult.getSender());
        assertEquals(PERSON_A.getName(), matchResult.getSender().getName());
    }

    @Test
    public void getReceiver() {
        MatchResult matchResult = new MatchResult(PERSON_A, PERSON_B);

        assertEquals(PERSON_B, matchResult.getReceiver());
        assertEquals(PERSON_B.getName(), matchResult.getReceiver().getName());
    }

    @Test
    public void testEquals() {
        MatchResult AB = new MatchResult(PERSON_A, PERSON_B);
        MatchResult BA = new MatchResult(PERSON_B, PERSON_A);

        assertEquals(AB, new MatchResult(PERSON_A, PERSON_B));
        assertEquals(BA, new MatchResult(PERSON_B, PERSON_A));

        assertNotEquals(AB, BA);
    }

    @Test
    public void testToString() {
        MatchResult matchResult = new MatchResult(PERSON_A, PERSON_B);
        assertEquals("A, B", matchResult.toString());
    }
}
