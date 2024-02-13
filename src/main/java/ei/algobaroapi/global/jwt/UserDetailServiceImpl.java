package ei.algobaroapi.global.jwt;

import ei.algobaroapi.domain.member.domain.MemberRepository;
import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmailAndDeletedAtIsNull(new EmailVo(email))
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
