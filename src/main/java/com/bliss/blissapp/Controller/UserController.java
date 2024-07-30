package com.bliss.blissapp.Controller;

import com.bliss.blissapp.Model.Location;
import com.bliss.blissapp.Model.User;
import com.bliss.blissapp.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
  private final UserService userService;

  @PostMapping("/set/location")
  public ResponseEntity<User> setLocation(@RequestBody Location location) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    return new ResponseEntity<>(userService.updateUserLocation(username, location), HttpStatus.OK);
  }
}
