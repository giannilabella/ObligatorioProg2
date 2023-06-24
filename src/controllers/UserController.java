package controllers;

import entities.User;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.hashtable.MyClosedHashingHashtable;
import uy.edu.um.prog2.adt.hashtable.MyHashtable;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.MySinglyLinkedList;

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
}
