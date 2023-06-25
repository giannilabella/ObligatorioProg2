package models;

import java.util.Objects;

public class Tweet {
    private final int id;
    private final String content;
    private final byte day;
    private final byte month;
    private final short year;
    private final User user;
    private final Hashtag[] hashtags;

    public Tweet(int id, String content, byte day, byte month, short year, User user, Hashtag[] hashtags) {
        this.id = id;
        this.content = content;
        this.day = day;
        this.month = month;
        this.year = year;
        this.user = user;
        this.hashtags = hashtags;
    }

    public String getContent() {
        return content;
    }

    public byte getDay() {
        return day;
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

    public Hashtag[] getHashtags() {
        return hashtags;
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
