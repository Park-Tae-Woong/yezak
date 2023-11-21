package yezak.api.api.setting.sickness;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.setting.sickness.dto.CreateSicknessReq;
import yezak.api.api.setting.sickness.dto.SicknessListReq;
import yezak.api.api.setting.sickness.dto.UpdateSicknessReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@Component
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SicknessServiceImpl implements SicknessService{

    private final SicknessMapper sicknessMapper;

    private final Integer sicknessId = 61;
    @Override
    public ResultResponse<?> getSickList(String searchValue) {
        if(myDepth3Id().contains(sicknessId)) {
            SicknessListReq sicknessListReq = new SicknessListReq(myHospitalId(), searchValue);
            return ResultResponse.builder()
                    .data(sicknessMapper.getSickList(sicknessListReq))
                    .result(true)
                    .resultCode("200")
                    .build();
        }
        else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> insertSick(CreateSicknessReq createSicknessReq) {
        if (myDepth3Id().contains(sicknessId)) {
            createSicknessReq = CreateSicknessReq.builder()
                    .customCode(createSicknessReq.getCustomCode())
                    .content(createSicknessReq.getContent())
                    .hospitalId(myHospitalId())
                    .build();
            sicknessMapper.insertSick(createSicknessReq);
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
    public ResultResponse<?> updateSick(UpdateSicknessReq updateSicknessReq) {
        if (myDepth3Id().contains(sicknessId)) {
            String code = sicknessMapper.findByCode(updateSicknessReq.getCustomCode());
            if (code != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("사용자코드가 중복됩니다. 사용자코드를 변경해주세요.")
                        .build();
            }
            sicknessMapper.updateSick(updateSicknessReq);
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
    public ResultResponse<?> deleteSick(List<Long> ids) {
        if (myDepth3Id().contains(sicknessId)) {
            for (Long id : ids) {
                sicknessMapper.deleteSick(id);
            }
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

}
