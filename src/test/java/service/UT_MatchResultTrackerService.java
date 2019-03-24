package service;

import matcher.MatchResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static util.TestConstants.*;

public class UT_MatchResultTrackerService {

    private static final MatchResult AB = new MatchResult(PERSON_A, PERSON_B);

    private static final MatchResult BC = new MatchResult(PERSON_B, PERSON_C);

    private static final MatchResult BA = new MatchResult(PERSON_B, PERSON_A);

    private MatchResultTrackerService matchResultTrackerService;

    @Before
    public void setUp() {
        matchResultTrackerService = new MatchResultTrackerService();
    }

    @Test
    public void getLatestMatchedRoundDefaultValue() {
        assertEquals(-1, matchResultTrackerService.getLatestMatchedRound(AB));
        assertEquals(-1, matchResultTrackerService.getLatestMatchedRound(BC));
        assertEquals(-1, matchResultTrackerService.getLatestMatchedRound(BA));
    }

    @Test
    public void getCurrentYearDefaultValue() {
        assertEquals(0, matchResultTrackerService.getCurrentYear());
    }

    @Test
    public void incrementCurrentYear() {
        matchResultTrackerService.incrementCurrentYear();
        assertEquals(1, matchResultTrackerService.getCurrentYear());

        matchResultTrackerService.incrementCurrentYear();
        matchResultTrackerService.incrementCurrentYear();
        assertEquals(3, matchResultTrackerService.getCurrentYear());
    }

    @Test
    public void updateMatches() {
        // Act
        matchResultTrackerService.updateMatches(Collections.singletonList(AB));

        assertEquals(0, matchResultTrackerService.getLatestMatchedRound(AB));
        assertEquals(-1, matchResultTrackerService.getLatestMatchedRound(BA));
        assertEquals(-1, matchResultTrackerService.getLatestMatchedRound(BC));

        // Act
        matchResultTrackerService.incrementCurrentYear();
        matchResultTrackerService.incrementCurrentYear();
        matchResultTrackerService.updateMatches(Collections.singletonList(AB));

        assertEquals(2, matchResultTrackerService.getLatestMatchedRound(AB));
        assertEquals(-1, matchResultTrackerService.getLatestMatchedRound(BA));
        assertEquals(-1, matchResultTrackerService.getLatestMatchedRound(BC));
    }
}
