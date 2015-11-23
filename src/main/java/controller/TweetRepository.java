package controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by udbhavgoyal on 17/11/15.
 */

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long>{

    @Query(value="select u.email, u.firstName, t.tweetTimestamp, t.tweet from User u, Tweet t where t.email IN (select f.followUser from Follow f where f.email=:email) and u.email=t.email order by t.tweetTimestamp desc ")
    List getTweets(@Param("email") String email);

}