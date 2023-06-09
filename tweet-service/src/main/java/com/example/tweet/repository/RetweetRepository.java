package com.example.tweet.repository;

import com.example.tweet.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {

    Integer countAllByParentTweetId(Long parentTweetId);
    Optional<Retweet> findByParentTweetIdAndProfileId(Long parentTweetId, String profileId);
    List<Retweet> findAllByProfileId(String profileId);
}
