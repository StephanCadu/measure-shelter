package agrotechfields.measureshelter.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.Measure;

public interface MeasureRepository extends MongoRepository<Measure, Integer> {

  @Query("{isleId:'?0'}")
  public List<Measure> findByIsleId(int isleId);
}
