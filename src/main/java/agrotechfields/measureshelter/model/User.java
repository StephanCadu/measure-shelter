package agrotechfields.measureshelter.model;

import org.springframework.data.annotation.Id;
import agrotechfields.measureshelter.utils.Role;

public class User {

  @Id
  private int id;

  private String username;

  private String password;

  private Role role;

  private boolean active;

  /**
   * Default constructor.
   */
  public User() {
    this.role = Role.USER;
    this.active = true;
  }

  /**
   * Constructor with args.
   * 
   * @param username User name.
   * @param password User password.
   * @param role User role.
   */
  public User(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.active = true;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

}
