package exception;

public class DuplicateName extends Exception {

    private static final String MESSAGE = "Duplicate name found for the participant";

    public DuplicateName() {
        super(MESSAGE);
    }
}
