package agrotechfields.measureshelter.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

  @Query("{username:'?0'}")
  public Optional<User> findByUsername(String username);
}
