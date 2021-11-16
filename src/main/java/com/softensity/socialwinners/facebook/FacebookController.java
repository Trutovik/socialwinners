package com.softensity.socialwinners.facebook;

import com.softensity.socialwinners.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class FacebookController {

  @Autowired
  private FacebookService facebookService;

  @GetMapping
  public List<String> welcome() {
    List<String> urls = new ArrayList<>();
    urls.add("http://localhost:8080/generateFacebookAuthorizeUrl");
    urls.add("http://localhost:8080/getUserData");
    return urls;
  }

  @GetMapping("/generateFacebookAuthorizeUrl")
  public String generateFacebookAuthorizeUrl() {
    return facebookService.generateFacebookAuthorizeUrl();
  }

  @GetMapping("/facebook")
  public void generateFacebookAccessToken(@RequestParam("code") String code) {
    facebookService.generateFacebookAccessToken(code);
  }

  @GetMapping("/getUserData")
  public String getUserData() {
    return facebookService.getUserData();
  }
}
