package entities;

import java.util.Objects;

public record Hashtag(long id, String hashtagText) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashTag = (Hashtag) o;
        return id == hashTag.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
