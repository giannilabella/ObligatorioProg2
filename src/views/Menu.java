package views;

import controllers.FileLoader;
import controllers.TweetController;

public class Menu {
    public static void main(String[] args) {
        TweetController tweetController = new TweetController();
        FileLoader.LoadDataset(tweetController);
        tweetController.printAll();
    }
}
