package ei.algobaroapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class AlgobaroApiApplicationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("DB Connection 테스트")
    void testDatabaseConnection() {
        try {
            Integer test = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            assertThat(test).isEqualTo(1);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
