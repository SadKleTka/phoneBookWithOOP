public class phoneBookName {
    private final String name;
    public phoneBookName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof phoneBookName))
            return false;
        phoneBookName other = (phoneBookName) o;
        return name.equalsIgnoreCase(other.name);
    }

    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
