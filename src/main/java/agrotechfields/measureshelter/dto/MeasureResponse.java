package agrotechfields.measureshelter.dto;

import agrotechfields.measureshelter.model.Measure;

public class MeasureResponse extends MeasureDto {

  private String id;

  private String isleId;

  public MeasureResponse(Measure measure) {
    this.id = measure.getId().toString();
    this.isleId = measure.getIsleId().toString();
    this.setAirHumidity(measure.getAirHumidity());
    this.setGroundHumidity(measure.getGroundHumidity());
    this.setAirTemperature(measure.getAirTemperature());
    this.setGroundTemperature(measure.getGroundTemperature());
    this.setPrecipitation(measure.getPrecipitation());
    this.setSolarRadiation(measure.getSolarRadiation());
    this.setTimestamp(measure.getTimestamp());
    this.setWindDirection(measure.getWindDirection());
    this.setWindSpeed(measure.getWindSpeed());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIsleId() {
    return isleId;
  }

  public void setIsleId(String isleId) {
    this.isleId = isleId;
  }

}
