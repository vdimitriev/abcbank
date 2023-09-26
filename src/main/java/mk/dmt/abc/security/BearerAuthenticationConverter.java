//package mk.dmt.abc.security;
//
//import com.nimbusds.jose.JWSObject;
//import com.nimbusds.jose.util.JSONObjectUtils;
//import mk.dmt.abc.exception.InvalidJwtTokenException;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.security.authentication.AuthenticationDetailsSource;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationConverter;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.ParseException;
//import java.util.Map;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//
///**
// * The {@code BearerAuthenticationConverter} class converts from a {@link HttpServletRequest} to a {@link JwtAuthenticationToken}.
// */
//@Component
//public class BearerAuthenticationConverter implements AuthenticationConverter {
//
//    public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";
//
//    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
//
//    public BearerAuthenticationConverter() {
//        this(new WebAuthenticationDetailsSource());
//    }
//
//    public BearerAuthenticationConverter(
//            AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource) {
//        super();
//        this.authenticationDetailsSource = authenticationDetailsSource;
//    }
//
//    @Override
//    public Authentication convert(HttpServletRequest request) {
//        String header = request.getHeader(AUTHORIZATION);
//        if (header == null) {
//            return null;
//        }
//
//        if (!StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BEARER)
//                || header.length() <= AUTHENTICATION_SCHEME_BEARER.length() + 1
//                || header.charAt(AUTHENTICATION_SCHEME_BEARER.length()) != ' ') {
//            return null;
//        }
//
//        String token = header.substring(AUTHENTICATION_SCHEME_BEARER.length() + 1);
//        JWSObject jwsToken = parseToken(token);
//        String clientId = getTokenClientId(jwsToken);
//        JwtAuthenticationToken bearerToken = new JwtAuthenticationToken(clientId, jwsToken);
//        Object details = this.authenticationDetailsSource.buildDetails(request);
//        bearerToken.setDetails(details);
//        return bearerToken;
//    }
//
//    public AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> getAuthenticationDetailsSource() {
//        return authenticationDetailsSource;
//    }
//
//    public void setAuthenticationDetailsSource(
//            AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource) {
//        this.authenticationDetailsSource = authenticationDetailsSource;
//    }
//
//    protected JWSObject parseToken(String authToken) {
//        try {
//            return JWSObject.parse(authToken);
//        } catch (ParseException | NullPointerException e) {
//            throw new InvalidJwtTokenException("Failed to parse JWT token", e);
//        }
//    }
//
//    protected String getTokenClientId(JWSObject jws) {
//        Map<String, Object> jwtJsonObject = jws.getPayload().toJSONObject();
//        try {
//            String username = JSONObjectUtils.getString(jwtJsonObject, "iss");
//            if (Strings.isBlank(username)) {
//                throw new InvalidJwtTokenException("Invalid JWT token");
//            }
//            return username;
//        } catch (ParseException e) {
//            throw new InvalidJwtTokenException("Failed to parse JWT payload", e);
//        }
//    }
//}
