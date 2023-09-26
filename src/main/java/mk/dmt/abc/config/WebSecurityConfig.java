//package mk.dmt.abc.config;
//
//import mk.dmt.abc.exception.CustomerNotFoundException;
//import mk.dmt.abc.repository.CustomerRepository;
//import mk.dmt.abc.security.CustomerDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletResponse;
//
//import static java.lang.String.format;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    private final CustomerRepository customerRepository;
//
//    private final CustomerDetailsService userDetailsService;
//
//    private final JwtTokenFilter jwtTokenFilter;
//
//    public WebSecurityConfig(CustomerRepository customerRepository, CustomerDetailsService userDetailsService) {
//        this.customerRepository = customerRepository;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        // Enable CORS and disable CSRF
//        http = http.cors().and().csrf().disable();
//
//        // Set session management to stateless
//        http = http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
//
//        // Set unauthorized requests exception handler
//        http = http
//                .exceptionHandling()
//                .authenticationEntryPoint(
//                        (request, response, ex) -> {
//                            response.sendError(
//                                    HttpServletResponse.SC_UNAUTHORIZED,
//                                    ex.getMessage()
//                            );
//                        }
//                )
//                .and();
//
//        // Set permissions on endpoints
//        http.authorizeRequests()
//                // Our public endpoints
//                .antMatchers("/api/public/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
//                // Our private endpoints
//                .anyRequest().authenticated();
//
//        // Add JWT token filter
//        http.addFilterBefore(
//                jwtTokenFilter,
//                UsernamePasswordAuthenticationFilter.class
//        );
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
