package entities;

import java.util.Objects;

public class User {
    private final long id;
    private final String name;
    private final boolean isVerified;
    private int tweetsCount;

    public User(long id, String name, boolean isVerified) {
        this.id = id;
        this.name = name;
        this.isVerified = isVerified;
        this.tweetsCount = 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public int getTweetsCount() {
        return tweetsCount;
    }

    public void incrementTweetCount() {
        tweetsCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
