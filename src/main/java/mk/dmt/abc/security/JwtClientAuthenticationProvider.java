//package mk.dmt.abc.security;
//
//import com.nimbusds.jose.JWSObject;
//import com.nimbusds.jose.util.JSONObjectUtils;
//import mk.dmt.abc.entity.CustomerEntity;
//import mk.dmt.abc.exception.CustomerNotFoundException;
//import mk.dmt.abc.exception.InvalidJwtTokenException;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//import mk.dmt.abc.security.model.CustomerPrincipal;
//import mk.dmt.abc.repository.CustomerRepository;
//
//import java.text.ParseException;
//import java.time.Instant;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Optional;
//
//@Component
//public class JwtClientAuthenticationProvider implements AuthenticationProvider {
//
//    private final CustomerRepository customerRepository;
//
//    public JwtClientAuthenticationProvider(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        JwtAuthenticationToken authToken = (JwtAuthenticationToken) authentication;
//        JWSObject jwt = authToken.getJwt();
//        String username = authToken.getPrincipal();
//
//        Optional<CustomerEntity> optApiClientEntity = customerRepository.findByUsername(username);
//        if (optApiClientEntity.isEmpty()) {
//            throw new CustomerNotFoundException(username);
//        }
//
//        String algorithm = jwt.getHeader().getAlgorithm().getName();
//        validateToken(jwt, algorithm);
//
//        return new UsernamePasswordAuthenticationToken(
//                new CustomerPrincipal(username),
//                null,
//                Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//
//    protected void validateToken(JWSObject jwt, String algorithm) {
//        Map<String, Object> jwtJsonObject = jwt.getPayload().toJSONObject();
//        long jwtExpiresAt;
//        String jwtAudience;
//        try {
//            jwtExpiresAt = JSONObjectUtils.getLong(jwtJsonObject, "exp");
//            jwtAudience = JSONObjectUtils.getString(jwtJsonObject, "aud");
//        } catch (ParseException e) {
//            throw new InvalidJwtTokenException("Failed to parse JWT payload", e);
//        }
//        // check expiration
//        if (Instant.ofEpochSecond(jwtExpiresAt).isBefore(Instant.now())) {
//            throw new BadCredentialsException("JWT token expired.");
//        }
//    }
//}
