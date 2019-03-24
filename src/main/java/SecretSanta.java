import constraint.CanMatchSamePersonEveryThreeYears;
import constraint.CannotMatchYourself;
import constraint.IMatchConstraint;
import exception.DuplicateName;
import exception.MatchNotFound;
import matcher.IMatchAlgorithm;
import matcher.MatchResult;
import matcher.ShuffledListMatcher;
import model.Person;
import service.MatchResultTrackerService;

import java.util.*;
import java.util.stream.Collectors;

public class SecretSanta {

    private IMatchAlgorithm matcher;

    private List<Person> members;

    private List<IMatchConstraint> matchConstraints;

    private MatchResultTrackerService resultTrackerService;

    SecretSanta(IMatchAlgorithm matcher,
                List<Person> members,
                List<IMatchConstraint> matchConstraints,
                MatchResultTrackerService resultTrackerService) {
        this.matcher = matcher;
        this.members = members;
        this.matchConstraints = matchConstraints;
        this.resultTrackerService = resultTrackerService;
    }


    List<MatchResult> getMatches() throws DuplicateName {
        validateUniqueNames();

        List<MatchResult> matchResults = new ArrayList<>();
        try {
            matchResults = matcher.findMatches(members, matchConstraints);

            resultTrackerService.updateMatches(matchResults);
            resultTrackerService.incrementCurrentYear();
        } catch (MatchNotFound matchNotFound) {
            System.out.println(matchNotFound.getMessage());
        }
        return matchResults;
    }

    private void validateUniqueNames() throws DuplicateName {
        Set<String> memberNames = members.stream()
                .map(Person::getName)
                .collect(Collectors.toSet());
        if (memberNames.size() != members.size()) {
            throw new DuplicateName();
        }
    }

    private void print(List<MatchResult> results) {
        results.forEach(System.out::println);
    }

    public static void main(String[] args) {

        // Setup participants
        Person A = new Person("A");
        Person B = new Person("B");
        Person C = new Person("C");
        Person D = new Person("D");
        Person E = new Person("E");
        Person F = new Person("F");
        Person G = new Person("G");
        List<Person> members = Arrays.asList(A, B, C, D, E, F, G);

        // Setup secret-santa helper services
        MatchResultTrackerService resultTrackerService = new MatchResultTrackerService();

        // Setup all constraints
        IMatchConstraint cannotMatchYourself = new CannotMatchYourself();
        IMatchConstraint canMatchSamePersonEveryThreeYears =
                new CanMatchSamePersonEveryThreeYears(resultTrackerService);
        List<IMatchConstraint> constraints = Arrays.asList(
                cannotMatchYourself,
                canMatchSamePersonEveryThreeYears);

        // Setup match algorithm
        IMatchAlgorithm matcher = new ShuffledListMatcher();

        // Start the program
        System.out.println("Starting secret-santa matcher");
        System.out.println("Using match algorithm " + matcher.getClass().getName());

        SecretSanta secretSanta = new SecretSanta(matcher, members, constraints, resultTrackerService);
        List<MatchResult> matchResults = new ArrayList<>();
        for (int index = 0; index < 5; index++) {
            System.out.println("*******************");
            System.out.println("Round: " + index);

            try {
                matchResults = secretSanta.getMatches();
            } catch (DuplicateName duplicateName) {
                System.out.println(duplicateName.getMessage());
            }
            secretSanta.print(matchResults);
        }
    }
}
