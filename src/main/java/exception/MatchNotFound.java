package exception;

public class MatchNotFound extends Exception {

    private static final String MESSAGE = "Unable to find the match";

    public MatchNotFound() {
        super(MESSAGE);
    }
}
