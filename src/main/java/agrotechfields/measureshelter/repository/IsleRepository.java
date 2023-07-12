package agrotechfields.measureshelter.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.Isle;

public interface IsleRepository extends MongoRepository<Isle, Integer> {

  @Query("{name:'?0'}")
  public Optional<Isle> findByName(String name);
}
