package agrotechfields.measureshelter.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import agrotechfields.measureshelter.dto.LoginDto;
import agrotechfields.measureshelter.dto.UserDto;
import agrotechfields.measureshelter.model.User;
import agrotechfields.measureshelter.security.AuthenticationService;
import agrotechfields.measureshelter.security.TokenService;
import agrotechfields.measureshelter.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @GetMapping
  public ResponseEntity<List<User>> findUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.findUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findUserById(@PathVariable("id") String userId) {
    User user = this.userService.findUserById(new ObjectId(userId));
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<UserDetails> findUserByName(@PathVariable("name") String username) {
    UserDetails user = this.authenticationService.loadUserByUsername(username);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping
  public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
    User user = this.userService.saveUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
    UsernamePasswordAuthenticationToken userAuthToken =
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

    Authentication auth = this.authenticationManager.authenticate(userAuthToken);

    User user = (User) auth.getPrincipal();

    String token = this.tokenService.generateToken(user);

    return ResponseEntity.status(HttpStatus.OK).body(token);
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ADMIN")
  public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
    this.userService.deleteUser(new ObjectId(userId));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") int userId,
      @RequestBody UserDto userDto) {
    User user = this.userService.updateUser(userId, userDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
  }
}
