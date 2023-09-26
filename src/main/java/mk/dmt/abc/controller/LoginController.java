package mk.dmt.abc.controller;

import mk.dmt.abc.model.LoginRequest;
import mk.dmt.abc.model.LoginResponse;
import mk.dmt.abc.security.model.CustomerDetails;
import mk.dmt.abc.security.model.JwtUtils;
import mk.dmt.abc.service.LoginService;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import mk.dmt.abc.config.JwtTokenUtil;

@RestController
@RequestMapping("/logon")
public class LoginController {

    private final LoginService loginService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final JwtTokenUtil jwtTokenUtil;

    public LoginController(LoginService loginService,
                           AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils, JwtTokenUtil jwtTokenUtil) {
        this.loginService = loginService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.jwtTokenUtil = jwtTokenUtil;
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        CustomerDetails userDetails = (CustomerDetails) authentication.getPrincipal();
//        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(new LoginResponse(userDetails.getUsername(), roles));
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logon(@RequestBody LoginRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            CustomerDetails user = (CustomerDetails) authenticate.getPrincipal();
            List<String> roles = user.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(new LoginResponse(user.getUsername(), roles));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
