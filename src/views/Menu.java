package views;

import controllers.DataLoaderController;
import controllers.DriverController;
import controllers.TweetController;
import entities.MentionedDriver;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class Menu {
    public static void main(String[] args) {
        loadData();

        DriverController driverController = DriverController.getInstance();
        for (MentionedDriver driver: driverController.getMostMentionedDriversByMonthAndYear((byte) 8, (short) 2021, 10)) {
            System.out.println("Driver " + driver.getId() + " - " + driver.getFullName() + " is mentioned " + driver.mentionCount() + " times");
        }
    }

    private static void loadData() {
        DriverController driverController = DriverController.getInstance();
        TweetController tweetController = TweetController.getInstance();

        System.out.println("Reading drivers file...");
        long startTime = System.nanoTime();
        DataLoaderController.loadDrivers(Path.of("drivers.txt"));
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Reading drivers file took " + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds!");
        System.out.println("Loaded " + driverController.getDriversCount() + " drivers!\n");

        System.out.println("Reading dataset file...");
        startTime = System.nanoTime();
        DataLoaderController.loadDataset(Path.of("f1_dataset.csv"));
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Reading dataset file took " + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds!");
        System.out.println("Loaded " + tweetController.getTweetCount() + " tweets!\n");
    }
}
