package com.example.profile.service;

import com.example.profile.entity.Follow;
import com.example.profile.entity.Profile;
import com.example.profile.repository.FollowRepository;
import com.example.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final ProfileRepository profileRepository;

    public boolean follow(String followeeId, String loggedInUser) {
        return profileRepository.findByEmail(loggedInUser)
                .map(Profile::getId)
                .filter(followerId -> !isFollowed(followeeId, loggedInUser))
                .map(followerId -> Follow.builder()
                        .followerProfile(profileRepository.findById(followerId).orElseThrow())
                        .followeeProfile(profileRepository.findById(followeeId).orElseThrow())
                        .followDateTime(LocalDateTime.now())
                        .build())
                .map(followRepository::save)
                .isPresent();
    }

    public boolean unfollow(String followeeId, String loggedInUser) {
        return profileRepository.findByEmail(loggedInUser)
                .map(Profile::getId)
                .filter(followerId -> isFollowed(followeeId, loggedInUser))
                .map(followerId ->
                        followRepository.deleteByFollowerProfile_IdAndFolloweeProfile_Id(followerId, followeeId))
                .isPresent();
    }

    public List<Profile> getFollowers(String profileId) {
        return followRepository.findAllByFolloweeProfile_Id(profileId)
                .stream()
                .map(Follow::getFollowerProfile)
                .toList();
    }

    public List<Profile> getFollowees(String profileId) {
        return followRepository.findAllByFollowerProfile_Id(profileId)
                .stream()
                .map(Follow::getFolloweeProfile)
                .toList();
    }

    public boolean isFollowed(String followeeId, String loggedInUser) {
        return profileRepository.findByEmail(loggedInUser)
                .map(Profile::getId)
                .map(followerId -> followRepository.existsByFollowerProfile_IdAndFolloweeProfile_Id(followerId, followeeId))
                .orElse(false);
    }
}
