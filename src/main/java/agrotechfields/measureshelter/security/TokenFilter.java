package agrotechfields.measureshelter.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import agrotechfields.measureshelter.model.User;

@Component
public class TokenFilter extends OncePerRequestFilter {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null) {
      String token = authHeader.replace("Bearer ", "");

      String subject = this.tokenService.getSubject(token);

      User user = (User) this.authenticationService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);
  }

}
