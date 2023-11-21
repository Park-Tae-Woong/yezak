package yezak.api.api.admin.audit_record.login_log;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogListReq;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogReq;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogRes;

import java.util.List;

@Mapper
public interface LoginLogMapper {
    void insertLoginLog(LoginLogReq loginLogReq);
    List<LoginLogRes> getLoginLogList(LoginLogListReq loginLogListReq);
    Integer countLoginLogList(LoginLogListReq loginLogListReq);
    List<LoginLogRes> loginLogExcel(LoginLogListReq loginLogListReq);

}
