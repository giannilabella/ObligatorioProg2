package views;

import controllers.DriverController;
import controllers.HashtagController;
import controllers.UserController;
import entities.MentionedDriver;
import entities.User;
import uy.edu.um.prog2.adt.collection.MyCollection;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuView {
    public static void main(String[] args) {
        LoadDataView.loadData();

        int selection = -1;
        while (selection != 0) {
            System.out.println("====== Choose the one of the following reports ======");
            System.out.println("-----------------------------------------------------");
            System.out.println("1 - Top 10 most mentioned drivers in a month and year");
            System.out.println("2 - Top 15 users with more tweets");
            System.out.println("3 - Number of different hashtags for a given day");
            System.out.println("4 - Most used hashtag for a given day (excluding #F1)");
            System.out.println("5 - Top 7 users with most favorites");
            System.out.println("6 - Number of tweets with a specific word or phrase");
            System.out.println("0 - Exit");
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter the selected option: ");

            Scanner scanner = new Scanner(System.in);
            selection = scanner.nextInt();
            switch (selection) {
                case 1 -> {
                    System.out.println("========== Listing most mentioned drivers! ==========\n");
                    getMostMentionedDrivers(10);
                }
                case 2 -> {
                    System.out.println("========== Listing users with more tweets! ==========\n");
                    getMostActiveUsers(15);
                }
                case 3 -> {
                    System.out.println("======= Getting number of different hashtags! =======\n");
                    getNumberOfDifferentHashtags();
                }
                case 4 -> {
                    System.out.println("======= Getting most used hashtag in the day! =======\n");
                    getMostUsedHashtag();
                }
                case 5 -> {
                    System.out.println("=========== Listing most favorited users! ===========\n");
                    getMostFavoritedUsers(7);
                }
                case 6 -> {
                    System.out.println("============= Getting number of tweets! =============\n");
                    getNumberOfTweetWithWordOrPhrase();
                }
                case 0 -> System.out.println("====================== Exiting! =====================\n");
                default -> System.out.println("================== Invalid option! ==================\n");
            }
        } 
    }

    private static void getMostMentionedDrivers(int numberOfDrivers) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insert the month: ");
        byte month;
        try {
            month = scanner.nextByte();
        } catch (InputMismatchException ignored) {
            System.out.println("Invalid month!");
            waitForEnter();
            return;
        }

        System.out.print("Insert the year: ");
        short year;
        try {
            year = scanner.nextShort();
        } catch (InputMismatchException ignored) {
            System.out.println("Invalid year!");
            waitForEnter();
            return;
        }

        System.out.println();

        DriverController driverController = DriverController.getInstance();
        MyCollection<MentionedDriver> mostMentionedDrivers = driverController.getMostMentionedDriversByMonthAndYear(month, year, numberOfDrivers);
        for (MentionedDriver driver: mostMentionedDrivers) {
            System.out.println("Driver " + driver.getFullName() + " is mentioned " + driver.mentionsCount() + " times");
        }

        waitForEnter();
    }
    
    private static void getMostActiveUsers(int numberOfUsers) {
        UserController userController = UserController.getInstance();
        MyCollection<User> mostActiveUsers = userController.getMostActiveUsers(numberOfUsers);
        for (User user: mostActiveUsers) {
            System.out.println("User \"" + user.getName() + "\" has tweeted " + user.getTweetsCount() + " times and " + (user.isVerified() ? "is verified" : "is not verified"));
        }

        waitForEnter();
    }
    
    private static void getNumberOfDifferentHashtags() {
        System.out.print("Insert the date (yyyy-mm-dd): ");
        String date;
        try {
            Scanner scanner = new Scanner(System.in);
            date = scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        } catch (InputMismatchException ignored) {
            System.out.println("Invalid date format!");
            waitForEnter();
            return;
        }

        byte day = Byte.parseByte(date.substring(8, 10));
        byte month = Byte.parseByte(date.substring(5, 7));
        short year = Short.parseShort(date.substring(0, 4));

        HashtagController hashtagController = HashtagController.getInstance();
        int numberOfDifferentHashtags = hashtagController.getNumberOfDifferentHashtagsInADay(day, month, year);
        System.out.println("In the day " + date + ", " + numberOfDifferentHashtags + " different hashtags were used");

        waitForEnter();
    }
    
    private static void getMostUsedHashtag() {
        System.out.print("Insert the date (yyyy-mm-dd): ");
        String date;
        try {
            Scanner scanner = new Scanner(System.in);
            date = scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        } catch (InputMismatchException ignored) {
            System.out.println("Invalid date format!");
            waitForEnter();
            return;
        }

        byte day = Byte.parseByte(date.substring(8, 10));
        byte month = Byte.parseByte(date.substring(5, 7));
        short year = Short.parseShort(date.substring(0, 4));

        // TODO: list most used hashtags

        waitForEnter();
    }
    
    private static void getMostFavoritedUsers(int numberOfUsers) {
        UserController userController = UserController.getInstance();
        MyCollection<User> mostFavoritedUsers = userController.getMostFavoritedUsers(numberOfUsers);
        for (User user: mostFavoritedUsers) {
            System.out.println("User \"" + user.getName() + "\" has " + user.getFavoritesCount() + " favorites");
        }

        waitForEnter();
    }

    private static void getNumberOfTweetWithWordOrPhrase() {
        Scanner scanner = new Scanner(System.in);

        // TODO: get word or phrase and get number of tweets with it

        waitForEnter();
    }

    private static void waitForEnter() {
        System.out.println("\nPress enter to go back to the menu");
        try {
            System.in.read();
        } catch (IOException ignored) {}
    }
}
