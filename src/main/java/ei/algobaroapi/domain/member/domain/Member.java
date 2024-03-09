package ei.algobaroapi.domain.member.domain;

import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import ei.algobaroapi.domain.member.dto.request.MemberGeneralUpdateRequest;
import ei.algobaroapi.domain.member.dto.request.MemberPasswordUpdateRequest;
import ei.algobaroapi.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private EmailVo email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "boj_id", nullable = false)
    private String bojId;

    @Column(name = "profile_image")
    private String profileImage;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles;

    @Builder
    public Member(String email, String password, String nickname, String bojId) {
        this.email = new EmailVo(email);
        this.password = password;
        this.nickname = nickname;
        this.bojId = bojId;
        roles = List.of("ROLE_USER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateProfileImage(String profileImageUrl) {
        this.profileImage = profileImageUrl;
    }

    public void updateGeneralInfo(MemberGeneralUpdateRequest request) {
        this.nickname = request.getNickname() != null ? request.getNickname() : this.nickname;
        this.bojId = request.getBojId() != null ? request.getBojId() : this.bojId;
    }

    public void updatePassword(String encryptPassword) {
        this.password = encryptPassword != null ? encryptPassword : this.password;
    }
}
