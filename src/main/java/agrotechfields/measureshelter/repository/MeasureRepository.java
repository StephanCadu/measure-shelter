package agrotechfields.measureshelter.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.Measure;

public interface MeasureRepository extends MongoRepository<Measure, ObjectId> {

  @Query("{isleId:'?0'}")
  public List<Measure> findByIsleId(ObjectId isleId);
}
