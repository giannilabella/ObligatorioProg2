package controllers;

import entities.Driver;
import entities.MentionedDriver;
import entities.Tweet;
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
        int id = drivers.size();

        fullName = fullName.trim();
        String[] splitName = fullName.split(" ", 2);
        String name = splitName[0];
        String surname = splitName[1];

        String regex = name + "|" + String.join(" *", surname.split(" "));
        Pattern driverPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Driver driver = new Driver(id, name, surname, fullName, driverPattern);
        drivers.add(driver);
    }

    public MyCollection<Driver> getDrivers() {
        return drivers;
    }

    public int getDriversCount() {
        return drivers.size();
    }

    public MyCollection<MentionedDriver> getMostMentionedDriversByMonthAndYear(byte month, short year, int numberOfDrivers) {
        int[] mentionCount = new int[drivers.size()];
        Arrays.fill(mentionCount, 0);

        TweetController tweetController = TweetController.getInstance();
        for (Tweet tweet: tweetController.getTweets()) {
            if (tweet.getMonth() == month && tweet.getYear() == year) {
                for (Driver driver: drivers) {
                    if (driver.isMentioned(tweet.getContent())) {
                        mentionCount[driver.getId()]++;
                    }
                }
            }
        }

        MyHeap<MentionedDriver> mentionedDriverHeap = new MyMaxHeap<>(drivers.size());
        for (Driver driver: drivers) {
            mentionedDriverHeap.add(new MentionedDriver(driver, mentionCount[driver.getId()]));
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
