package views;

import entities.User;
import controllers.TweetController;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLoader {
    private static final Pattern datePattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");

    public static void loadDataset(TweetController tweetController) {
        System.out.println("Reading dataset file...");
        long startTime = System.nanoTime();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("f1_dataset.csv"));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record: records) {
                if (record.getRecordNumber() == 1) continue;

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
            reader.close();
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Reading dataset file took " + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds!");
            System.out.println("Loaded " + tweetController.getTweetCount() + " tweets!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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
