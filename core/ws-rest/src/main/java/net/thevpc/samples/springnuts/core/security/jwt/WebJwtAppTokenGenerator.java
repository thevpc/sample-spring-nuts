package net.thevpc.samples.springnuts.core.security.jwt;

import net.thevpc.samples.springnuts.core.service.impl.security.UserDetailsImpl;
import net.thevpc.samples.springnuts.core.service.impl.service.AppTokenGenerator;
import net.thevpc.samples.springnuts.core.service.impl.service.ConfService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import net.thevpc.nuts.util.NLiteral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class WebJwtAppTokenGenerator implements AppTokenGenerator {
  private static final Logger logger = LoggerFactory.getLogger(WebJwtAppTokenGenerator.class);
  @Autowired
  private ConfService confService;
//  @Value("${app.jwt.secret}")
//  private String jwtSecret;
//
//  @Value("${app.jwt.expiration-seconds}")
//  private int jwtExpirationSeconds;

  public String createToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    int jwtExpirationSeconds= NLiteral.of(confService.getConfValue("security.jwt","expirationSeconds").orNull())
            .asInt().orElse(3600);
    return Jwts.builder()
        .setSubject((userPrincipal.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationSeconds *1000))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();
  }
  
  private Key key() {
    String secret= NLiteral.of(confService.getConfValue("security.jwt","secret").orNull())
            .asString().orElse("jwt-is-awesome#");
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
