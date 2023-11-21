package yezak.api.api.marketing.landing;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.marketing.landing.dto.MarketingLandingListResponse;
import yezak.api.api.marketing.landing.dto.MarketingLandingModifyRequest;
import yezak.api.api.marketing.landing.dto.MarketingLandingRegistRequest;

import java.util.List;
import java.util.Map;

@Mapper
public interface MarketingLandingMapper {

    List<MarketingLandingListResponse> getList(Map<String, Object> paramMap);

    void deleteRow(Map<String, Object> paramMap);

    MarketingLandingListResponse getDetail(Map<String, Object> paramMap);

    void modifyData(MarketingLandingModifyRequest request);

    Integer checkAccessRootName(Map<String, Object> map);

    void registData(MarketingLandingRegistRequest request);

    String getKoNameById(Integer id);
}
