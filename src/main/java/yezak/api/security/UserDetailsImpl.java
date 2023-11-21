package yezak.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import yezak.api.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    //권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //여러개의 권한을 가질 수 있으므로 리스트
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 역할 목록
        GrantedAuthority roleAuthority = new SimpleGrantedAuthority(String.valueOf(user.getRoleId()));
        authorities.add(roleAuthority);

        return authorities;
    }

    //계정의 비밀번호 리턴
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //계정의 이름 리턴
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    //계정이 만료되지 않았는지 리턴 (true = 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않았는지 리턴 (true = 잠겨있지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //패스워드가 만료되지 않았는지 리턴 (true = 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용가능한지 리턴 (true = 사용가능)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getHospitalId() {
        return user.getHospitalId();
    }
}
