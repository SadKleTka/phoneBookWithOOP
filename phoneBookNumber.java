public class phoneBookNumber {
    String number;
    public phoneBookNumber(String number) {
        this.number = number;
    }
    public String toString() {
        return number;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof phoneBookNumber))
            return false;
        phoneBookNumber other = (phoneBookNumber) o;
        return number.equals(other.number);
    }

    public int hashCode() {
        return number.hashCode();
    }
}
