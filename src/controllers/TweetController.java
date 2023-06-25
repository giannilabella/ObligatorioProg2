package controllers;

import entities.Hashtag;
import entities.Tweet;
import entities.User;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.MySinglyLinkedList;

public class TweetController {
    private static TweetController INSTANCE;
    private final MyList<Tweet> tweets;

    private TweetController() {
        this.tweets = new MySinglyLinkedList<>();
    }

    public static TweetController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TweetController();
        }

        return INSTANCE;
    }

    public void create(long id, String content, byte day, byte month, short year, User user, Hashtag[] hashtags) {
        Tweet tweet = new Tweet(id, content, day, month, year, user, hashtags);
        tweets.add(tweet);
    }

    public MyCollection<Tweet> getTweets() {
        return tweets;
    }

    public int getTweetCount() {
        return tweets.size();
    }
}
