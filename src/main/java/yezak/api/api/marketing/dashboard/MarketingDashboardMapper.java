package yezak.api.api.marketing.dashboard;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.marketing.dashboard.dto.AccessRootCountInfo;
import yezak.api.api.marketing.dashboard.dto.AdExpenseProfitInfo;
import yezak.api.api.marketing.dashboard.dto.RegionCountInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MarketingDashboardMapper {
    String getHospitalGoalsInfo(Map<String, Object> paramMap);

    List<AccessRootCountInfo> getAccessRoot(Map<String, Object> paramMap);

    List<HashMap> getGenderAge(Map<String, Object> paramMap);

    List<RegionCountInfo> getRegion(Map<String, Object> paramMap);

    String getYearsInfoList(Long hospitalId);

    Integer getPreMonthSales(Map<String, Object> paramMap);

    Integer getMonthSales(Map<String, Object> paramMap);

    AdExpenseProfitInfo getAdExpenseProfit(Map<String, Object> paramMap);
}
