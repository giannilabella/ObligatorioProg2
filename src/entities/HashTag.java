package entities;

import java.util.Objects;

public class HashTag {
    private final long id;
    private final String text;

    public HashTag(long id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return id == hashTag.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
