package entities;

import java.util.Objects;

public class UsedHashtag implements Comparable<UsedHashtag> {
    private final Hashtag hashtag;
    private int usesCount;

    public UsedHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
        this.usesCount = 0;
    }

    public String getHashtagText() {
        return hashtag.hashtagText();
    }

    public int getUsesCount() {
        return usesCount;
    }

    public void incrementUsesCount() {
        usesCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsedHashtag that = (UsedHashtag) o;
        return Objects.equals(hashtag.id(), that.hashtag.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashtag.id());
    }

    @Override
    public int compareTo(UsedHashtag o) {
        return Integer.compare(this.usesCount, o.usesCount);
    }
}
