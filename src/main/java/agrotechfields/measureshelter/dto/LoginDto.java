package agrotechfields.measureshelter.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import agrotechfields.measureshelter.utils.Role;

public class LoginDto {

  @NotNull
  @NotBlank
  private String username;

  @NotNull
  @NotBlank
  @Size(min = 6)
  private String password;

  private Role role;

  /**
   * Default constructor.
   */
  public LoginDto() {}

  /**
   * Constructor with args.
   * 
   * @param username User name.
   * @param password User password.
   */
  public LoginDto(String username, String password) {
    this.username = username;
    this.password = password;
    this.role = Role.USER;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
