package yezak.api.api.setting.prescription.drug;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.setting.prescription.drug.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Transactional
@Slf4j
public class DrugServiceImpl implements DrugService {
    private final DrugMapper drugMapper;
    private final Integer drugId = 64;

    @Override
    public ResultResponse<?> drugList(String dosageForm, String searchValue, Integer pageNum, Integer pageSize) {
        if(myDepth3Id().contains(drugId)) {
            int offset = (pageNum - 1) * pageSize;
            DrugListReq drugListReq = new DrugListReq(dosageForm, searchValue, myHospitalId(), offset, pageSize);

            int totalCount = drugMapper.countDrug(drugListReq);
            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double)totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(drugMapper.drugList(drugListReq))
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
    public ResultResponse<?> drugDetail(Long id) {
        if(myDepth3Id().contains(drugId)) {
            Long drugCodeId = drugMapper.findCodeAndHosByDrugId(id).getDrugCodeId();
            Long hospitalId = drugMapper.findHospitalId(id);

            if (hospitalId == 0) {
                SystemDrugDetailRes systemDrugDetailRes = drugMapper.systemDrugDetail(id);

                List<MaxValueChangeLogRes> maxValueChangeLogRes = drugMapper.maxValueChangeLog(drugCodeId);

                List<String> dateList = new ArrayList<>();
                for (MaxValueChangeLogRes date : maxValueChangeLogRes) {
                    String applicated = date.getApplicatedDate();
                    dateList.add(applicated);
                }

                List<Integer> priceList = new ArrayList<>();
                for (MaxValueChangeLogRes price : maxValueChangeLogRes) {
                    Integer prices = price.getMaxValue();
                    priceList.add(prices);
                }

                Map<String, Object> map = new HashMap<>();
                map.put("drugDetailRes", systemDrugDetailRes);
                map.put("dateChangeLog", dateList);
                map.put("priceChangeLog", priceList);

                drugMapper.maxValueChangeLog(drugCodeId);
                return ResultResponse.builder()
                        .data(map)
                        .result(true)
                        .resultCode("200")
                        .resultMessage("성공")
                        .build();
            } else {
                MyHospitalDrugDetailRes myHospitalDrugDetailRes = drugMapper.myHospitalDrugDetail(id);
                if (myHospitalDrugDetailRes == null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("Id is null")
                            .build();
                }

                Map<String, Object> map = new HashMap<>();
                map.put("drugDetailRes", myHospitalDrugDetailRes);
                map.put("dateChangeLog", drugMapper.dateChangeLog(drugCodeId));
                map.put("priceChangeLog", drugMapper.priceChangeLog(drugCodeId));
                return ResultResponse.builder()
                        .data(map)
                        .result(true)
                        .resultCode("200")
                        .resultMessage("성공")
                        .build();
            }

        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    @Transactional
    public ResultResponse<?> createDrug(InsertDrugReq insertDrugReq) {
        if(myDepth3Id().contains(drugId)) {
            FindCustomCode findCustomCode = new FindCustomCode(myHospitalId(), insertDrugReq.getCustomCode());
            FindDrugCode findDrugCode = new FindDrugCode(insertDrugReq.getCode(), myHospitalId());

            Long customCodeId = drugMapper.findCustomCode(findCustomCode);
            Long drugCodeId = drugMapper.findDrugCode(findDrugCode);

            if (customCodeId != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                        .build();
            }
            if (drugCodeId != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("약품코드가 중복됩니다.\n약품코드를 변경해주세요.")
                        .build();
            }
            insertDrugReq.setHospitalId(myHospitalId());

            drugMapper.drugCode(insertDrugReq);
            Long drugId = insertDrugReq.getId();
            insertDrugReq.setDrugCodeId(drugId);
            drugMapper.insertDrug(insertDrugReq);
            drugMapper.drugOption(insertDrugReq);

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

    @Override
    public ResultResponse<?> deleteDrug(List<Long> ids) {
        if(!myDepth3Id().contains(drugId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

        for (Long id : ids) {
            Long hospitalId = drugMapper.findHospitalId(id);
            if(hospitalId == 0) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("등록 주체가 라이브러리인 데이터는 삭제 불가능합니다.")
                        .build();
            }
            drugMapper.deleteDrug(id, myHospitalId());
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("삭제되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> updateDrug(UpdateDrugReq updateDrugReq) {
        if(myDepth3Id().contains(drugId)) {
            //라이브러리인지
            Long hospitalId = drugMapper.findHospitalId(updateDrugReq.getId());
            //코드id, 병원id 찾기
            FindCodeAndHosByDrugIdRes findCodeAndHosByDrugIdRes = drugMapper.findCodeAndHosByDrugId(updateDrugReq.getId());
            //사용자코드만 변경이므로 조회한 약가코드id를 set
            updateDrugReq.setDrugCodeId(findCodeAndHosByDrugIdRes.getDrugCodeId());
            if (hospitalId == 0){
                updateDrugReq.setHospitalId(myHospitalId());

                FindCustomCode findCustomCode = new FindCustomCode(myHospitalId(), updateDrugReq.getCustomCode());

                drugMapper.deleteOldOption(findCodeAndHosByDrugIdRes.getDrugCodeId(), myHospitalId());

                if (drugMapper.findCustomCode(findCustomCode) != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }

                drugMapper.updateSystemDrug(updateDrugReq);
            } else {
                updateDrugReq.setHospitalId(findCodeAndHosByDrugIdRes.getHospitalId());

                FindCustomCode findCustomCode = new FindCustomCode(myHospitalId(), updateDrugReq.getCustomCode());

                Long drugId = findCodeAndHosByDrugIdRes.getDrugCodeId();
                drugMapper.deleteOld(drugId, myHospitalId());
                if (drugMapper.findCustomCode(findCustomCode) != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }
                drugMapper.updateDrug(updateDrugReq);
                drugMapper.updateDrugOption(updateDrugReq);
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

