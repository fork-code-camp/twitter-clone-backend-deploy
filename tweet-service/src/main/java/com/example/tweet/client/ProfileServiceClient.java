package com.example.tweet.client;

import com.example.tweet.client.response.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "${PROFILE_SERVICE_URI:http://localhost}")
public interface ProfileServiceClient {

    @GetMapping("/api/v1/profiles/id/{email}")
    String getProfileIdByLoggedInUser(@PathVariable String email);

    @GetMapping("/api/v1/profiles/{id}")
    ProfileResponse getProfileById(@PathVariable String id);
}
