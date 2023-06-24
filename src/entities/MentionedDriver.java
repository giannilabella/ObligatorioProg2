package entities;

import java.util.Objects;

public record MentionedDriver(Driver driver, int mentionsCount) implements Comparable<MentionedDriver> {
    public int getId() {
        return driver.getId();
    }

    public String getName() {
        return driver.getName();
    }

    public String getSurname() {
        return driver.getSurname();
    }

    public String getFullName() {
        return driver.getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MentionedDriver that = (MentionedDriver) o;
        return Objects.equals(driver, that.driver);
    }

    @Override
    public int compareTo(MentionedDriver o) {
        return Integer.compare(this.mentionsCount, o.mentionsCount);
    }
}
