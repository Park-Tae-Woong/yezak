package yezak.api.api.admin.permission.account;

import yezak.api.api.admin.permission.account.dto.EmplInfoReq;
import yezak.api.api.admin.permission.account.dto.SalaryUpdateReq;
import yezak.api.global.common.ResultResponse;

public interface AccountService {
    ResultResponse<?> getUsers();
    ResultResponse<?> getUserDetail(Long userId);
    ResultResponse<?> updateSalary(SalaryUpdateReq salaryUpdateReq);
    ResultResponse<?> updateEmplInfo(EmplInfoReq emplInfoReq);

    ResultResponse<?> updateStatusAble(Long id);
    ResultResponse<?> updateStatusDelete(Long id);
    ResultResponse<?> updateStatusStop(Long id);


}
