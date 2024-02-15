package ei.algobaroapi;

import static org.assertj.core.api.Assertions.assertThat;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlgobaroApiApplicationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("DB Connection 테스트")
    void testDatabaseConnection() {
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("test")
                .build();

        Member savedMember = memberRepository.save(newMember);

        assertThat(savedMember).isNotNull();
    }
}
