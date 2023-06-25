package models;

import java.util.Objects;

public record Hashtag(int id, String hashtagText) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(hashtagText, hashtag.hashtagText);
    }
}
