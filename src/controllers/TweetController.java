package controllers;

import models.Tweet;
import models.User;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.MySinglyLinkedList;

public class TweetController {
    private MyList<Tweet> tweets;

    public TweetController() {
        this.tweets = new MySinglyLinkedList<>();
    }

    public void create(long id, String content, byte month, short year, User user) {
        Tweet tweet = new Tweet(id, content, month, year, user);
        tweets.add(tweet);
    }

    public void printAll() {
        for (Tweet tweet: tweets) {
            System.out.println(tweet.getId());
        }
    }
}
