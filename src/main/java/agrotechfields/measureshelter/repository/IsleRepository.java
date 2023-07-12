package agrotechfields.measureshelter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import agrotechfields.measureshelter.model.Isle;

public interface IsleRepository extends MongoRepository<Isle, Integer> {
  public Isle findByName(String name);
}
