package yezak.api.api.setting.disease;

import yezak.api.api.setting.disease.dto.UpdateDiseaseReq;
import yezak.api.global.common.ResultResponse;

public interface DiseaseService {
    ResultResponse<?> diseaseList(String perfectCodeChecker, String searchValue, int pageNum, int pageSize);

    ResultResponse<?> findByDiseaseCode(Long id);

    ResultResponse<?> updateCode(UpdateDiseaseReq updateDiseaseReq);



}
