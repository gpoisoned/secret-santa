package service;

import model.Relationship;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static util.TestConstants.*;

public class UT_RelationshipService {

    private RelationshipService relationshipService;

    @Before
    public void setUp() {
        Relationship r1 = new Relationship(PERSON_A, PERSON_B);
        Relationship r2 = new Relationship(PERSON_C, PERSON_B);

        Set<Relationship> relations = new HashSet<>();
        relations.add(r1);
        relations.add(r2);

        relationshipService = new RelationshipService(relations);
    }

    @Test
    public void testRelationship() {
        assertTrue(relationshipService.areRelated(PERSON_A, PERSON_B));
        assertTrue(relationshipService.areRelated(PERSON_B, PERSON_A));

        assertTrue(relationshipService.areRelated(PERSON_C, PERSON_B));
        assertTrue(relationshipService.areRelated(PERSON_B, PERSON_C));

        assertFalse(relationshipService.areRelated(PERSON_C, PERSON_A));
        assertFalse(relationshipService.areRelated(PERSON_C, PERSON_E));
        assertFalse(relationshipService.areRelated(PERSON_A, PERSON_D));
    }

}
