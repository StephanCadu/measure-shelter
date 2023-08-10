package agrotechfields.measureshelter.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agrotechfields.measureshelter.dto.IsleDto;
import agrotechfields.measureshelter.model.Isle;
import agrotechfields.measureshelter.repository.IsleRepository;

@Service
public class IsleService {

  @Autowired
  private IsleRepository isleRepository;

  public List<Isle> findIsles() {
    return this.isleRepository.findAll();
  }

  public Isle findIsleById(ObjectId isleId) {
    Optional<Isle> isleFound = this.isleRepository.findById(isleId);

    if (isleFound.isEmpty()) {
      // throw ObjectNotFoundException
    }

    return isleFound.get();
  }

  public Isle findIsleByName(String name) {
    Optional<Isle> isleFound = this.isleRepository.findByName(name);

    if (isleFound.isEmpty()) {
      // throw ObjectNotFoundException
    }

    return isleFound.get();
  }

  public Isle saveIsle(IsleDto isleDto) {
    Optional<Isle> isleFound = this.isleRepository.findByName(isleDto.getName());

    if (isleFound.isPresent()) {
      // throw ObjectAlreadyExistsException
    }

    Isle isle = new Isle(null, isleDto.getName(), isleDto.getAltitude(), isleDto.getLatitude(),
        isleDto.getLongitude(), isleDto.getStatus());

    return this.isleRepository.insert(isle);
  }

  public void deleteIsle(ObjectId isleId) {
    Isle isle = this.findIsleById(isleId);
    this.isleRepository.delete(isle);
  }

  public Isle updateIsle(ObjectId isleId, IsleDto isleDto) {
    Isle isle = this.findIsleById(isleId);

    isle.setAltitude(isleDto.getAltitude());
    isle.setLatitude(isleDto.getLatitude());
    isle.setLongitude(isleDto.getLongitude());
    isle.setName(isleDto.getName());
    isle.setStatus(isleDto.getStatus());

    return this.isleRepository.save(isle);
  }

  public boolean updateIsleStatus(ObjectId isleId, boolean status) {
    Isle isle = this.findIsleById(isleId);

    isle.setStatus(status);

    return isle.getStatus();
  }
}
