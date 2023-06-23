package views;

import controllers.DriverController;
import entities.User;
import controllers.TweetController;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DataLoader {
    private static final Pattern driverPattern = Pattern.compile("\\S+ .+");
    private static final Pattern datePattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");

    public static void loadDrivers(Path filePath) {
        System.out.println("Reading drivers file...");
        long startTime = System.nanoTime();

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
        lines.filter(line -> driverPattern.matcher(line).matches()).forEach(driverController::create);

        try {
            reader.close();
        } catch (IOException exception) {
            System.out.println("Error closing file reader!");
            exception.printStackTrace();
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Reading drivers file took " + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds!");
        System.out.println("Loaded " + driverController.getDriversCount() + " drivers!");
    }

    public static void loadDataset(Path filePath) {
        System.out.println("Reading dataset file...");
        long startTime = System.nanoTime();

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

        TweetController tweetController = TweetController.getInstance();
        for (CSVRecord record: records) {
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

            loadTweet(tweetController, tweetId, tweetContent, tweetDate, null);
        }

        try {
            reader.close();
        } catch (IOException exception) {
            System.out.println("Error closing file reader!");
            exception.printStackTrace();
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Reading dataset file took " + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds!");
        System.out.println("Loaded " + tweetController.getTweetCount() + " tweets!");
    }

    private static void loadTweet(TweetController tweetController, String rawId, String rawContent, String rawDate, User user) {
        long id = 0;
        try {
            id = Long.parseLong(rawId);
        } catch (NumberFormatException exception) {
            System.out.println("Not loading tweet of id " + rawId);
            return;
        }

        String content = rawContent;

        Matcher dateMatcher = datePattern.matcher(rawDate);
        if (!dateMatcher.matches()) {
            System.out.println("Not loading tweet of id " + rawId + ", invalid date: " + rawDate);
            return;
        };
        byte month = Byte.parseByte(rawDate.substring(5, 7));
        short year = Short.parseShort(rawDate.substring(0, 4));

        tweetController.create(id, content, month, year, user);
    }
}
