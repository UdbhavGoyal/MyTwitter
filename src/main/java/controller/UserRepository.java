package controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by udbhavgoyal on 9/11/15.
 */

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    User findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.password=?1 where u.email=?2")
    void changePassword(String newPassword, String email);


    @Query("select u.firstName,u.email from User u where u.email NOT IN (select f.followUser from Follow f where f.email=:email) And u.email <> :email")
    List<User> whoToFollow(@Param("email") String email);

    @Query("select u.firstName,u.email from User u where u.email IN (select f.followUser from Follow f where f.email=:email AND NOT (f.email=:email AND f.followUser=:email))")

    List<User> whoToUnfollow(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.imageUrl=?1 where u.email=?2")
    void updateImage(String imageUrl, String email);


}