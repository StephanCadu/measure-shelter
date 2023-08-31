package agrotechfields.measureshelter.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import agrotechfields.measureshelter.utils.Direction;

@Document
public class Measure {

  @MongoId
  private ObjectId id;

  private ObjectId isleId;

  private double airTemperature;

  private double groundTemperature;

  private double airHumidity;

  private double groundHumidity;

  private double precipitation;

  private double solarRadiation;

  private double windSpeed;

  private Direction windDirection;

  private LocalDateTime timestamp;

  /**
   * Default constructor.
   */
  public Measure() {}

  /**
   * Constructor with args.
   * 
   * @param isleId Isle id.
   * @param airTemp Air temperature.
   * @param groundTemp Ground temperature.
   * @param airHmdt Air humidity.
   * @param groundHmdt Ground humidity.
   * @param precipitation Precipitation
   * @param solarRad Solar radiation.
   * @param windSpeed Wind speed.
   * @param direction Wind direction.
   */
  public Measure(ObjectId id, ObjectId isleId, double airTemp, double groundTemp, double airHmdt,
      double groundHmdt, double precipitation, double solarRad, double windSpeed,
      Direction direction) {
    this.id = id;
    this.isleId = isleId;
    this.airTemperature = airTemp;
    this.groundTemperature = groundTemp;
    this.airHumidity = airHmdt;
    this.groundHumidity = groundHmdt;
    this.precipitation = precipitation;
    this.solarRadiation = solarRad;
    this.windSpeed = windSpeed;
    this.windDirection = direction;
    this.timestamp = LocalDateTime.now().atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public ObjectId getIsleId() {
    return isleId;
  }

  public void setIsleId(ObjectId isleId) {
    this.isleId = isleId;
  }

  public double getAirTemperature() {
    return airTemperature;
  }

  public void setAirTemperature(double airTemperature) {
    this.airTemperature = airTemperature;
  }

  public double getGroundTemperature() {
    return groundTemperature;
  }

  public void setGroundTemperature(double groundTemperature) {
    this.groundTemperature = groundTemperature;
  }

  public double getAirHumidity() {
    return airHumidity;
  }

  public void setAirHumidity(double airHumidity) {
    this.airHumidity = airHumidity;
  }

  public double getGroundHumidity() {
    return groundHumidity;
  }

  public void setGroundHumidity(double groundHumidity) {
    this.groundHumidity = groundHumidity;
  }

  public double getPrecipitation() {
    return precipitation;
  }

  public void setPrecipitation(double precipitation) {
    this.precipitation = precipitation;
  }

  public double getSolarRadiation() {
    return solarRadiation;
  }

  public void setSolarRadiation(double solarRadiation) {
    this.solarRadiation = solarRadiation;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public Direction getWindDirection() {
    return windDirection;
  }

  public void setWindDirection(Direction windDirection) {
    this.windDirection = windDirection;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
