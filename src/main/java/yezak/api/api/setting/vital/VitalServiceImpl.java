package yezak.api.api.setting.vital;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.setting.vital.dto.MultiVitalReq;
import yezak.api.api.setting.vital.dto.UpdateVitalReq;
import yezak.api.api.setting.vital.dto.VitalDto;
import yezak.api.global.common.ResultResponse;

import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class VitalServiceImpl implements VitalService{
    private final VitalMapper vitalMapper;
    private final Integer vitalId = 68;
    @Override
    public ResultResponse<?> getVitalList() {
        if(myDepth3Id().contains(vitalId)) {
            return ResultResponse.builder()
                    .data(vitalMapper.getVitalList(myHospitalId()))
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
    public ResultResponse<?> insertAndUpdateVital(MultiVitalReq multiVitalReq) {
        if(myDepth3Id().contains(vitalId)) {
            List<VitalDto> vitalDtos = multiVitalReq.getInsertVital();
            if (vitalDtos != null) {
                for (VitalDto vitalDto : vitalDtos) {
                    vitalDto.setHospitalId(myHospitalId());
                    vitalMapper.insertVital(vitalDto);
                }
            }
            List<UpdateVitalReq> updateVitalReqs = multiVitalReq.getUpdateVital();
            if (updateVitalReqs != null) {
                for (UpdateVitalReq updateVitalReq : updateVitalReqs) {
                    updateVitalReq.setHospitalId(myHospitalId());
                    vitalMapper.updateVital(updateVitalReq);
                }
            }
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
}
