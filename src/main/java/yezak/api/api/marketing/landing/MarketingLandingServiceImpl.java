package yezak.api.api.marketing.landing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yezak.api.api.marketing.landing.dto.MarketingLandingModifyRequest;
import yezak.api.api.marketing.landing.dto.MarketingLandingRegistRequest;
import yezak.api.global.common.ResultResponse;

import java.util.HashMap;
import java.util.Map;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class MarketingLandingServiceImpl implements MarketingLandingService {

    private final MarketingLandingMapper mapper;
    private final Integer landingId = 14;

    @Override
    public ResultResponse<?> getList(String searchKeyword) {
        if(myDepth3Id().contains(landingId)) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("searchKeyword", searchKeyword);

            return ResultResponse.builder()
                    .data(mapper.getList(paramMap))
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
    public ResultResponse<?> deleteRow(String idArr) {
        if(myDepth3Id().contains(landingId)) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("id", idArr);
            mapper.deleteRow(paramMap);

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("삭제되었습니다.")
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
    public ResultResponse<?> getDetail(Integer id) {
        if(myDepth3Id().contains(landingId)) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("id", id);
            return ResultResponse.builder()
                    .data(mapper.getDetail(paramMap))
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
    public ResultResponse<?> modifyData(MarketingLandingModifyRequest request) {
        if(myDepth3Id().contains(landingId)) {
            Map<String, Object> map = new HashMap<>();
            map.put("koName", request.getKoName().trim());
            map.put("hospitalId", myHospitalId());

            if(!request.getKoName().equalsIgnoreCase(mapper.getKoNameById(request.getId()))) {
                if(mapper.checkAccessRootName(map) > 0) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("중복된 유입경로 명 입니다.")
                            .build();
                }
            }

            mapper.modifyData(MarketingLandingModifyRequest.builder()
                    .id(request.getId())
                    .koName(request.getKoName())
                    .landingPageUrl(request.getLandingPageUrl())
                    .useYn(request.getUseYn())
                    .remark(request.getRemark())
                    .hospitalId(myHospitalId())
                    .build());

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
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
    public ResultResponse<?> registData(MarketingLandingRegistRequest request) {
        if(myDepth3Id().contains(landingId)) {
            Map<String, Object> map = new HashMap<>();
            map.put("koName", request.getKoName().trim());
            map.put("hospitalId", myHospitalId());
            if(mapper.checkAccessRootName(map) > 0) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("중복된 유입경로 명 입니다.")
                        .build();
            }

            mapper.registData(MarketingLandingRegistRequest.builder()
                    .koName(request.getKoName())
                    .landingPageUrl(request.getLandingPageUrl())
                    .useYn(request.getUseYn())
                    .remark(request.getRemark())
                    .hospitalId(myHospitalId())
                    .userId(myUserId())
                    .build());

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("등록되었습니다.")
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
