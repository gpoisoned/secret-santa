package matcher;

import model.Person;

public class MatchResult {

    private Person sender;

    private Person receiver;

    public MatchResult(Person sender, Person receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public Person getSender() {
        return sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchResult that = (MatchResult) o;

        if (!sender.equals(that.sender)) return false;
        return receiver.equals(that.receiver);
    }

    @Override
    public int hashCode() {
        int result = sender.hashCode();
        result = 31 * result + receiver.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", sender.getName(), receiver.getName());
    }
}