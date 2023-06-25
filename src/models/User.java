package models;

public class User implements Comparable<User> {
    private final int id;
    private final String name;
    private int favoritesCount;
    private final boolean isVerified;
    private int tweetsCount;
    private UserComparingMethods comparingMethod;

    public User(int id, String name, int favoritesCount, boolean isVerified) {
        this.id = id;
        this.name = name;
        this.favoritesCount = favoritesCount;
        this.isVerified = isVerified;
        this.tweetsCount = 0;
    }

    public String getName() {
        return name;
    }

    public long getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
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

    public void setComparingMethod(UserComparingMethods comparingMethod) {
        this.comparingMethod = comparingMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int compareTo(User o) {
        int result = 0;
        if (comparingMethod == UserComparingMethods.TWEETS_COUNT) {
            result = Integer.compare(this.tweetsCount, o.tweetsCount);
        } else if (comparingMethod == UserComparingMethods.FAVORITES_COUNT) {
            result = Integer.compare(this.favoritesCount, o.favoritesCount);
        }
        return result;
    }
}
