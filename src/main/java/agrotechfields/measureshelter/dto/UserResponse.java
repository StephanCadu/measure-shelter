package agrotechfields.measureshelter.dto;

import agrotechfields.measureshelter.model.User;

public class UserResponse extends UserDto {

  private String id;

  public UserResponse(User user) {
    this.id = user.getId().toString();
    this.setUsername(user.getUsername());
    this.setPassword(user.getPassword());
    this.setRole(user.getRole());
    this.setActive(user.isActive());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
