package agrotechfields.measureshelter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Isle {

  @Id
  private int id;

  private String name;

  private double longitude;

  private double latitude;

  private double altitude;

  private boolean status;

  /**
   * Default constructor.
   */
  public Isle() {}

  /**
   * Constructor with args.
   * 
   * @param name Isle name.
   * @param longitude Isle longitude.
   * @param latitude Isle latitude.
   * @param altitude Isle altitude.
   * @param status Isle initial status.
   */
  public Isle(String name, double longitude, double latitude, double altitude, boolean status) {
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
    this.altitude = altitude;
    this.status = status;
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

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

}
