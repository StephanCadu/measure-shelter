package agrotechfields.measureshelter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agrotechfields.measureshelter.dto.LoginDto;
import agrotechfields.measureshelter.dto.UserDto;
import agrotechfields.measureshelter.model.User;
import agrotechfields.measureshelter.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findUsers() {
    return this.userRepository.findAll();
  }

  public User findUserById(int userId) {
    Optional<User> userFound = this.userRepository.findById(userId);

    if (userFound.isEmpty()) {
      // throw ObjectNotFoundException
    }

    return userFound.get();
  }

  public User findUserByName(String username) {
    Optional<User> userFound = this.userRepository.findByUsername(username);

    if (userFound.isEmpty()) {
      // throw ObjectNotFoundException
    }

    return userFound.get();
  }

  public User saveUser(LoginDto loginDto) {
    Optional<User> userFound = this.userRepository.findByUsername(loginDto.getUsername());

    if (userFound.isPresent()) {
      // throw ObjectAlreadyExistsException
    }

    User user = new User();
    user.setUsername(loginDto.getUsername());
    user.setPassword(loginDto.getPassword());

    return this.userRepository.insert(user);
  }

  public void deleteUser(int userId) {
    this.userRepository.deleteById(userId);
  }

  public User updateUser(int userId, UserDto userDto) {
    Optional<User> userFound = this.userRepository.findByUsername(userDto.getUsername());

    if (userFound.isEmpty()) {
      // throw ObjectNotFoundException
    }

    User user = userFound.get();
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    user.setRole(userDto.getRole());
    user.setActive(userDto.getActive());

    return this.userRepository.save(user);
  }
}
