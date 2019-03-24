package service;

import matcher.MatchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class records the valid matches along with the round (year)
 * when it was last valid.
 */
public class MatchResultTrackerService {

    private int currentYear;

    private Map<MatchResult, Integer> matchHistory;

    public MatchResultTrackerService() {
        this.currentYear = 0;
        this.matchHistory = new HashMap<>();
    }

    public int getLatestMatchedRound(MatchResult match) {
        return matchHistory.getOrDefault(match, -1);
    }

    public void updateMatches(List<MatchResult> matches) {
        matches.forEach(match -> matchHistory.put(match, currentYear));
    }

    public void incrementCurrentYear() {
        currentYear++;
    }

    public int getCurrentYear() {
        return currentYear;
    }

}
