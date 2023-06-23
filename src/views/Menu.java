package views;

import controllers.DriverController;
import controllers.TweetController;

import java.nio.file.Path;

public class Menu {
    public static void main(String[] args) {
        DriverController driverController = DriverController.getInstance();
        DataLoader.loadDrivers(Path.of("drivers.txt"));

        TweetController tweetController = TweetController.getInstance();
        DataLoader.loadDataset(Path.of("f1_dataset.csv"));
    }
}
