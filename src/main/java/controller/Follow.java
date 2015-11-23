package controller;

import javax.persistence.*;

/**
 * Created by udbhavgoyal on 18/11/15.
 */

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String email;

    @Column(name="followuser")
   private String followUser;

    public long getId() {
        return id;
    }

       public Follow(String followUser, String email) {
        this.followUser = followUser;
        this.email = email;
    }
}