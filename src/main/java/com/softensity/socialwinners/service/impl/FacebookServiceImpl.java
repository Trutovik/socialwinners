package com.softensity.socialwinners.service.impl;

import com.softensity.socialwinners.service.FacebookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImpl implements FacebookService {

  private String accessToken;

  @Value("${spring.social.facebook.app-id}")
  private String facebookAppId;

  @Value("${spring.social.facebook.app-secret}")
  private String facebookSecret;

  private FacebookConnectionFactory createConnection() {
    return new FacebookConnectionFactory(facebookAppId, facebookSecret);
  }

  @Override
  public String generateFacebookAuthorizeUrl() {
    OAuth2Parameters params = new OAuth2Parameters();
    params.setRedirectUri("http://localhost:8080/facebook");
    params.setScope("email");
    return createConnection().getOAuthOperations().buildAuthenticateUrl(params);
  }

  @Override
  public void generateFacebookAccessToken(String code) {
    accessToken = createConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null).getAccessToken();
  }

  @Override
  public String getUserData() {
    Facebook facebook = new FacebookTemplate(accessToken);
    String[] fields = {"id", "first_name", "name", "email", "birthday", "gender", "age_range", "hometown", "inspirational_people"};
    return facebook.fetchObject("me", String.class, fields);
  }
}
