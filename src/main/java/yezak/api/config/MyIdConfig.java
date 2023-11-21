package yezak.api.config;

import org.springframework.context.annotation.Configuration;
import yezak.api.domain.User;
import yezak.api.api.admin.permission.auth.AuthMapper;
import yezak.api.api.user.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Configuration
public class MyIdConfig {

    private static UserMapper userMapper;
    private static HttpServletRequest request;
    private static AuthMapper authMapper;

    public MyIdConfig(UserMapper userMapper, HttpServletRequest request, AuthMapper authMapper) {
        MyIdConfig.userMapper = userMapper;
        MyIdConfig.request = request;
        MyIdConfig.authMapper = authMapper;
    }

    public static Long myHospitalId() {
        User user = userMapper.findById((Long) request.getAttribute("userId"));
        return user.getHospitalId();
    }

    public static Long myRoleId() {
        User user = userMapper.findById((Long) request.getAttribute("userId"));
        return user.getRoleId();
    }

    public static List<Integer> myDepth3Id() {
        User user = userMapper.findById((Long) request.getAttribute("userId"));
        Long roleId = user.getRoleId();
        return authMapper.checkAuth(roleId);
    }

    public static Long myUserId() {
        User user = userMapper.findById((Long) request.getAttribute("userId"));
        return user.getId();
    }
}