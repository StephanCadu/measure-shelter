package agrotechfields.measureshelter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.Isle;

public interface IsleRepository extends MongoRepository<Isle, Integer> {

  @Query("{name:'?0'}")
  public Isle findByName(String name);
}
