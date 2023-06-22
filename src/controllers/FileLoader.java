package controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader {
    public static void LoadDataset(TweetController tweetController) {
        System.out.println("Reading dataset file...");
        try {
            Reader reader = Files.newBufferedReader(Paths.get("f1_dataset.csv"));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record: records) {
                if (record.getRecordNumber() == 1) continue;

                long id = Long.parseLong(record.get(0));
                String content = record.get(10);
                String date = record.get(9);

                tweetController.create(id, content, ((byte) 23), ((short) 2023), null);
            }
            reader.close();
            System.out.println("Reading dataset file finished!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
