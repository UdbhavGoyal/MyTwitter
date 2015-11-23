package controller;

import java.sql.Timestamp;

/**
 * Created by udbhavgoyal on 20/11/15.
 */
public class TweetTemplate {

    String email;

    String firstName;

    String tweet;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    Timestamp tweetTimestamp;

    public Timestamp getTweetTimestamp() {
        return tweetTimestamp;
    }

    public void setTweetTimestamp(Timestamp tweetTimestamp) {
        this.tweetTimestamp = tweetTimestamp;
    }
}
