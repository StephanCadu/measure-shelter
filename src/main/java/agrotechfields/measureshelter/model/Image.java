package agrotechfields.measureshelter.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.data.annotation.Id;

public class Image {

  @Id
  private int id;

  private String name;

  private byte[] bytes;

  private LocalDateTime timestamp;

  /**
   * Default constructor.
   */
  public Image() {}

  /**
   * Constructor with args.
   * 
   * @param name Image name.
   * @param bytes Image bytes.
   */
  public Image(String name, byte[] bytes) {
    this.name = name;
    this.bytes = bytes;
    this.timestamp = LocalDateTime.now().atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
