package controllers;

import entities.HashableString;
import entities.Hashtag;
import entities.Tweet;
import entities.UsedHashtag;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.hashtable.MyHashtable;
import uy.edu.um.prog2.adt.hashtable.MyClosedHashingHashtable;

public class HashtagController {
    private static HashtagController INSTANCE;
    private final MyClosedHashingHashtable<HashableString, Hashtag> hashtags;

    private HashtagController() {
        this.hashtags = new MyClosedHashingHashtable<>(65000, 0.85f);
    }

    public static HashtagController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HashtagController();
        }

        return INSTANCE;
    }

    public Hashtag create(String hashtagText) {
        HashableString hashableHashtagText = new HashableString(hashtagText);
        if (hashtags.containsKey(hashableHashtagText)) return hashtags.get(hashableHashtagText);

        Hashtag hashtag = new Hashtag(hashtags.size(), hashtagText);
        hashtags.put(hashableHashtagText, hashtag);
        return hashtag;
    }

    public int getHashtagsCount() {
        return hashtags.size();
    }

    public int getNumberOfDifferentHashtagsInADay(byte day, byte month, short year) {
        MyHashtable<Integer, Hashtag> differentHashtags = new MyClosedHashingHashtable<>(hashtags.size());

        TweetController tweetController = TweetController.getInstance();
        for (Tweet tweet: tweetController.getTweets()) {
            if (tweet.getDay() == day && tweet.getMonth() == month && tweet.getYear() == year) {
                for (Hashtag hashtag: tweet.getHashtags()) {
                    if (!differentHashtags.containsKey(hashtag.id())) {
                        differentHashtags.put(hashtag.id(), hashtag);
                    }
                }
            }
        }

        return differentHashtags.size();
    }

    public UsedHashtag getMostUsedHashtagInTheDay(byte day, byte month, short year) {
        MyHashtable<Integer, UsedHashtag> usedHashtags = new MyClosedHashingHashtable<>(hashtags.size(), 1);
        Hashtag ignoredHashtag = hashtags.get(new HashableString("F1"));

        TweetController tweetController = TweetController.getInstance();
        for (Tweet tweet: tweetController.getTweets()) {
            if (tweet.getDay() == day && tweet.getMonth() == month && tweet.getYear() == year) {
                for (Hashtag hashtag: tweet.getHashtags()) {
                    if (hashtag.id() != ignoredHashtag.id()) {
                        UsedHashtag usedHashtag;
                        if (!usedHashtags.containsKey(hashtag.id())) {
                            usedHashtag = new UsedHashtag(hashtag);
                            usedHashtags.put(hashtag.id(), usedHashtag);
                        } else {
                            usedHashtag = usedHashtags.get(hashtag.id());
                        }
                        usedHashtag.incrementUsesCount();
                    }
                }
            }
        }

        MyCollection<UsedHashtag> usedHashtagsList = usedHashtags.values();
        UsedHashtag mostUsedHashtags = null;
        for (UsedHashtag usedHashtag: usedHashtagsList) {
            if (
                    (usedHashtag != null && mostUsedHashtags == null) ||
                    (usedHashtag != null && usedHashtag.compareTo(mostUsedHashtags) > 0)
            ) {
                mostUsedHashtags = usedHashtag;
            }
        }
        return mostUsedHashtags;
    }
}
