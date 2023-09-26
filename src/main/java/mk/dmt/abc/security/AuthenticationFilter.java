//package mk.dmt.abc.security;
//
//import mk.dmt.abc.exception.MissingJwtTokenException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AuthenticationConverter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    public AuthenticationFilter() {
//        super("/**");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(final HttpServletRequest request,
//                                                final HttpServletResponse response) throws AuthenticationException {
//
//        final Authentication token = getAuthenticationToken(request);
//        if(token == null) {
//            throw new MissingJwtTokenException("JWT Token is missing from the header.");
//        }
//        return getAuthenticationManager().authenticate(token);
//    }
//
//    protected Authentication getAuthenticationToken(HttpServletRequest httpServletRequest) {
//        return null;
//    }
//
//    @Override
//    protected void successfulAuthentication(final HttpServletRequest request,
//                                            final HttpServletResponse response,
//                                            final FilterChain chain,
//                                            final Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//        chain.doFilter(request, response);
//    }
//}
