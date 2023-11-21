package yezak.api.api.marketing.landing;


import yezak.api.api.marketing.landing.dto.MarketingLandingModifyRequest;
import yezak.api.api.marketing.landing.dto.MarketingLandingRegistRequest;
import yezak.api.global.common.ResultResponse;

public interface MarketingLandingService {

    ResultResponse<?> getList(String searchKeyword);

    ResultResponse<?> deleteRow(String idArr);

    ResultResponse<?> getDetail(Integer id);

    ResultResponse<?> modifyData(MarketingLandingModifyRequest request) throws Exception;

    ResultResponse<?> registData(MarketingLandingRegistRequest request) throws Exception;
}
