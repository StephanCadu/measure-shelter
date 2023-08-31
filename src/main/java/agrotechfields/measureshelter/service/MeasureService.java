package agrotechfields.measureshelter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agrotechfields.measureshelter.dto.MeasureDto;
import agrotechfields.measureshelter.exception.IsleNotWorkingException;
import agrotechfields.measureshelter.exception.ObjectNotFoundException;
import agrotechfields.measureshelter.model.Isle;
import agrotechfields.measureshelter.model.Measure;
import agrotechfields.measureshelter.repository.IsleRepository;
import agrotechfields.measureshelter.repository.MeasureRepository;

@Service
public class MeasureService {

  @Autowired
  private MeasureRepository measureRepository;

  @Autowired
  private IsleRepository isleRepository;

  public List<Measure> findMeasures() {
    return this.measureRepository.findAll();
  }

  public Measure findMeasureById(ObjectId measureId) {
    Optional<Measure> measureFound = this.measureRepository.findById(measureId);

    if (measureFound.isEmpty()) {
      throw new ObjectNotFoundException("Measure with ID: " + measureId + " not found.");
    }

    return measureFound.get();
  }

  public List<Measure> findMeasuresByIsle(String isleId) {
    List<Measure> filteredMeasures = this.measureRepository.findAll().stream()
        .filter(measure -> measure.getIsleId().toString().equals(isleId))
        .collect(Collectors.toList());
    return filteredMeasures;
  }

  public Measure saveMeasure(MeasureDto measureDto) {
    Optional<Isle> isle = isleRepository.findById(new ObjectId(measureDto.getIsleId()));

    if (isle.isEmpty()) {
      throw new ObjectNotFoundException("Isle with ID: " + measureDto.getIsleId() + " not found.");
    }

    if (!isle.get().getStatus()) {
      throw new IsleNotWorkingException(
          "Isle with ID: " + measureDto.getIsleId() + " is not working.");
    }

    Measure measure = new Measure(null, new ObjectId(measureDto.getIsleId()),
        measureDto.getAirTemperature(), measureDto.getGroundTemperature(),
        measureDto.getAirHumidity(), measureDto.getGroundHumidity(), measureDto.getPrecipitation(),
        measureDto.getSolarRadiation(), measureDto.getWindSpeed(), measureDto.getWindDirection());

    return this.measureRepository.insert(measure);
  }

  public void deleteMeasure(ObjectId measureId) {
    this.measureRepository.deleteById(measureId);
  }

  public Measure updateMeasure(ObjectId measureId, MeasureDto measureDto) {
    Measure measure = this.findMeasureById(measureId);

    measure.setAirTemperature(measureDto.getAirTemperature());
    measure.setGroundTemperature(measureDto.getGroundTemperature());
    measure.setAirHumidity(measureDto.getAirHumidity());
    measure.setGroundHumidity(measureDto.getGroundHumidity());
    measure.setIsleId(new ObjectId(measureDto.getIsleId()));
    measure.setPrecipitation(measureDto.getPrecipitation());
    measure.setSolarRadiation(measureDto.getSolarRadiation());
    measure.setWindDirection(measureDto.getWindDirection());
    measure.setWindSpeed(measureDto.getWindSpeed());

    return this.measureRepository.save(measure);
  }
}
