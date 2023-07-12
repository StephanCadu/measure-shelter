package agrotechfields.measureshelter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import agrotechfields.measureshelter.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

  @Query("{username:'?0'}")
  public User findByUsername(String username);
}
