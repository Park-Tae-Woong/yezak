package yezak.api.api.admin.audit_record.login_log;

import yezak.api.global.common.ResultResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginLogService {
    ResultResponse<?> getLoginLogList(String start, String end, Integer pageNum, Integer pageSize);
    void loginLogExcel(HttpServletResponse response, String start, String end) throws IOException;

}
