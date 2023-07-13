package agrotechfields.measureshelter.dto;

import javax.validation.constraints.NotNull;
import agrotechfields.measureshelter.utils.Role;

public class UserDto extends LoginDto {

  @NotNull
  private boolean active;

  public UserDto() {
    super();
    this.active = true;
  }

  public UserDto(String username, String password, Role role, boolean active) {
    this.setUsername(username);
    this.setPassword(password);
    this.setRole(role);
    this.active = active;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
