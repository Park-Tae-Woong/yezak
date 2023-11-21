package yezak.api.api.user.join;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.domain.User;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogReq;
import yezak.api.api.user.dto.UserJoinReq;

import java.util.Optional;

@Mapper
public interface UserJoinMapper {
	Optional<User> findByEmail(String email);

	void insertUser(UserJoinReq userJoinReq);
	void firstEmploymentInfo(Long id);
	void firstPersonalInfo (Long userId);
	Integer findDeleteByEmail(String email);
	Long findGradeByUserId(Long userId);
	void userJoinLog(LoginLogReq loginLogReq);

}