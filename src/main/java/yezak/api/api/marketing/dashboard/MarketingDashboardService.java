package yezak.api.api.marketing.dashboard;


import yezak.api.global.common.ResultResponse;

public interface MarketingDashboardService {

    ResultResponse<?> getHospitalGoals(String year, String month);

    ResultResponse<?> getAccessRoot(String year, String month);

    ResultResponse<?> getGenderAge(String year, String month);

    ResultResponse<?> getRegion(String year, String month);

    ResultResponse<?> getYearsList();

    ResultResponse<?> getAdExpenseProfit(String year, String month);
}
