package agrotechfields.measureshelter.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Isle {

  @MongoId
  private ObjectId id;

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
  public Isle(ObjectId id, String name, double longitude, double latitude, double altitude,
      boolean status) {
    this.id = id;
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
    this.altitude = altitude;
    this.status = status;
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
