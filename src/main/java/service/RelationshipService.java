package service;

import model.Person;
import model.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * This class stores the relationships for the participants and in addition
 * it provides a method to check if any given two people are related.
 */
public class RelationshipService {

    private Set<Relationship> relations;

    public RelationshipService(Set<Relationship> relations) {
        this.relations = relations;
    }

    public boolean areRelated(Person a, Person b) {
        return relations.stream()
                .anyMatch(relation -> {
                    Set<Person> related = new HashSet<>();

                    related.add(relation.getPrimary());
                    related.add(relation.getSecondary());

                    return related.contains(a) && related.contains(b);
                });
    }
}
