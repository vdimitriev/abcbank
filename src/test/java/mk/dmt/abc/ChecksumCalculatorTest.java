package mk.dmt.abc;

import mk.dmt.abc.algorithm.ChecksumCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = AbcApplication.class)
@ActiveProfiles("test-containers-flyway")
public class ChecksumCalculatorTest {

    @Autowired
    private ChecksumCalculator mod9710;

    @Test
    void givenAccountNumber0000000001_whenCalculateChecksum_thenValid() {

        // given
        String accountNumber = "0000000001";

        // when
        String checksum = mod9710.compute(accountNumber);

        // then
        assertEquals("95", checksum);
    }

    @Test
    void givenAccountNumber0000000002_whenCalculateChecksum_thenValid() {

        // given
        String accountNumber = "0000000002";

        // when
        String checksum = mod9710.compute(accountNumber);

        // then
        assertEquals("92", checksum);
    }

    @Test
    void givenAccountNumber0000001001_whenCalculateChecksum_thenValid() {

        // given
        String accountNumber = "0000001001";

        // when
        String checksum = mod9710.compute(accountNumber);

        // then
        assertEquals("05", checksum);
    }
}
