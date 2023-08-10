package agrotechfields.measureshelter.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agrotechfields.measureshelter.dto.MeasureDto;
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
      // throw ObjectNotFoundException
    }

    return measureFound.get();
  }

  public List<Measure> findMeasuresByIsle(int isleId) {
    return this.measureRepository.findByIsleId(isleId);
  }

  public Measure saveMeasure(MeasureDto measureDto) {
    Optional<Isle> isle = isleRepository.findById(new ObjectId(measureDto.getIsleId()));

    if (isle.isEmpty()) {
      // throw ObjectNotFoundException
    }

    if (!isle.get().getStatus()) {
      // throw IsleNotWorkingException
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
