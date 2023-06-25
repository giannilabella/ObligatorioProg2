package controllers;

import models.Driver;
import models.Hashtag;
import models.MentionedDriver;
import models.Tweet;
import uy.edu.um.prog2.adt.collection.MyCollection;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyMaxHeap;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.MySinglyLinkedList;

import java.util.Arrays;
import java.util.regex.Pattern;

public class DriverController {
    private static DriverController INSTANCE;
    private final MyList<Driver> drivers;

    private DriverController() {
        this.drivers = new MySinglyLinkedList<>();
    }

    public static DriverController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverController();
        }

        return INSTANCE;
    }

    public void create(String fullName) {
        String[] splitFullName = fullName.trim().split(" +", 2);
        String name = splitFullName[0];
        String surname = splitFullName[1];
        String[] splitSurname = surname.split(" +");

        fullName = name + " " + String.join(" ", splitSurname);
        String regex = name + "|" + String.join(" *", splitSurname);
        Pattern driverPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Driver driver = new Driver(drivers.size(), fullName, driverPattern);
        drivers.add(driver);
    }

    public int getDriversCount() {
        return drivers.size();
    }

    public MyCollection<MentionedDriver> getMostMentionedDriversByMonthAndYear(byte month, short year, int numberOfDrivers) {
        int[] mentionsCount = new int[drivers.size()];
        Arrays.fill(mentionsCount, 0);

        TweetController tweetController = TweetController.getInstance();
        for (Tweet tweet: tweetController.getTweets()) {
            if (tweet.getMonth() == month && tweet.getYear() == year) {
                for (Driver driver: drivers) {
                    if (driver.isMentioned(tweet.getContent())) {
                        mentionsCount[driver.getId()]++;
                    } else for (Hashtag hashtag: tweet.getHashtags()) {
                        if (driver.isMentioned(hashtag.hashtagText())) {
                            mentionsCount[driver.getId()]++;
                            break;
                        }
                    }
                }
            }
        }

        MyHeap<MentionedDriver> mentionedDriverHeap = new MyMaxHeap<>(drivers.size());
        for (Driver driver: drivers) {
            mentionedDriverHeap.add(new MentionedDriver(driver, mentionsCount[driver.getId()]));
        }

        MyList<MentionedDriver> mentionedDrivers = new MySinglyLinkedList<>();
        for (int i = 0; i < numberOfDrivers; i++) {
            MentionedDriver mentionedDriver = mentionedDriverHeap.remove();
            if (mentionedDriver == null) break;
            mentionedDrivers.add(mentionedDriver);
        }

        return  mentionedDrivers;
    }
}
