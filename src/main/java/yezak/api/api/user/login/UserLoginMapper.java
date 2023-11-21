package yezak.api.api.user.login;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.user.dto.FindPasswordReq;
import yezak.api.api.user.dto.NameAndRoleName;
import yezak.api.api.user.dto.UserLoginReq;
import yezak.api.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper

public interface UserLoginMapper {

	void login(UserLoginReq userLoginReq);

	User findById(Long id);
	User findByEmail(String email);

	Date getLastPasswordChange(String email);
//	LocalDateTime getLastPasswordChange(String email);
	Date getCreatedAt(String email);
	void updateLoginFail (String email);
	void resetLoginFail (String email);
	int getLoginCount (String email);
	NameAndRoleName nameAndRoleName (Long id);
	void findPassword(FindPasswordReq findPasswordReq);
	int getIsLocked(String email);
	void resetPassword(String email);
	List<Long> myNaviList(Long roleId);
	List<Map<String, Object>> selectGradeList();

}