package yezak.api.api.setting.disease;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.setting.disease.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
@Transactional
public class DiseaseServiceImpl implements DiseaseService{

    private final DiseaseMapper diseaseMapper;
    private final Integer diseaseId = 62;

    @Override
    public ResultResponse<?> diseaseList(String perfectCodeChecker, String searchValue, int pageNum, int pageSize) {

        if(myDepth3Id().contains(diseaseId)) {
            int offset = (pageNum - 1) * pageSize;
            FilterDiseaseReq filterDiseaseReq = new FilterDiseaseReq(perfectCodeChecker,searchValue, myHospitalId(), offset, pageSize);
            List<DiseaseDto> dtos = diseaseMapper.diseaseList(filterDiseaseReq);
            filterDiseaseReq.setHospitalId(myHospitalId());

            for(DiseaseDto dto : dtos) {
                String customCode = diseaseMapper.customCodeByHospitalId(myHospitalId(), dto.getId());
                if (customCode != null) {
                    dto.setCustomCode(customCode);
                }
            }
            int totalCount = diseaseMapper.countTotal(filterDiseaseReq);
            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double)totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(dtos)
                    .page(page)
                    .result(true)
                    .resultCode("200")
                    .resultMessage("성공")
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
    public ResultResponse<?> findByDiseaseCode(Long id) {
        if(myDepth3Id().contains(diseaseId)) {
            return ResultResponse.builder()
                    .select(diseaseMapper.findByDiseaseCode(id))
                    .result(true)
                    .resultCode("200")
                    .resultMessage("성공")
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
    public ResultResponse<?> updateCode(UpdateDiseaseReq updateDiseaseReq) {
        if(myDepth3Id().contains(diseaseId)) {
            //optionId
            Long optionId = diseaseMapper.findDiseaseOption(updateDiseaseReq.getDiseaseId(), myHospitalId());

            //optionId 없으면 insert
            if (optionId == null){
                InsertDiseaseOptionReq insertDiseaseOptionReq = new InsertDiseaseOptionReq(updateDiseaseReq.getDiseaseId(), myHospitalId(), updateDiseaseReq.getCustomCode());
                diseaseMapper.insertDiseaseOption(insertDiseaseOptionReq);
            }
            //optionId 있으면 update
            else {
                CustomCodeDuplicateCheck customCodeDuplicateCheck = new CustomCodeDuplicateCheck(updateDiseaseReq.getCustomCode(), myHospitalId());
                String exist = diseaseMapper.findByCustomCode(customCodeDuplicateCheck);
                if (exist != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자 코드가 중복됩니다. 사용자 코드를 변경해주세요.")
                            .build();
                }
                updateDiseaseReq.setDiseaseId(optionId);
                updateDiseaseReq.setHospitalId(myHospitalId());
                diseaseMapper.updateCode(updateDiseaseReq);
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
