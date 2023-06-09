package com.example.tweet.integration.mocks;

import com.example.tweet.client.ProfileServiceClient;
import com.example.tweet.client.response.ProfileResponse;
import org.mockito.Mockito;

import java.time.LocalDate;

import static com.example.tweet.integration.constants.GlobalConstants.*;

public class ProfileClientMock {

    public static void setupProfileClientResponse(ProfileServiceClient profileServiceClient) {
        ProfileResponse response = ProfileResponse.builder()
                .username(USERNAME.getConstant())
                .email(EMAIL.getConstant())
                .joinDate(LocalDate.MIN)
                .bio("some bio")
                .location("some location")
                .birthDate(LocalDate.MIN)
                .website("some website")
                .avatarUrl("some avatar url")
                .bannerUrl("some banner url")
                .build();

        Mockito.doReturn(ID.getConstant())
                .when(profileServiceClient)
                .getProfileIdByLoggedInUser(EMAIL.getConstant());

        Mockito.doReturn(response)
                .when(profileServiceClient)
                .getProfileById(ID.getConstant());
    }
}
