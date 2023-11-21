package yezak.api.api.marketing.normal;

import yezak.api.api.marketing.normal.dto.MarketingManageNormalListRequest;
import yezak.api.global.common.ResultResponse;

public interface MarketingManageNormalService {

    ResultResponse<?> getReservationStatusesList();

    ResultResponse<?> getDbList(MarketingManageNormalListRequest request);

    ResultResponse<?> getRoomList();
}
