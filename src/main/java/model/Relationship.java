package model;

public class Relationship {

    private Person primary;

    private Person secondary;

    public Relationship(Person primary, Person secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public Person getPrimary() {
        return primary;
    }

    public Person getSecondary() {
        return secondary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relationship that = (Relationship) o;

        if (primary != null ? !primary.equals(that.primary) : that.primary != null) return false;
        return secondary != null ? secondary.equals(that.secondary) : that.secondary == null;
    }

    @Override
    public int hashCode() {
        int result = primary != null ? primary.hashCode() : 0;
        result = 31 * result + (secondary != null ? secondary.hashCode() : 0);
        return result;
    }
}
