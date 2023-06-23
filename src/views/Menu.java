package views;

import controllers.TweetController;

public class Menu {
    public static void main(String[] args) {
        TweetController tweetController = new TweetController();
        FileLoader.loadDataset(tweetController);
    }
}
