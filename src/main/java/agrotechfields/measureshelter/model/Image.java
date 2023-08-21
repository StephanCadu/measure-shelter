package agrotechfields.measureshelter.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class Image {

  @MongoId
  private ObjectId id;

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
  public Image(ObjectId id, String name, byte[] bytes) {
    this.id = id;
    this.name = name;
    this.bytes = bytes;
    this.timestamp = LocalDateTime.now().atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
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
