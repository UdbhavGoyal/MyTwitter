package controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by udbhavgoyal on 18/11/15.
 */

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {

    @Override
    List<Follow> findAll();

    @Modifying
    @Transactional
    @Query(value="delete from Follow f where f.email=?1 AND f.followUser=?2")
    void deleteFollowerRecord(String email, String followUser);
}

