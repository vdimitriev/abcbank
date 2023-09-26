package mk.dmt.abc.config;

import mk.dmt.abc.repository.CustomerRepository;
import mk.dmt.abc.security.model.CustomerDetails;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomerRepository userRepo;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil,
                          CustomerRepository userRepo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("header = " + header);
        if (Strings.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        System.out.println("token = " + token);
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }
        // Get user identity and set it on the spring security context
        CustomerDetails userDetails = userRepo
                .findByUsername(jwtTokenUtil.getUsername(token))
                .map(customerEntity -> new CustomerDetails(customerEntity.getUsername(), customerEntity.getPassword()))
                .orElse(null);

        System.out.println("userDetails = " + userDetails.getUsername());
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails == null ? new ArrayList() : userDetails.getAuthorities()
            );

        System.out.println("authentication = " + authentication.getName());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
