package agrotechfields.measureshelter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import agrotechfields.measureshelter.dto.UserDto;
import agrotechfields.measureshelter.model.User;
import agrotechfields.measureshelter.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> findUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.findUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findUserById(@PathVariable("id") int userId) {
    User user = this.userService.findUserById(userId);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<User> findUserByName(@PathVariable("name") String username) {
    User user = this.userService.findUserByName(username);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping
  public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
    User user = this.userService.saveUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
    this.userService.deleteUser(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") int userId,
      @RequestBody UserDto userDto) {
    User user = this.userService.updateUser(userId, userDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
  }
}
