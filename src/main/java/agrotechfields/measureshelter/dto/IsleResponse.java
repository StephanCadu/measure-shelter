package agrotechfields.measureshelter.dto;

import agrotechfields.measureshelter.model.Isle;

public class IsleResponse extends IsleDto {

  private String id;

  public IsleResponse(Isle isle) {
    this.id = isle.getId().toString();
    this.setName(isle.getName());
    this.setAltitude(isle.getAltitude());
    this.setLatitude(isle.getLatitude());
    this.setLongitude(isle.getLongitude());
    this.setStatus(isle.getStatus());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
