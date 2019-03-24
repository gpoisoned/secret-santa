package constraint;

import matcher.MatchResult;

@FunctionalInterface
public interface IMatchConstraint {

    /**
     * Check whether the match satisfies this implementation of constraint.
     *
     * @param possibleMatch
     * @return
     */
    Boolean isSatisfied(MatchResult possibleMatch);

}
