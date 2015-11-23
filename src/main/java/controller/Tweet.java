package controller;


import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by udbhavgoyal on 17/11/15.
 */

@Entity
@Table(name = "tweet")
public class Tweet {

    public Tweet(){

    }

    public Tweet(String tweet, String email, Timestamp tweetTimestamp) {
        this.tweet = tweet;
        this.email = email;
        this.tweetTimestamp = tweetTimestamp;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String tweet;

    public long getId() {
        return id;
    }

    private String email;

    @Column(name="tweettimestamp")
    Timestamp tweetTimestamp;




}