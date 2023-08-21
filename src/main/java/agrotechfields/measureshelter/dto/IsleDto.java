package agrotechfields.measureshelter.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IsleDto {

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @Min(value = -180)
  @Max(value = 180)
  private double longitude;

  @NotNull
  @Min(value = -90)
  @Max(value = 90)
  private double latitude;

  @NotNull
  @Min(value = 0)
  private double altitude;

  @NotNull
  private boolean status;

  /**
   * Default constructor.
   */
  public IsleDto() {}

  /**
   * Constructor with args.
   * 
   * @param name Isle name.
   * @param longitude Isle longitude.
   * @param latitude Isle latitude.
   * @param altitude Isle altitude
   * @param status Isle status.
   */
  public IsleDto(String name, double longitude, double latitude, double altitude, boolean status) {
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
    this.altitude = altitude;
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getAltitude() {
    return altitude;
  }

  public void setAltitude(double altitude) {
    this.altitude = altitude;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
