package constraint;

import matcher.MatchResult;

/**
 * This constraint is satisfied only when a given participant 'A'
 * is matched with any other participant except for 'A'.
 */
public class CannotMatchYourself implements IMatchConstraint {

    @Override
    public Boolean isSatisfied(MatchResult possibleMatch) {
        String sender = possibleMatch.getSender().getName();
        String receiver = possibleMatch.getReceiver().getName();

        return !sender.equals(receiver);
    }

}