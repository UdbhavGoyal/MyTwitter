package controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by udbhavgoyal on 6/11/15.
 */

@RestController
public class RequestHandler {


    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailTest emailTest;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    DataHolder dataHolder;

    @Autowired
    FollowRepository followRepository;

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    private ResponseEntity registerUser(@RequestBody String json) {
        System.out.print("Details of users are " + json);

        JSONObject newUser = null;
        try {

            newUser = new JSONObject(json);

            User user = userRepository.findByEmail(newUser.getString("email"));

            if (user == null) {

                user = new User(newUser.getString("firstname"), newUser.getString("lastname"), newUser.getString("email"), newUser.getString("password"), "images/userprofileimage.jpg");
                userRepository.save(user);
                    dataHolder.setImageUrl("images/userprofileimage.jpg");
                Follow follow = new Follow(newUser.getString("email"), newUser.getString("email"));
                followRepository.save(follow);

                emailTest.send(newUser.getString("email"), newUser.getString("password"));
                return new ResponseEntity(json, HttpStatus.OK);

            }

        } catch (Exception e) {
            System.out.println("Error in registerUser method is " + e);
        }

        return new ResponseEntity(json, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity loginUser(@RequestBody String json) {
        System.out.println("Login Credentials : " + json);

        try {
            JSONObject loginUser = new JSONObject(json);
            User user = userRepository.findByEmail(loginUser.getString("username"));

            if (user != null) {
                if (loginUser.getString("username").equals(user.getEmail()) && loginUser.getString("password").equals(user.getPassword())) {
                    dataHolder.setEmail(loginUser.getString("username"));

                    dataHolder.setPassword(loginUser.getString("password"));
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", user.getFirstName());
                    jsonObject.put("imageurl", user.getImageUrl());
                    dataHolder.setImageUrl(user.getImageUrl());


                    return new ResponseEntity(jsonObject.toString(), HttpStatus.OK);
                }

            }
        } catch (Exception e) {

            System.out.println("Error is " + e);
        }
        return new ResponseEntity(json, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    private ResponseEntity changePassword(@RequestBody String json) {


        try {
            JSONObject pwd_details = new JSONObject(json);

            if (dataHolder.getPassword().equals(pwd_details.getString("password"))) {

                userRepository.changePassword(pwd_details.getString("newpassword"), dataHolder.getEmail());

                dataHolder.setPassword(pwd_details.getString("newpassword"));

                return new ResponseEntity(json, HttpStatus.OK);
            }

        } catch (Exception e) {
            System.out.print("Error in change password method " + e);
        }

        return new ResponseEntity(json, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/posttweet", method = RequestMethod.POST)
    private ResponseEntity postTweet(@RequestBody String json) {
        Tweet tweet;
        JSONObject userTweet = null;
        try {
            userTweet = new JSONObject(json);


            tweet = new Tweet(userTweet.getString("tweet"), dataHolder.getEmail(), new Timestamp(new Date().getTime()));

            tweetRepository.save(tweet);

        } catch (Exception e) {
            System.out.println("Error in postTweet method is " + e);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/displaynonfollowers", method = RequestMethod.POST)
    private ResponseEntity displayUnfollowersList() {
        JSONObject followUser = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {


            List userList = new ArrayList(userRepository.whoToFollow(dataHolder.getEmail()));

            for (int i = 0; i < userList.size(); i++) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("nonfollowerobject", userList.get(i));

                jsonArray.put(jsonObject);

            }
            followUser.put("nonfollowerslist", jsonArray);
            System.out.println(followUser);

        } catch (Exception e) {
            System.out.print("Error in display non followers handler is " + e);
        }

        return new ResponseEntity(followUser.toString(), HttpStatus.OK);

    }

    @RequestMapping(value = "/displayfollowers", method = RequestMethod.POST)
    private ResponseEntity displayFollowersList() {

        JSONObject unfollowUser = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            List followersList = new ArrayList(userRepository.whoToUnfollow(dataHolder.getEmail()));

            for (int i = 0; i < followersList.size(); i++) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("followerobject", followersList.get(i));

                jsonArray.put(jsonObject);

            }
            unfollowUser.put("followerslist", jsonArray);
        } catch (Exception e) {

            System.out.print("Error in display followers handler is " + e);
        }
        return new ResponseEntity(unfollowUser.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/followuser", method = RequestMethod.POST)
    private ResponseEntity followUser(@RequestBody String json) {

        Follow follow;
        System.out.print("Email in follow user method is" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);

            follow = new Follow(jsonObject.getString("email"), dataHolder.getEmail());
            followRepository.save(follow);

        } catch (Exception e) {
            System.out.println("Error in follow user method is " + e);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/unfollowuser", method = RequestMethod.POST)
    private ResponseEntity unfollowUser(@RequestBody String json) {
        Follow follow;

        try {
            JSONObject jsonObject = new JSONObject(json);

            followRepository.deleteFollowerRecord(dataHolder.getEmail(), jsonObject.getString("email"));

        } catch (Exception e) {
            System.out.print("Error in unfollow user method is " + e);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }


    @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
    public
    @ResponseBody
    void handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        if (!file.isEmpty()) {

            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/webapp/images/" + dataHolder.getEmail() + ".jpg"));
                stream.write(bytes);
                stream.close();
                userRepository.updateImage("../images/" + dataHolder.getEmail() + ".jpg", dataHolder.getEmail());
                dataHolder.setImageUrl("../images/" + dataHolder.getEmail()+ ".jpg");

                response.sendRedirect("/#/dashboard");

            } catch (Exception e) {

            }
        } else {

        }
    }


    @RequestMapping(value = "/displaytweets", method = RequestMethod.POST)
    public ResponseEntity returnTweets() {
        List<TweetTemplate> list = tweetRepository.getTweets(dataHolder.getEmail());
        return new ResponseEntity(list, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.POST)
    public ResponseEntity sendUserData() {

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("profileimage", dataHolder.getImageUrl());

        } catch (Exception e) {

        }
        return new ResponseEntity(jsonObject.toString(), HttpStatus.ACCEPTED);
    }


}
