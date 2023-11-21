package yezak.api.api.setting.vital;

import yezak.api.api.setting.vital.dto.MultiVitalReq;
import yezak.api.global.common.ResultResponse;

public interface VitalService {

    ResultResponse<?> getVitalList();

    ResultResponse<?> insertAndUpdateVital(MultiVitalReq multiVitalReq);
}
