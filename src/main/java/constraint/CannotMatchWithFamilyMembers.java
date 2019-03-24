package constraint;

import matcher.MatchResult;
import service.RelationshipService;

/**
 * This constraint is satisfied only when the people that are matched are
 * not closely related.
 * It relies on the relationship service {@link RelationshipService} to
 * correctly store all relations and to query if two people are related.
 */
public class CannotMatchWithFamilyMembers implements IMatchConstraint {

    private RelationshipService relationshipService;

    public CannotMatchWithFamilyMembers(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @Override
    public Boolean isSatisfied(MatchResult possibleMatch) {
        return !relationshipService.areRelated(possibleMatch.getSender(), possibleMatch.getReceiver());
    }
}
