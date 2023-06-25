package entities;

import java.util.Objects;

public class HashableString {
    private final String string;
    private int hash;
    private boolean hashIsZero;

    public HashableString(String string) {
        this.string = string;
        this.hash = 0;
        this.hashIsZero = false;
    }

    public String get() {
        return this.string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashableString that = (HashableString) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        if (hash != 0 || hashIsZero) return hash;

        for (byte v : string.getBytes()) {
            hash = (v & 0xff) + (hash << 6) + (hash << 16) + hash;
        }

        if (hash == 0) hashIsZero = true;
        return hash;
    }
}
