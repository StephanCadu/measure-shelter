package agrotechfields.measureshelter.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import agrotechfields.measureshelter.model.User;

@Service
public class TokenService {

  public String generateToken(User user) {
    return JWT.create().withIssuer("measure-shelter").withSubject(user.getUsername())
        .withClaim("id", user.getId().toString())
        .withExpiresAt(
            Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"))))
        .sign(Algorithm.HMAC256("nemteconto"));
  }

  public String getSubject(String token) {
    return JWT.require(Algorithm.HMAC256("nemteconto")).withIssuer("measure-shelter").build()
        .verify(token).getSubject();
  }

}
