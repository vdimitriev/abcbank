//package mk.dmt.abc.security;
//
//import com.nimbusds.jose.JWSObject;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//
//public class JwtAuthenticationToken extends AbstractAuthenticationToken {
//
//    private final String username;
//    private final JWSObject jwt;
//
//    public JwtAuthenticationToken(String username, JWSObject jwt) {
//        super(null);
//        this.username = username;
//        this.jwt = jwt;
//        this.setAuthenticated(false);
//    }
//
//    public JWSObject getJwt() {
//        return jwt;
//    }
//
//    @Override
//    public JWSObject getCredentials() {
//        return jwt;
//    }
//
//    @Override
//    public String getPrincipal() {
//        return username;
//    }
//
//}
