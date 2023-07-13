package agrotechfields.measureshelter.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ImageDto {

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotEmpty
  private byte[] bytes;

  /**
   * Default constructor.
   */
  public ImageDto() {}

  /**
   * Constructor with args.
   * 
   * @param name Image name.
   * @param bytes Image bytes.
   */
  public ImageDto(String name, byte[] bytes) {
    this.name = name;
    this.bytes = bytes;
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
}
