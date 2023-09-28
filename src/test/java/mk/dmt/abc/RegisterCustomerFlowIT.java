package mk.dmt.abc;

import mk.dmt.abc.model.Account;
import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.CustomerResponse;
import mk.dmt.abc.model.LoginRequest;
import mk.dmt.abc.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ActiveProfiles("test-containers-flyway")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AbcApplication.class)
public class RegisterCustomerFlowIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @LocalServerPort
    private int randomServerPort;

    private ObjectMapper mapper;

    private static final HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        headers.setContentType(MediaType.APPLICATION_JSON);
        customerRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void givenRegisteredCustomer_whenLoginOK_thenOverviewSuccess_withGoodToken() throws IOException {

        // given
        final String json = getJsonStringFromFile("json/register-customer.json");
        final Customer customer = mapper.readValue(json, Customer.class);
        final HttpEntity<Customer> registerRequest = new HttpEntity<>(customer, headers);
        String url = new StringBuilder("http://localhost:")
                .append(randomServerPort)
                .append("/register").toString();

        // when
        final ResponseEntity<CustomerResponse> registerResponse = restTemplate.postForEntity(url, registerRequest, CustomerResponse.class);

        // then
        assertEquals(HttpStatus.CREATED, registerResponse.getStatusCode());

        // and
        final CustomerResponse customerResponse = registerResponse.getBody();

        assertEquals("test", customerResponse.getUsername());
        assertFalse(customerResponse.getPassword().isEmpty());

        url = new StringBuilder("http://localhost:")
                .append(randomServerPort)
                .append("/logon").toString();

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(customerResponse.getUsername());
        loginRequest.setPassword(customerResponse.getPassword());

        final ResponseEntity<Void> loginResult = restTemplate.postForEntity(url, loginRequest, Void.class);
        final HttpHeaders loginResultHeaders = loginResult.getHeaders();

        final String authorizationHeader = loginResultHeaders.get(HttpHeaders.AUTHORIZATION).get(0);
        assertNotNull(authorizationHeader);

        url = new StringBuilder("http://localhost:")
                .append(randomServerPort)
                .append("/overview/NL95ABCB0000000001").toString();

        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + authorizationHeader);
        final HttpEntity<Void> overviewRequest = new HttpEntity<>(null, headers);
        final ResponseEntity<Account> overviewResponse = restTemplate.exchange(url, HttpMethod.GET, overviewRequest, Account.class);

        assertEquals(HttpStatus.OK, overviewResponse.getStatusCode());
    }

    @Test
    public void givenRegisteredCustomer_whenOverviewWithNoToken_thenForbidden() throws IOException {

        String url = new StringBuilder("http://localhost:")
                .append(randomServerPort)
                .append("/overview/NL92ABCB0000000002").toString();

        final HttpEntity<Void> overviewRequest = new HttpEntity<>(null, headers);
        final ResponseEntity<Account> overviewResponse = restTemplate.exchange(url, HttpMethod.GET, overviewRequest, Account.class);

        assertEquals(HttpStatus.FORBIDDEN, overviewResponse.getStatusCode());
    }

    private String getJsonStringFromFile(String fileName) throws IOException {
        final ClassPathResource jsonDataResource = new ClassPathResource(fileName);
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(jsonDataResource.getInputStream(), StandardCharsets.UTF_8));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}
