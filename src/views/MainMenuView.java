package views;

import controllers.DriverController;
import controllers.UserController;
import entities.MentionedDriver;
import entities.User;
import uy.edu.um.prog2.adt.collection.MyCollection;

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
                case 1:
                    System.out.println("========== Listing most mentioned drivers! ==========\n");
                    getMostMentionedDrivers(10);
                    break;
                case 2:
                    System.out.println("========== Listing users with more tweets! ==========\n");
                    getMostActiveUsers(15);
                    break;
                case 3:
                    System.out.println("======= Getting number of different hashtags! =======\n");
                    getNumberOfDifferentHashtags();
                    break;
                case 4:
                    System.out.println("======= Getting most used hashtag in the day! =======\n");
                    getMostUsedHashtag();
                    break;
                case 5:
                    System.out.println("=========== Listing most favorited users! ===========\n");
                    getMostFavoritedUsers(7);
                    break;
                case 6:
                    System.out.println("============= Getting number of tweets! =============\n");
                    getNumberOfTweetWithWordOrPhrase();
                    break;
                case 0:
                    System.out.println("====================== Exiting! =====================\n");
                    break;
                default:
                    System.out.println("================== Invalid option! ==================\n");
                    break;
            }
        } 
    }

    private static void getMostMentionedDrivers(int numberOfDrivers) {
        Scanner scanner = new Scanner(System.in);

        DriverController driverController = DriverController.getInstance();
        MyCollection<MentionedDriver> mostMentionedDrivers = driverController.getMostMentionedDriversByMonthAndYear((byte) 8, (short) 2021, numberOfDrivers);
        for (MentionedDriver driver: mostMentionedDrivers) {
            System.out.println("Driver " + driver.getFullName() + " is mentioned " + driver.mentionsCount() + " times");
        }

        System.out.println("\nPress enter to go back to the menu");
        scanner.nextLine();
    }
    
    private static void getMostActiveUsers(int numberOfUsers) {
        Scanner scanner = new Scanner(System.in);

        UserController userController = UserController.getInstance();
        MyCollection<User> mostActiveUsers = userController.getMostActiveUsers(numberOfUsers);
        for (User user: mostActiveUsers) {
            System.out.println("User \"" + user.getName() + "\" has tweeted " + user.getTweetsCount() + " times and " + (user.isVerified() ? "is verified" : "is not verified"));
        }

        System.out.println("\nPress enter to go back to the menu");
        scanner.nextLine();
    }
    
    private static void getNumberOfDifferentHashtags() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPress enter to go back to the menu");
        scanner.nextLine();
    }
    
    private static void getMostUsedHashtag() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPress enter to go back to the menu");
        scanner.nextLine();
    }
    
    private static void getMostFavoritedUsers(int numberOfUsers) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPress enter to go back to the menu");
        scanner.nextLine();
    }

    private static void getNumberOfTweetWithWordOrPhrase() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPress enter to go back to the menu");
        scanner.nextLine();
    }
}
