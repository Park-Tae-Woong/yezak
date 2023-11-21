package yezak.api.api.marketing.guest;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.marketing.guest.dto.MarketingManageGuestFileRequest;
import yezak.api.api.marketing.guest.dto.MarketingManageGuestListResponse;
import yezak.api.api.marketing.guest.dto.MarketingManageGuestRegistRequest;

import java.util.List;
import java.util.Map;

@Mapper
public interface MarketingManageGuestMapper {
    List<MarketingManageGuestListResponse> getList(Long hospitalId);

    Integer registDb(MarketingManageGuestRegistRequest request);

    void registFile(MarketingManageGuestFileRequest request);

    void registPatiantsData(Map paramMap);

    Integer checkPatientCounselingInfo(Map<String, Object> paramMap);
}
