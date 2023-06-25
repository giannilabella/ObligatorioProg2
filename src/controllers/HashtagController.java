package controllers;

import entities.Driver;
import entities.Hashtag;
import entities.Tweet;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.hashtable.MyHashtable;
import uy.edu.um.prog2.adt.hashtable.MyClosedHashingHashtable;

public class HashtagController {
    private static HashtagController INSTANCE;
    private final MyHashtable<String, Hashtag> hashtags;

    private HashtagController() {
        this.hashtags = new MyClosedHashingHashtable<>();
    }

    public static HashtagController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HashtagController();
        }

        return INSTANCE;
    }

    public Hashtag create(String hashtagText) {
        if (hashtags.containsKey(hashtagText)) return hashtags.get(hashtagText);

        Hashtag hashtag = new Hashtag(hashtags.size(), hashtagText);
        hashtags.put(hashtagText, hashtag);
        return hashtag;
    }

    public MyCollection<Hashtag> getHashtags() {
        return hashtags.values();
    }

    public int getHashtagsCount() {
        return hashtags.size();
    }

    public int getNumberOfDifferentHashtagsInADay(byte day, byte month, short year) {
        MyHashtable<Long, Object> differentHashtags = new MyClosedHashingHashtable<>(hashtags.size());

        TweetController tweetController = TweetController.getInstance();
        for (Tweet tweet: tweetController.getTweets()) {
            if (tweet.getDay() == day && tweet.getMonth() == month && tweet.getYear() == year) {
                for (Hashtag hashtag: tweet.getHashtags()) {
                    if (!differentHashtags.containsKey(hashtag.id())) {
                        differentHashtags.put(hashtag.id(), null);
                    }
                }
            }
        }

        return differentHashtags.size();
    }
}
