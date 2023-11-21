package yezak.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.domain.User;
import yezak.api.api.user.login.UserLoginMapper;
@RequiredArgsConstructor
@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserLoginMapper userLoginMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userLoginMapper.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("없는 사용자 입니다.");
        }
        return new UserDetailsImpl(user);
    }
}
