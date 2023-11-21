package yezak.api.api.setting.drug_usage;

import yezak.api.api.setting.drug_usage.dto.InsertDrugUsageReq;
import yezak.api.api.setting.drug_usage.dto.UpdateDrugUsageReq;
import yezak.api.global.common.ResultResponse;

public interface DrugUsageService {

    ResultResponse<?> drugUsageList(Long drugUsageTypeId, String searchValue, Integer pageNum, Integer pageSize);

    ResultResponse<?> updateUsage(UpdateDrugUsageReq updateDrugUsageReq);

    ResultResponse<?> insertUsage(InsertDrugUsageReq insertDrugUsageReq);

}
