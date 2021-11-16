package com.softensity.socialwinners.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface FacebookService {

  public String generateFacebookAuthorizeUrl();
  public void generateFacebookAccessToken(String code);
  public String getUserData();
}
