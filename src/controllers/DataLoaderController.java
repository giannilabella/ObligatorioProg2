package controllers;

import models.Hashtag;
import models.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DataLoaderController {
    private static final Pattern driverNamePattern = Pattern.compile("\\S+ .+");
    private static final Pattern hashtagListPattern = Pattern.compile("\\[('[^\\s']+')+(, *'[^\\s']+')*]");
    private static final Pattern datePattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");

    public static void loadDrivers(Path filePath) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(filePath);
        } catch (IOException exception) {
            System.out.println("Error reading drivers file!");
            exception.printStackTrace();
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(reader);
        Stream<String> lines = bufferedReader.lines();

        DriverController driverController = DriverController.getInstance();
        lines.filter(line -> driverNamePattern.matcher(line).matches()).forEach(driverController::create);

        try {
            reader.close();
        } catch (IOException exception) {
            System.out.println("Error closing file reader!");
            exception.printStackTrace();
        }
    }

    public static void loadDataset(Path filePath) {
        Reader reader;
        Iterable<CSVRecord> records;
        try {
            reader = Files.newBufferedReader(filePath);
            records = CSVFormat.DEFAULT.parse(reader);
        } catch (IOException exception) {
            System.out.println("Error reading dataset file!");
            exception.printStackTrace();
            return;
        }

        Iterator<CSVRecord> iterator = records.iterator();
        if (iterator.hasNext()) iterator.next();

        try {
            for (CSVRecord record : records) {
                String tweetId = record.get(0);
                String userName = record.get(1);
                String userLocation = record.get(2);
                String userDescription = record.get(3);
                String userCreationDate = record.get(4);
                String userFollowersCount = record.get(5);
                String userFriendsCount = record.get(6);
                String userFavoritesCount = record.get(7);
                String userIsVerified = record.get(8);
                String tweetDate = record.get(9);
                String tweetContent = record.get(10);
                String tweetHashtags = record.get(11);
                String tweetSource = record.get(12);
                String tweetIsRetweeted = record.get(13);

                User user = loadUser(userName, userFavoritesCount, userIsVerified);
                Hashtag[] hashtags = loadHashtags(tweetHashtags);
                loadTweet(tweetId, tweetContent, tweetDate, user, hashtags);
            }
        } catch (UncheckedIOException exception) {
            System.out.println("Error reading dataset records!");
            exception.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException exception) {
            System.out.println("Error closing file reader!");
            exception.printStackTrace();
        }
    }

    private static User loadUser(String rawName, String rawFavoritesCount, String rawIsVerified) {
        int favoritesCount;
        try {
            favoritesCount = Integer.parseInt(rawFavoritesCount.split("\\.")[0]);
        } catch (NumberFormatException exception) {
            System.out.println("User of name \"" + rawName + "\" has invalid number of favorites: " + rawFavoritesCount + ", so it is being set to 0");
            favoritesCount = 0;
        }
        boolean isVerified = Boolean.parseBoolean(rawIsVerified);

        UserController userController = UserController.getInstance();
        return userController.create(rawName, favoritesCount, isVerified);
    }

    private static Hashtag[] loadHashtags(String rawHashtags) {
        boolean noHashtags = rawHashtags.equals("");
        boolean validHashtagList = hashtagListPattern.matcher(rawHashtags).matches();
        if (!validHashtagList && !noHashtags) {
            System.out.println("Not loading invalid hashtags: " + rawHashtags);
            return new Hashtag[0];
        }

        if (noHashtags) return new Hashtag[0];

        String[] hashtagsTexts = rawHashtags.substring(2, rawHashtags.length() - 2).split("', *'");
        Hashtag[] hashtags = new Hashtag[hashtagsTexts.length];

        HashtagController hashtagController = HashtagController.getInstance();
        for (int i = 0; i < hashtagsTexts.length; i++) {
            hashtags[i] = hashtagController.create(hashtagsTexts[i].toUpperCase());
        }
        return hashtags;
    }

    private static void loadTweet(String rawId, String rawContent, String rawDate, User user, Hashtag[] hashtags) {
        int id;
        try {
            id = Integer.parseInt(rawId);
        } catch (NumberFormatException exception) {
            System.out.println("Not loading tweet with invalid id: " + rawId);
            return;
        }

        boolean validDate = datePattern.matcher(rawDate).matches();
        if (!validDate) {
            System.out.println("Not loading tweet of id " + rawId + ", invalid date: " + rawDate);
            return;
        }
        byte day = Byte.parseByte(rawDate.substring(8, 10));
        byte month = Byte.parseByte(rawDate.substring(5, 7));
        short year = Short.parseShort(rawDate.substring(0, 4));

        TweetController tweetController = TweetController.getInstance();
        tweetController.create(id, rawContent, day, month, year, user, hashtags);
    }
}
