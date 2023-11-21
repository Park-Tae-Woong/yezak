package yezak.api.api.setting.sickness;

import yezak.api.api.setting.sickness.dto.CreateSicknessReq;
import yezak.api.api.setting.sickness.dto.UpdateSicknessReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface SicknessService {

    ResultResponse<?> getSickList(String searchValue);

    ResultResponse<?> insertSick(CreateSicknessReq createSicknessReq);

    ResultResponse<?> updateSick(UpdateSicknessReq updateSicknessReq);

    ResultResponse<?> deleteSick(List<Long> ids);
}
