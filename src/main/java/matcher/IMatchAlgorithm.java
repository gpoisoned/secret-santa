package matcher;

import constraint.IMatchConstraint;
import exception.MatchNotFound;
import model.Person;

import java.util.List;

@FunctionalInterface
public interface IMatchAlgorithm {

    /**
     * @param members     list of people participating in the game
     * @param constraints list of match constraints that need to be satisfied
     * @return
     * @throws MatchNotFound
     */
    List<MatchResult> findMatches(List<Person> members, List<IMatchConstraint> constraints) throws MatchNotFound;

}
