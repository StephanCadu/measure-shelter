package agrotechfields.measureshelter.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import agrotechfields.measureshelter.dto.UserDto;
import agrotechfields.measureshelter.exception.ObjectAlreadyExistsException;
import agrotechfields.measureshelter.exception.ObjectNotFoundException;
import agrotechfields.measureshelter.model.User;
import agrotechfields.measureshelter.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<User> findUsers() {
    return this.userRepository.findAll();
  }

  public User findUserById(ObjectId userId) throws ObjectNotFoundException {
    Optional<User> userFound = this.userRepository.findById(userId);

    if (userFound.isEmpty()) {
      throw new ObjectNotFoundException("User with ID: " + userId + " not found.");
    }

    return userFound.get();
  }

  public User saveUser(UserDto userDto) {
    Optional<User> userFound = this.userRepository.findByUsername(userDto.getUsername());

    if (userFound.isPresent()) {
      throw new ObjectAlreadyExistsException(
          "User with Name: " + userDto.getUsername() + " already exists.");
    }

    String encodedPass = passwordEncoder.encode(userDto.getPassword());

    User user = new User(null, userDto.getUsername(), encodedPass, userDto.getRole());

    return this.userRepository.insert(user);
  }

  public void deleteUser(ObjectId userId) {
    this.userRepository.deleteById(userId);
  }

  public User updateUser(String userId, UserDto userDto) {
    Optional<User> userFound = this.userRepository.findById(new ObjectId(userId));

    if (userFound.isEmpty()) {
      throw new ObjectNotFoundException("User with ID: " + userId + " not found.");
    }

    User user = userFound.get();
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    user.setRole(userDto.getRole());
    user.setActive(userDto.getActive());

    return this.userRepository.save(user);
  }
}
