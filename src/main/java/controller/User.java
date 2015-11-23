package controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by udbhavgoyal on 6/11/15.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    private String email;

    @Column(name = "firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    private String password;

   @Column(name="imageurl")
   private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public User(){


    }

    public User(String firstName, String lastName, String email, String password,String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

       public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}