package models;

import java.util.Objects;

public class Tweet {
    private final long id;
    private final String content;
    private final byte month;
    private final short year;
    private final User user;

    public Tweet(long id, String content, byte month, short year, User user) {
        this.id = id;
        this.content = content;
        this.month = month;
        this.year = year;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return id == tweet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
