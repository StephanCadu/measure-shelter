package agrotechfields.measureshelter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.Image;

public interface ImageRepository extends MongoRepository<Image, Integer> {

  @Query("{name:'?0'}")
  public Image findByName(String name);
}
