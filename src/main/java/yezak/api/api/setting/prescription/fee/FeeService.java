package yezak.api.api.setting.prescription.fee;

import yezak.api.api.setting.prescription.fee.dto.InsertFeeReq;
import yezak.api.api.setting.prescription.fee.dto.InsertInspectionFeeReq;
import yezak.api.api.setting.prescription.fee.dto.InsertSurgeryReq;
import yezak.api.api.setting.prescription.fee.dto.UpdateFeeReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface FeeService {
    ResultResponse<?> selectCodeTypeList();
    ResultResponse<?> getFeeList(Long categoryId, Long subdivisionId, String searchValue, Integer pageNum, Integer pageSize);
    ResultResponse<?> getFeeDetail(Long id);
    ResultResponse<?> insertFee(InsertFeeReq insertFeeReq);
    ResultResponse<?> insertInspectionFee(InsertInspectionFeeReq insertInspectionFeeReq);
    ResultResponse<?> insertSurgeryFee(InsertSurgeryReq insertSurgeryReq);
    ResultResponse<?> deleteFee(List<Long> ids);
    ResultResponse<?> updateFee(UpdateFeeReq updateFeeReq);

    ResultResponse<?> selectList();


}
