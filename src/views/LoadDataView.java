package views;

import controllers.*;

import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LoadDataView {
    public static void loadData() {
        String driversFilename = "drivers.txt";
        String completeDatasetFilename = "f1_dataset.csv";
        String reducedDatasetFilename = "f1_dataset_test.csv";

        System.out.println("========= Choose the dataset file to load =========");
        System.out.println("---------------------------------------------------");
        System.out.println("1 - Load complete dataset (" + completeDatasetFilename + ")");
        System.out.println("2 - Load reduced test dataset (" + reducedDatasetFilename + ")");
        System.out.println("---------------------------------------------------");
        System.out.print("Enter the selected option: ");

        Scanner scanner = new Scanner(System.in);
        final int selection = scanner.nextInt();

        String datasetFilename;
        if (selection == 1) {
            System.out.println("============ Loading complete dataset! ============\n");
            datasetFilename = completeDatasetFilename;
        } else {
            System.out.println("========== Loading reduced test dataset! ==========\n");
            datasetFilename = reducedDatasetFilename;
        }

        loadDrivers(driversFilename);
        loadDataset(datasetFilename);
    }

    private static void loadDrivers(String filename) {
        System.out.println("Loading drivers from " + filename);
        System.out.println("Reading file...");
        final long startTime = System.nanoTime();

        DataLoaderController.loadDrivers(Path.of(filename));
        final long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Reading drivers file took " + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds!");

        DriverController driverController = DriverController.getInstance();
        System.out.println("Loaded " + driverController.getDriversCount() + " drivers!\n");
    }

    private static void loadDataset(String filename) {
        System.out.println("Loading dataset from " + filename);
        System.out.println("Reading file...");
        final long startTime = System.nanoTime();

        DataLoaderController.loadDataset(Path.of(filename));
        final long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Reading dataset file took " + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds!");

        UserController userController = UserController.getInstance();
        System.out.println("Loaded " + userController.getUsersCount() + " users!");

        HashtagController hashtagController = HashtagController.getInstance();
        System.out.println("Loaded " + hashtagController.getHashtagsCount() + " hashtags!");

        TweetController tweetController = TweetController.getInstance();
        System.out.println("Loaded " + tweetController.getTweetCount() + " tweets!\n");
    }
}
