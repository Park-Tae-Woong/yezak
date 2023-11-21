package yezak.api.api.admin.permission.account;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.domain.User;
import yezak.api.api.admin.permission.account.dto.EmplInfoReq;
import yezak.api.api.admin.permission.account.dto.SalaryUpdateReq;
import yezak.api.api.admin.permission.account.dto.UserManageDetailDto;
import yezak.api.api.admin.permission.account.dto.UserManagementDto;
import yezak.api.api.user.dto.GetChangeAmount;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogReq;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {
    List<UserManagementDto> getUsers(Long hospitalID);
    UserManageDetailDto getUserDetail(Long userId);

    List<Map<String, Object>> getRoleCategory();
    List<Map<String, Object>> getRoles(Long roleCategoryId);
    List<Map<String, Object>> selectSalaryType();
    List<Map<String, Object>> selectEmploymentStatus();
    List<Map<String, Object>> selectTreatmentSubject();
    List<Map<String, Object>> selectEmploymentTypes();
    List<GetChangeAmount> getChangeAmount(Long userId);

    User findById(Long userId);

    void updateSalary(SalaryUpdateReq salaryUpdateReq);
    void updateEmplInfo(EmplInfoReq emplInfoReq);

    void updateEmplStatusSchedule();
    void updateUserStatusStopSchedule();

    void updateStatusAble(Long id);
    void updateStatusDelete(Long id);
    void updateStatusStop(Long id);
    void userDeleteLog(LoginLogReq loginLogReq);

    Long findRoleCategoryId(Long userId);


}
