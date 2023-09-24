//package mk.dmt.abc.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Configuration
//public class WebSecurityConfigEntryPointApplication extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        List<String> AUTH_LIST = new ArrayList<>();
//        AUTH_LIST.add("/swagger-resources/**");
//        AUTH_LIST.add("/swagger-ui.html**");
//        AUTH_LIST.add("/webjars/**");
//        AUTH_LIST.add("favicon.ico");
//        http
//                .antMatcher("/**").authorizeRequests().anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .defaultAuthenticationEntryPointFor(swaggerAuthenticationEntryPoint(), new CustomRequestMatcher(AUTH_LIST))
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .and()
//                .csrf().disable();
//    }
//
//    @Bean
//    public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
//        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//        entryPoint.setRealmName("Swagger Realm");
//        return entryPoint;
//    }
//
//    private class CustomRequestMatcher implements RequestMatcher {
//
//        private List<AntPathRequestMatcher> matchers;
//
//        private CustomRequestMatcher(List<String> matchers) {
//            this.matchers = matchers.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
//        }
//
//        @Override
//        public boolean matches(HttpServletRequest request) {
//            return matchers.stream().anyMatch(a -> a.matches(request));
//        }
//
//    }
//
//}
