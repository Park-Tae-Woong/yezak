package yezak.api.api.marketing.normal;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.marketing.normal.dto.*;

import java.util.List;

@Mapper
public interface MarketingManageNormalMapper {

    List<MarketingManageReservationStatusInfo> getReservationStatusesList();

    List<MarketingManageNormalDbData> getDbList(MarketingManageNormalListRequest request);

    List<MarketingManageNormalCounselHistoryInfo> getCounselHistoryInfo(Integer id);

    List<MarketingManageRoomInfo> getRoomList(Long hospitalId);
}
