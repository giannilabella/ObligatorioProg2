package controllers;

import models.Hashtag;
import models.Tweet;
import models.User;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.MySinglyLinkedList;

import java.util.regex.Pattern;

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

    public void create(int id, String content, byte day, byte month, short year, User user, Hashtag[] hashtags) {
        Tweet tweet = new Tweet(id, content, day, month, year, user, hashtags);
        tweets.add(tweet);
    }

    public MyCollection<Tweet> getTweets() {
        return tweets;
    }

    public int getTweetCount() {
        return tweets.size();
    }

    public int getNumberOfTweetsWithString(String searchString, boolean caseSensitive) {
        int numberOfTweets = 0;
        Pattern searchPattern = caseSensitive ? Pattern.compile(searchString) : Pattern.compile(searchString, Pattern.CASE_INSENSITIVE);
        for (Tweet tweet: tweets) {
            if (searchPattern.matcher(tweet.getContent()).find()) {
                numberOfTweets++;
            }
        }
        return numberOfTweets;
    }
}
