package yezak.api.api.setting.prescription.drug;

import yezak.api.api.setting.prescription.drug.dto.InsertDrugReq;
import yezak.api.api.setting.prescription.drug.dto.UpdateDrugReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface DrugService {

    ResultResponse<?> drugList(String dosageForm, String searchValue, Integer pageNum, Integer pageSize);
    ResultResponse<?> drugDetail(Long id);

    ResultResponse<?> updateDrug(UpdateDrugReq updateDrugReq);

    ResultResponse<?> createDrug(InsertDrugReq insertDrugReq);
    ResultResponse<?> deleteDrug(List<Long> ids);


}
