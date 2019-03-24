package constraint;

import matcher.MatchResult;
import service.MatchResultTrackerService;

/**
 * For a given match (A, B) this constraint is satisfied only if the same match
 * (A, B) has been recorded at least three rounds ago.
 * <p>
 * This relies on the match result tracker {@link MatchResultTrackerService} to update
 * its history and last seen count when new valid matches are found.
 */
public class CanMatchSamePersonEveryThreeYears implements IMatchConstraint {

    private MatchResultTrackerService resultTrackerService;

    public CanMatchSamePersonEveryThreeYears(MatchResultTrackerService resultTrackerService) {
        this.resultTrackerService = resultTrackerService;
    }

    @Override
    public Boolean isSatisfied(MatchResult possibleMatch) {
        int lastMatchedYear = resultTrackerService.getLatestMatchedRound(possibleMatch);

        // If this match hasn't been recorded before, it is automatically a valid match
        if (lastMatchedYear < 0) {
            return true;
        }

        int currentYear = resultTrackerService.getCurrentYear();
        return (currentYear - lastMatchedYear) > 3;
    }
}
