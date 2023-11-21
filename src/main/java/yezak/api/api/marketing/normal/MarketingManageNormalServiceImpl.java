package yezak.api.api.marketing.normal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yezak.api.api.marketing.normal.dto.*;
import yezak.api.global.common.ResultResponse;

import java.util.ArrayList;
import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Service
@Slf4j
public class MarketingManageNormalServiceImpl implements MarketingManageNormalService {

    private final MarketingManageNormalMapper mapper;
    private final Integer normalId = 16;

    @Override
    public ResultResponse<?> getReservationStatusesList() {
        if(myDepth3Id().contains(normalId)) {
            return ResultResponse.builder()
                    .data(mapper.getReservationStatusesList())
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
    public ResultResponse<?> getDbList(MarketingManageNormalListRequest request) {
        if(myDepth3Id().contains(normalId)) {
            request.setHospitalId(myHospitalId());
            List<MarketingManageNormalDbData> res = mapper.getDbList(request);
            List<MarketingManageNormalDbData> dbData = new ArrayList<>();
            for(int i=0; i<res.size(); i++) {
                List<MarketingManageNormalCounselHistoryInfo> counselHistoryInfo = mapper.getCounselHistoryInfo(res.get(i).getId());
                dbData.add(MarketingManageNormalDbData.builder()
                        .id(res.get(i).getId())
                        .name(res.get(i).getName())
                        .phoneNumber(res.get(i).getPhoneNumber())
                        .availableTime(res.get(i).getAvailableTime())
                        .accessRootName(res.get(i).getAccessRootName())
                        .createdAt(res.get(i).getCreatedAt())
                        .chargeId(res.get(i).getChargeId())
                        .chargeName(res.get(i).getChargeName())
                        .counselingStatus(res.get(i).getCounselingStatus())
                        .counselingStatusId(res.get(i).getCounselingStatusId())
                        .reservationStatus(res.get(i).getReservationStatus())
                        .reservationStatusId(res.get(i).getReservationStatusId())
                        .counselHistoryInfo(counselHistoryInfo)
                        .build());
            }
            MarketingManageNormalDbListResponse marketingManageNormalDbListResponse = MarketingManageNormalDbListResponse.builder()
                    .dbData(dbData)
                    .totalDbDataCount(res.size())
                    .build();
            return ResultResponse.builder()
                    .data(marketingManageNormalDbListResponse)
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
    public ResultResponse<?> getRoomList() {
        if(myDepth3Id().contains(normalId)) {
            return ResultResponse.builder()
                    .data(mapper.getRoomList(myHospitalId()))
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
}
