package agrotechfields.measureshelter.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.data.annotation.Id;
import agrotechfields.measureshelter.utils.Direction;

public class Measure {

  @Id
  private int id;

  private int isleId;

  private double airTemperature;

  private double groundTemperature;

  private double airHumidity;

  private double groundHumidity;

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
   * @param windSpeed Wind speed.
   * @param direction Wind direction.
   */
  public Measure(int isleId, double airTemp, double groundTemp, double airHmdt, double groundHmdt,
      double windSpeed, Direction direction) {
    this.isleId = isleId;
    this.airTemperature = airTemp;
    this.groundTemperature = groundTemp;
    this.airHumidity = airHmdt;
    this.groundHumidity = groundHmdt;
    this.windSpeed = windSpeed;
    this.windDirection = direction;
    this.timestamp = LocalDateTime.now().atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIsleId() {
    return isleId;
  }

  public void setIsleId(int isleId) {
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