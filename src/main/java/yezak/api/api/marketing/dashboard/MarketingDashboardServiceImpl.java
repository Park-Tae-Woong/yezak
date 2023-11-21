package yezak.api.api.marketing.dashboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import yezak.api.api.marketing.admin.dto.MarketingManageAdminDashboardGenderAgeCount;
import yezak.api.api.marketing.dashboard.dto.AdExpenseProfitInfo;
import yezak.api.api.marketing.dashboard.dto.HospitalGoalsInfo;
import yezak.api.api.marketing.dashboard.dto.YearsInfo;
import yezak.api.global.common.ResultResponse;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Service
@Slf4j
public class MarketingDashboardServiceImpl implements MarketingDashboardService {

    private final MarketingDashboardMapper mapper;
    private final Integer boardId = 13;

    @Override
    public ResultResponse<?> getHospitalGoals(String year, String month) {
        if(myDepth3Id().contains(boardId)) {
            String date = year+"-"+month;
            String preDate = this.getPreDate(year, month);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("date", date);
            paramMap.put("preDate", preDate);

            String monthSalesTarget = mapper.getHospitalGoalsInfo(paramMap);
            Integer preMonthSales = mapper.getPreMonthSales(paramMap);
            Integer monthSales = mapper.getMonthSales(paramMap);

            HospitalGoalsInfo hospitalGoalsInfo = HospitalGoalsInfo.builder()
                    .monthSalesTarget(monthSalesTarget == null ? 0 : Integer.parseInt(monthSalesTarget))
                    .preMonthSales(preMonthSales)
                    .monthSales(monthSales)
                    .build();

            return ResultResponse.builder()
                    .data(hospitalGoalsInfo)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    private String getPreDate(String year, String month) {
        String result = year + "-" + (Integer.parseInt(month)-1);
        //01 02 03 04 05 06 07 08 09 10 11 12
        if(month.equals("01")) {
            result = Integer.parseInt(year)-1 + "-12";
        } else if(Integer.parseInt(month) <= 10) {
            result = year + "-0" + (Integer.parseInt(month)-1);
        }

        return result;
    }

    @Override
    public ResultResponse<?> getAccessRoot(String year, String month) {
        if(myDepth3Id().contains(boardId)) {
            String date = year+"-"+month;

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("date", date);

            return ResultResponse.builder()
                    .data(mapper.getAccessRoot(paramMap))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> getGenderAge(String year, String month) {
        if(myDepth3Id().contains(boardId)) {
            String date = year+"-"+month;

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("date", date);

            List<HashMap> genderAgeRes = mapper.getGenderAge(paramMap);
            return ResultResponse.builder()
                    .data(getGenderAgeCount(genderAgeRes))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getRegion(String year, String month) {
        if(myDepth3Id().contains(boardId)) {
            String date = year+"-"+month;

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("date", date);
            return ResultResponse.builder()
                    .data(mapper.getRegion(paramMap))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getYearsList() {
        if(myDepth3Id().contains(boardId)) {
            int min = Integer.parseInt(mapper.getYearsInfoList(myHospitalId()));
            int max = LocalDate.now().getYear();

            List<YearsInfo> list = new ArrayList();
            for(int i=0; i<max-min; i++) {
                list.add(YearsInfo.builder()
                        .value(max-i)
                        .text((max-i) + "년")
                        .build());
            }
            list.add(YearsInfo.builder()
                    .value(min)
                    .text((min) + "년")
                    .build());
            return ResultResponse.builder()
                    .data(list)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getAdExpenseProfit(String year, String month) {
        if(myDepth3Id().contains(boardId)) {
            String date = year+"-"+month;

            //ROAS = (등록한 DB 고객의 매출 / 집행 금액) x 100
            // 입력값을 YearMonth 객체로 변환
            YearMonth inputYearMonth = YearMonth.parse(date, DateTimeFormatter.ofPattern("yyyy-MM"));

            // 이전 6개월에 해당하는 연-월 데이터 추출
            List<YearMonth> previousMonths = new ArrayList<>();
            previousMonths.add(inputYearMonth);
            for (int i = 1; i < 6; i++) {
                YearMonth previousMonth = inputYearMonth.minusMonths(i);
                previousMonths.add(previousMonth);
            }

            List<AdExpenseProfitInfo> adExpenseProfitInfoList = new ArrayList<>();

            // 추출된 연-월 데이터 출력
            for (YearMonth previousMonth : previousMonths) {
                String formattedMonth = previousMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("hospitalId", myHospitalId());
                paramMap.put("date", formattedMonth);
                adExpenseProfitInfoList.add(mapper.getAdExpenseProfit(paramMap));
            }

            return ResultResponse.builder()
                    .data(adExpenseProfitInfoList)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    private MarketingManageAdminDashboardGenderAgeCount getGenderAgeCount(List<HashMap> param) {
        Map<String, Integer> genderAgeCountMap = new HashMap<>();

        for (HashMap<String, Object> entry : param) {
            String ageRangeStr = entry.get("ageRange").toString();
            String genderStr = entry.get("gender").toString();
            Integer count = Integer.parseInt(entry.get("count").toString());

            String key = ageRangeStr + "-" + genderStr;
            genderAgeCountMap.put(key, count);
        }

        List<Integer> man = new ArrayList<>();
        man.add(getCountFromMap(genderAgeCountMap, "0-14-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "15-24-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "25-34-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "35-44-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "45-54-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "55-64-남자"));
        man.add(getCountFromMap(genderAgeCountMap, "65+-남자"));
        List<Integer> woman = new ArrayList<>();
        woman.add(getCountFromMap(genderAgeCountMap, "0-14-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "15-24-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "25-34-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "35-44-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "45-54-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "55-64-여자"));
        woman.add(getCountFromMap(genderAgeCountMap, "65+-여자"));

        return MarketingManageAdminDashboardGenderAgeCount.builder()
                .man(man)
                .woman(woman)
                .build();
    }

    private Integer getCountFromMap(Map<String, Integer> map, String key) {
        return map.getOrDefault(key, 0);
    }
}
