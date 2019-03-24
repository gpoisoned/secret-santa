package matcher;

import constraint.IMatchConstraint;
import exception.MatchNotFound;
import model.Person;

import java.util.*;

/**
 * In this implementation of the {@link IMatchAlgorithm} interface the list of
 * participants are copied into two separate lists of participants; senders and
 * receivers. We then randomly shuffles the receivers list. Finally, we pair
 * the gift sender with the receiver by iterating through those two lists.
 * <p>
 * Once the list of results {@link MatchResult} has been generated, we then check
 * that all of the match constraints {@link IMatchConstraint} are satisfied.
 * The result is only selected when every match satisfies all the constraints.
 * <p>
 * To avoid the chances of searching for matches infinitely, we have a limit of
 * {@value MAX_RETRY}. When there is no match found, an exception {@link MatchNotFound}
 * is thrown.
 */
public class ShuffledListMatcher implements IMatchAlgorithm {

    private static final int MAX_RETRY = 10_000;

    @Override
    public List<MatchResult> findMatches(List<Person> members, List<IMatchConstraint> constraints) throws MatchNotFound {
        List<Person> senders = new ArrayList<>(members);
        List<Person> receivers = new ArrayList<>(members);
        List<IMatchConstraint> matchConstraints = new ArrayList<>(constraints);

        List<MatchResult> allMatches = new ArrayList<>();

        int retry = 0;
        boolean allConstraintsSatisfied;
        while (retry < MAX_RETRY) {
            allMatches.clear();
            allMatches = generateMatches(senders, receivers);

            allConstraintsSatisfied = allMatches
                    .stream()
                    .allMatch(match -> matchConstraints.stream()
                            .allMatch(constraint -> constraint.isSatisfied(match)));

            if (allConstraintsSatisfied) {
                return allMatches;
            }
            retry++;
        }
        throw new MatchNotFound();
    }

    private List<MatchResult> generateMatches(List<Person> senders, List<Person> receivers) {
        List<MatchResult> possibleMatches = new ArrayList<>();
        Random random = new Random(System.nanoTime());

        // Randomly shuffle the list of all receivers
        Collections.shuffle(receivers, random);

        for (int index = 0; index < senders.size(); index++) {
            Person giver = senders.get(index);
            Person receiver = receivers.get(index);

            MatchResult possibleMatch = new MatchResult(giver, receiver);
            possibleMatches.add(possibleMatch);
        }
        return possibleMatches;
    }

}
