package yezak.api.api.admin.permission.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yezak.api.domain.User;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogReq;
import yezak.api.api.admin.permission.account.dto.EmplInfoReq;
import yezak.api.api.admin.permission.account.dto.SalaryUpdateReq;
import yezak.api.api.admin.permission.account.dto.UserManageDetailDto;
import yezak.api.api.admin.permission.auth.dto.AuthLogReq;
import yezak.api.global.common.ResultResponse;
import yezak.api.api.admin.permission.auth.AuthMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final AccountMapper accountMapper;
    private final AuthMapper authMapper;
    @Autowired
    private final HttpServletRequest request;
    private final Integer accountId = 55;

    @Override
    public ResultResponse<?> getUsers(){
        if(myDepth3Id().contains(accountId)) {
            return ResultResponse.builder()
                    .data(accountMapper.getUsers(myHospitalId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    public ResultResponse<?> getUserDetail(Long userId){
        if(myDepth3Id().contains(accountId)) {
            UserManageDetailDto userManageDetailDto = accountMapper.getUserDetail(userId);
            userManageDetailDto.setGetChangeAmount(accountMapper.getChangeAmount(userId));

            Map<String, Object> responseData = new HashMap<>();
            List<Map<String, Object>> roleCategory = accountMapper.getRoleCategory();
            List<Map<String, Object>> role = accountMapper.getRoles(myHospitalId());
            List<Map<String, Object>> salaryType = accountMapper.selectSalaryType();
            List<Map<String, Object>> employmentStatus = accountMapper.selectEmploymentStatus();
            List<Map<String, Object>> employmentTypes = accountMapper.selectEmploymentTypes();
            List<Map<String, Object>> treatmentSubject = accountMapper.selectTreatmentSubject();

            responseData.put("roleCategory", roleCategory);
            responseData.put("role", role);
            responseData.put("salaryType", salaryType);
            responseData.put("employmentStatus", employmentStatus);
            responseData.put("employmentTypes", employmentTypes);
            responseData.put("treatmentSubject", treatmentSubject);

            return ResultResponse.builder()
                    .data(userManageDetailDto)
                    .select(responseData)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> updateSalary(SalaryUpdateReq salaryUpdateReq) {
        if(myDepth3Id().contains(accountId)) {
            accountMapper.updateSalary(salaryUpdateReq);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> updateEmplInfo(EmplInfoReq emplInfoReq) {
        if(myDepth3Id().contains(accountId)) {
            accountMapper.updateEmplInfo(emplInfoReq);

            if (emplInfoReq.getEmploymentStatusId() != 1){
                // 휴직이나 퇴직으로 바꿀 시 사용정지 처리 및 로그 삽입
                accountMapper.updateStatusStop(emplInfoReq.getId());
                AuthLogReq authLogReq = AuthLogReq.builder()
                        .affectedUserId(emplInfoReq.getId())
                        .actUserId(myUserId())
                        .roleId(emplInfoReq.getRoleId())
                        .depth3NavigationsArray("")
                        .ip(getClientIp())
                        .hospitalId(myHospitalId())
                        .build();
                authMapper.authLog(authLogReq);
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> updateStatusAble(Long id) {
        if(myDepth3Id().contains(accountId)) {
            accountMapper.updateStatusAble(id);

            User user = accountMapper.findById(id);
            Long roleId = user.getRoleId();

            AuthLogReq authLogReq = AuthLogReq.builder()
                    .hospitalId(myHospitalId())
                    .actUserId(myUserId())
                    .depth3NavigationsArray(authMapper.findNavi3Id(roleId))
                    .ip(getClientIp())
                    .roleId(roleId)
                    .affectedUserId(id)
                    .build();
            authMapper.authLog(authLogReq);

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> updateStatusDelete(Long id) {
        if(myDepth3Id().contains(accountId)) {
            Long rcId = accountMapper.findRoleCategoryId(id);
            if (rcId == 12) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("병원장계정은 삭제가 불가능합니다.")
                        .build();
            }
            accountMapper.updateStatusDelete(id);

            LoginLogReq loginLogReq = LoginLogReq.builder()
                    .message("삭제")
                    .ip(getClientIp())
                    .hospitalId(myHospitalId())
                    .actUserId(myUserId())
                    .affectedUserId(id)
                    .result("성공")
                    .distinction("계정")
                    .build();
            accountMapper.userDeleteLog(loginLogReq);

            User userRole = accountMapper.findById(id);

            AuthLogReq authLogReq = AuthLogReq.builder()
                    .hospitalId(myHospitalId())
                    .actUserId(myUserId())
                    .depth3NavigationsArray("")
                    .ip(getClientIp())
                    .roleId(userRole.getRoleId())
                    .affectedUserId(id)
                    .build();
            authMapper.authLog(authLogReq);

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> updateStatusStop(Long id) {
        if(myDepth3Id().contains(accountId)) {
            accountMapper.updateStatusStop(id);

            User userRole = accountMapper.findById(id);

            AuthLogReq authLogReq = AuthLogReq.builder()
                    .hospitalId(myHospitalId())
                    .actUserId(myUserId())
                    .depth3NavigationsArray("")
                    .ip(getClientIp())
                    .roleId(userRole.getRoleId())
                    .affectedUserId(id)
                    .build();
            authMapper.authLog(authLogReq);

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }
    public String getClientIp() {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest cannot be null");
        }
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
