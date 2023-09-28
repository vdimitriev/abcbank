package mk.dmt.abc;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AbcApplication.class)
@ActiveProfiles("test-containers-flyway")
@AutoConfigureMockMvc
public class RegisterCustomerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    void givenValidJson_whenRegister_thenCreated() throws Exception {

        // given
        final String json = getJsonStringFromFile("json/register-customer.json");

        // when
        mvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

        // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.username", Matchers.is("test")))
                .andExpect(jsonPath("$.password", Matchers.isA(String.class)));
    }

    private String getJsonStringFromFile(String fileName) throws IOException {
        final ClassPathResource jsonDataResource = new ClassPathResource(fileName);
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(jsonDataResource.getInputStream(), StandardCharsets.UTF_8));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}
