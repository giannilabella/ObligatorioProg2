package controllers;

import entities.Tweet;
import entities.User;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyMaxHeap;
import uy.edu.um.prog2.adt.hashtable.MyHashtable;
import uy.edu.um.prog2.adt.hashtable.MyClosedHashingHashtable;

public class UserController {
    private static UserController INSTANCE;
    private final MyHashtable<String, User> users;
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

    public User create(String name, boolean isVerified) {
        if (users.containsKey(name)) return users.get(name);

        User user = new User(users.size(), name, isVerified);
        users.put(name, user);
        return user;
    }

    public MyCollection<User> getUsers() {
        return users.values();
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
        mostActiveUsersHeap.addAll(mostActiveUsers);

        mostActiveUsers.clear();
        for (int i = 0; i < numberOfUsers; i++) {
            User user = mostActiveUsersHeap.remove();
            if (user == null) break;
            mostActiveUsers.add(user);
        }
        return mostActiveUsers;
    }
}
