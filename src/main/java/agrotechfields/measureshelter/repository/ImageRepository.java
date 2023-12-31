package agrotechfields.measureshelter.repository;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.Image;

public interface ImageRepository extends MongoRepository<Image, ObjectId> {

  @Query("{name:'?0'}")
  public Optional<Image> findByName(String name);
}
