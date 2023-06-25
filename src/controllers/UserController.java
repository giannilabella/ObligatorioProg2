package controllers;

import entities.HashableString;
import entities.Tweet;
import entities.User;
import entities.UserComparingMethods;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyMaxHeap;
import uy.edu.um.prog2.adt.hashtable.MyHashtable;
import uy.edu.um.prog2.adt.hashtable.MyClosedHashingHashtable;

public class UserController {
    private static UserController INSTANCE;
    private final MyHashtable<HashableString, User> users;
    private boolean areUserTweetsCounted;

    private UserController() {
        this.users = new MyClosedHashingHashtable<>();
        this.areUserTweetsCounted = false;
    }

    public static UserController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserController();
        }

        return INSTANCE;
    }

    public User create(String name, int favoritesCount, boolean isVerified) {
        HashableString hashableName = new HashableString(name);
        if (users.containsKey(hashableName)) {
            User user = users.get(hashableName);
            if (favoritesCount > user.getFavoritesCount()) {
                user.setFavoritesCount(favoritesCount);
            }
            return user;
        }
        User user = new User(users.size(), name, favoritesCount, isVerified);
        users.put(hashableName, user);
        return user;
    }

    public int getUsersCount() {
        return users.size();
    }

    public MyCollection<User> getMostActiveUsers(int numberOfUsers) {
        if (!areUserTweetsCounted) {
            TweetController tweetController = TweetController.getInstance();
            for (Tweet tweet: tweetController.getTweets()) {
                tweet.getUser().incrementTweetCount();
            }
            areUserTweetsCounted = true;
        }
        MyCollection<User> mostActiveUsers = users.values();

        MyHeap<User> mostActiveUsersHeap = new MyMaxHeap<>();
        for (User user: mostActiveUsers) {
            user.setComparingMethod(UserComparingMethods.TWEETS_COUNT);
            mostActiveUsersHeap.add(user);
        }

        mostActiveUsers.clear();
        for (int i = 0; i < numberOfUsers; i++) {
            User user = mostActiveUsersHeap.remove();
            if (user == null) break;
            mostActiveUsers.add(user);
        }
        mostActiveUsersHeap.clear();
        return mostActiveUsers;
    }

    public MyCollection<User> getMostFavoritedUsers(int numberOfUsers) {
        MyHeap<User> mostFavoritedUsersHeap = new MyMaxHeap<>();
        MyCollection<User> mostFavoritedUsers = users.values();
        for (User user: mostFavoritedUsers) {
            user.setComparingMethod(UserComparingMethods.FAVORITES_COUNT);
            mostFavoritedUsersHeap.add(user);
        }

        mostFavoritedUsers.clear();
        for (int i = 0; i < numberOfUsers; i++) {
            User user = mostFavoritedUsersHeap.remove();
            if (user == null) break;
            mostFavoritedUsers.add(user);
        }
        return mostFavoritedUsers;
    }
}
