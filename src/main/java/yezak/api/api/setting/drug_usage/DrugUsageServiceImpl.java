package yezak.api.api.setting.drug_usage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yezak.api.api.setting.drug_usage.dto.DrugUsageListReq;
import yezak.api.api.setting.drug_usage.dto.FindByCustomCode;
import yezak.api.api.setting.drug_usage.dto.InsertDrugUsageReq;
import yezak.api.api.setting.drug_usage.dto.UpdateDrugUsageReq;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class DrugUsageServiceImpl implements DrugUsageService{
    private final DrugUsageMapper drugUsageMapper;
    private final Integer drugUsageId = 66;

    @Override
    public ResultResponse<?> drugUsageList(Long drugUsageTypeId, String searchValue, Integer pageNum, Integer pageSize) {
        if(myDepth3Id().contains(drugUsageId)) {
            int offset = (pageNum - 1) * pageSize;
            DrugUsageListReq drugUsageListReq = new DrugUsageListReq(drugUsageTypeId, searchValue, myHospitalId(), offset, pageSize);

            int totalCount = drugUsageMapper.countDrugUsage(drugUsageListReq);
            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double)totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(drugUsageMapper.drugUsageList(drugUsageListReq))
                    .select(drugUsageMapper.selectUsageType())
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
    public ResultResponse<?> updateUsage(UpdateDrugUsageReq updateDrugUsageReq) {

        if(myDepth3Id().contains(drugUsageId)) {
            FindByCustomCode findByCustomCode = new FindByCustomCode(myHospitalId(), updateDrugUsageReq.getCustomCode());
            Long check = drugUsageMapper.findByCustomCode(findByCustomCode);

            if(check != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("사용자코드가 중복됩니다. 사용자코드를 변경해주세요.")
                        .build();
            }
            drugUsageMapper.updateUsage(updateDrugUsageReq);
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
    public ResultResponse<?> insertUsage(InsertDrugUsageReq insertDrugUsageReq) {

        if (myDepth3Id().contains(drugUsageId)) {
            InsertDrugUsageReq req = InsertDrugUsageReq.builder()
                    .customCode(insertDrugUsageReq.getCustomCode())
                    .drugUsageTypeId(insertDrugUsageReq.getDrugUsageTypeId())
                    .content(insertDrugUsageReq.getContent())
                    .hospitalId(myHospitalId())
                    .build();

            FindByCustomCode findByCustomCode = new FindByCustomCode(myHospitalId(), insertDrugUsageReq.getCustomCode());
            Long check = drugUsageMapper.findByCustomCode(findByCustomCode);

            if(check != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("사용자코드가 중복됩니다. 사용자코드를 변경해주세요.")
                        .build();
            }
            drugUsageMapper.insertUsage(req);
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
