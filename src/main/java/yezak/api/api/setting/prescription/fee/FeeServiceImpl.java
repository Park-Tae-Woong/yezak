package yezak.api.api.setting.prescription.fee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.setting.prescription.fee.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
@Transactional
public class FeeServiceImpl implements FeeService {
    private final FeeMapper feeMapper;
    private final int feeId = 63;

    @Override
    public ResultResponse<?> selectCodeTypeList() {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        List<Map<String, Object>> categoryList = feeMapper.selectCategoryList();

        for (Map<String, Object> category : categoryList) {
            Long categoryId = (Long) category.get("value");
            List<Map<String, Object>> subdivisionList = feeMapper.selectSubdivisionList(categoryId);
            category.put("subdivisionList", subdivisionList);
        }

        return ResultResponse.builder()
                .data(categoryList)
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> getFeeList(Long categoryId, Long subdivisionId, String searchValue, Integer pageNum, Integer pageSize) {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        int offset = (pageNum - 1) * pageSize;
        FeeListReq feeListReq = new FeeListReq(categoryId, subdivisionId, searchValue, myHospitalId(), offset, pageSize);

        int totalCount = feeMapper.getFeeCount(feeListReq);
        Page page = Page.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .totalCount(totalCount)
                .totalPage((int) Math.ceil((double) totalCount / pageSize))
                .build();

        return ResultResponse.builder()
                .data(feeMapper.getFeeList(feeListReq))
                .page(page)
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> getFeeDetail(Long id) {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        Long categoryId = feeMapper.findCodeTypeCategoryId(id);
        Long pcodeId = feeMapper.findPrescriptionCodeIdById(id);

        //라이브러리인지 자체등록인지(0이면 라이브러리)
        Long inserted = feeMapper.getHospitalId(id);

        if (categoryId == 2) {
            Map<String, Object> map = new HashMap<>();
            if (inserted == 0) {
                map.put("feeDetail", feeMapper.getInspectionFeeDetail(id));
                map.put("normalStandard", feeMapper.normalStandard(id));
                map.put("applicatedChangeLog", feeMapper.applicatedChangeLog(pcodeId));

            } else {
                map.put("feeDetail", feeMapper.getInspectionCustomFeeDetail(id));
                map.put("normalStandard", feeMapper.normalStandard(id));
                map.put("applicatedChangeDateLog", feeMapper.customApplicatedDateLog(pcodeId));
                map.put("applicatedChangePriceLog", feeMapper.customPriceLog(pcodeId));
            }
            return ResultResponse.builder()
                    .data(map)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else if (categoryId == 9 || categoryId == 10) {
            Map<String, Object> map = new HashMap<>();
            if (inserted == 0) {
                map.put("feeDetail", feeMapper.getSurgeryFeeDetail(id));
                map.put("applicatedChangeLog", feeMapper.applicatedChangeLog(pcodeId));
            } else {
                map.put("feeDetail", feeMapper.getSurgeryCustomFeeDetail(id));
                map.put("applicatedChangeDateLog", feeMapper.customApplicatedDateLog(pcodeId));
                map.put("applicatedChangePriceLog", feeMapper.customPriceLog(pcodeId));
            }
            return ResultResponse.builder()
                    .data(map)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            if (inserted == 0) {
                FeeDetailRes feeDetailRes = feeMapper.getFeeDetail(id);
                List<Map<String, Object>> applicatedChangeLog = feeMapper.applicatedChangeLog(pcodeId);
                Map<String, Object> map = new HashMap<>();
                map.put("feeDetail", feeDetailRes);
                map.put("applicatedChangeLog", applicatedChangeLog);
                return ResultResponse.builder()
                        .data(map)
                        .result(true)
                        .resultCode("200")
                        .build();
            }
            CustomFeeDetailRes customFeeDetailRes = feeMapper.getCustomFeeDetail(id);
            Map<String, Object> map = new HashMap<>();
            map.put("feeDetail", customFeeDetailRes);
            map.put("applicatedChangeDateLog", feeMapper.customApplicatedDateLog(pcodeId));
            map.put("applicatedChangePriceLog", feeMapper.customPriceLog(pcodeId));
            return ResultResponse.builder()
                    .data(map)
                    .result(true)
                    .resultCode("200")
                    .build();

        }
    }

    @Override
    public ResultResponse<?> insertFee(InsertFeeReq insertFeeReq) {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        FindCustomCodeReq findCustomCodeReq = new FindCustomCodeReq(insertFeeReq.getCustomCode(), myHospitalId());
        Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);
        if (customCodeId != null) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                    .build();
        }
        insertFeeReq.setHospitalId(myHospitalId());
        feeMapper.insertPc(insertFeeReq);
        Long pcId = insertFeeReq.getId();
        insertFeeReq.setPrescriptionCodeId(pcId);
        feeMapper.insertP(insertFeeReq);
        feeMapper.insertPo(insertFeeReq);

        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> insertInspectionFee(InsertInspectionFeeReq insertInspectionFeeReq) {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        FindCustomCodeReq findCustomCodeReq = new FindCustomCodeReq(insertInspectionFeeReq.getCustomCode(), myHospitalId());
        Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);
        if (customCodeId != null) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                    .build();
        }
        insertInspectionFeeReq.setHospitalId(myHospitalId());
        feeMapper.insertInspectionPc(insertInspectionFeeReq);
        Long pcId = insertInspectionFeeReq.getId();
        insertInspectionFeeReq.setPrescriptionCodeId(pcId);
        feeMapper.insertInspectionP(insertInspectionFeeReq);
        feeMapper.insertInspectionPo(insertInspectionFeeReq);

        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> insertSurgeryFee(InsertSurgeryReq insertSurgeryReq) {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        FindCustomCodeReq findCustomCodeReq = new FindCustomCodeReq(insertSurgeryReq.getCustomCode(), myHospitalId());
        Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);

        if (customCodeId != null) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                    .build();
        }

        insertSurgeryReq.setHospitalId(myHospitalId());
        feeMapper.insertSurgeryPc(insertSurgeryReq);
        Long pcId = insertSurgeryReq.getId();
        insertSurgeryReq.setPrescriptionCodeId(pcId);
        feeMapper.insertSurgeryP(insertSurgeryReq);
        feeMapper.insertSurgeryPo(insertSurgeryReq);

        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> deleteFee(List<Long> ids) {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

        for (Long id : ids) {
            Long inserted = feeMapper.getHospitalId(id);
            if(inserted == 0) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("등록 주체가 라이브러리인 데이터는 삭제 불가능합니다.")
                        .build();
            }
            feeMapper.deleteFee(id);
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("삭제되었습니다.")
                .build();
    }

    @Override
    @Transactional
    public ResultResponse<?> updateFee(UpdateFeeReq updateFeeReq) {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        //수가 id로 수가 codeId 찾기
        Long prescriptionId = feeMapper.findPrescriptionCodeIdById(updateFeeReq.getId());
        //카테고리
        Long categoryId = feeMapper.findCodeTypeCategoryId(updateFeeReq.getId());
        //자체인지 라이브러리인지
        Long inserted = feeMapper.getHospitalId(updateFeeReq.getId());

        FindCustomCodeReq findCustomCodeReq = new FindCustomCodeReq(updateFeeReq.getCustomCode(), myHospitalId());

        if (categoryId == 2) {
            if(inserted == 0){
                UpdateFeeReq req = UpdateFeeReq.builder()
                        .prescriptionCodeId(prescriptionId)
                        .customCode(updateFeeReq.getCustomCode())
                        .specimenId(updateFeeReq.getSpecimenId())
                        .examinationType(updateFeeReq.getExaminationType())
                        .consignmentId(updateFeeReq.getConsignmentId())
                        .adultMaleMaximum(updateFeeReq.getAdultMaleMaximum())
                        .adultMaleMinimum(updateFeeReq.getAdultMaleMinimum())
                        .adultFemaleMaximum(updateFeeReq.getAdultFemaleMaximum())
                        .adultFemaleMinimum(updateFeeReq.getAdultFemaleMinimum())
                        .childMaximum(updateFeeReq.getChildMaximum())
                        .childMinimum(updateFeeReq.getChildMinimum())
                        .unit(updateFeeReq.getUnit())
                        .hospitalId(myHospitalId())
                        .build();
                feeMapper.updateLibraryDelete(prescriptionId);

                Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);
                if (customCodeId != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }

                feeMapper.updateLibraryInspectionFee(req);
                return ResultResponse.builder()
                        .result(true)
                        .resultCode("200")
                        .resultMessage("수정되었습니다.")
                        .build();
            } else {
                UpdateFeeReq req = UpdateFeeReq.builder()
                        .prescriptionCodeId(prescriptionId)
                        .koName(updateFeeReq.getKoName())
                        .enName(updateFeeReq.getEnName())
                        .applicatedDate(updateFeeReq.getApplicatedDate())
                        .customCode(updateFeeReq.getCustomCode())
                        .uninsuredPrice(updateFeeReq.getUninsuredPrice())
                        .specimenId(updateFeeReq.getSpecimenId())
                        .examinationType(updateFeeReq.getExaminationType())
                        .consignmentId(updateFeeReq.getConsignmentId())
                        .adultMaleMaximum(updateFeeReq.getAdultMaleMaximum())
                        .adultMaleMinimum(updateFeeReq.getAdultMaleMinimum())
                        .adultFemaleMaximum(updateFeeReq.getAdultFemaleMaximum())
                        .adultFemaleMinimum(updateFeeReq.getAdultFemaleMinimum())
                        .childMaximum(updateFeeReq.getChildMaximum())
                        .childMinimum(updateFeeReq.getChildMinimum())
                        .unit(updateFeeReq.getUnit())
                        .hospitalId(myHospitalId())
                        .build();
                feeMapper.updateSelfDelete(updateFeeReq.getId(), myHospitalId());

                Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);
                if (customCodeId != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }
                feeMapper.updateSelfInspectionFeeP(req);
                feeMapper.updateSelfInspectionFeePo(req);
                return ResultResponse.builder()
                        .result(true)
                        .resultCode("200")
                        .resultMessage("수정되었습니다.")
                        .build();
            }
        } else if (categoryId == 9 || categoryId == 10) {
            if (inserted == 0) {
                UpdateFeeReq req = UpdateFeeReq.builder()
                        .prescriptionCodeId(prescriptionId)
                        .customCode(updateFeeReq.getCustomCode())
                        .uninsuredPrice(updateFeeReq.getUninsuredPrice())
                        .isProduct(updateFeeReq.getIsProduct())
                        .estimatedTime(updateFeeReq.getEstimatedTime())
                        .hospitalId(myHospitalId())
                        .build();
                feeMapper.updateLibraryDelete(updateFeeReq.getId());
                Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);
                if (customCodeId != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }
                feeMapper.updateLibrarySurgeryFee(req);
                return ResultResponse.builder()
                        .result(true)
                        .resultCode("200")
                        .resultMessage("수정되었습니다.")
                        .build();
            } else {
                UpdateFeeReq req = UpdateFeeReq.builder()
                        .prescriptionCodeId(prescriptionId)
                        .koName(updateFeeReq.getKoName())
                        .enName(updateFeeReq.getEnName())
                        .applicatedDate(updateFeeReq.getApplicatedDate())
                        .customCode(updateFeeReq.getCustomCode())
                        .uninsuredPrice(updateFeeReq.getUninsuredPrice())
                        .isProduct(updateFeeReq.getIsProduct())
                        .estimatedTime(updateFeeReq.getEstimatedTime())
                        .hospitalId(myHospitalId())
                        .build();
                feeMapper.updateSelfDelete(updateFeeReq.getId(), myHospitalId());

                Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);
                if (customCodeId != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }
                feeMapper.updateSelfSurgeryFeeP(req);
                feeMapper.updateSelfSurgeryFeePo(req);

                return ResultResponse.builder()
                        .result(true)
                        .resultCode("200")
                        .resultMessage("수정되었습니다.")
                        .build();
            }
        } else {
            if (inserted == 0) {
                UpdateFeeReq req = UpdateFeeReq.builder()
                        .customCode(updateFeeReq.getCustomCode())
                        .prescriptionCodeId(prescriptionId)
                        .hospitalId(myHospitalId())
                        .build();
                feeMapper.updateLibraryDelete(updateFeeReq.getId());

                Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);
                if (customCodeId != null) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }
                feeMapper.updateLibraryFee(req);
                return ResultResponse.builder()
                        .result(true)
                        .resultCode("200")
                        .resultMessage("수정되었습니다.")
                        .build();
            } else {
                UpdateFeeReq req = UpdateFeeReq.builder()
                        .prescriptionCodeId(prescriptionId)
                        .koName(updateFeeReq.getKoName())
                        .enName(updateFeeReq.getEnName())
                        .applicatedDate(updateFeeReq.getApplicatedDate())
                        .customCode(updateFeeReq.getCustomCode())
                        .hospitalId(myHospitalId())
                        .uninsuredPrice(updateFeeReq.getUninsuredPrice())
                        .build();
                feeMapper.updateSelfDelete(updateFeeReq.getId(), myHospitalId());
                Long customCodeId = feeMapper.findCustomCode(findCustomCodeReq);

                if (customCodeId != null) {
                    System.out.println("asdasdf");
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                            .build();
                }

                feeMapper.updateSelfFeeP(req);
                feeMapper.updateSelfFeePo(req);
                return ResultResponse.builder()
                        .result(true)
                        .resultCode("200")
                        .resultMessage("수정되었습니다.")
                        .build();
            }
        }
    }

    @Override
    public ResultResponse<?> selectList() {
        if(!myDepth3Id().contains(feeId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("payTypeList", feeMapper.selectPayTypeList());
        selectMap.put("specimenList", feeMapper.selectSpecimen());
        selectMap.put("consignmentList", feeMapper.selectConsignment());
        selectMap.put("examinationTypeList", feeMapper.selectExaminationType());
        return ResultResponse.builder()
                .data(selectMap)
                .result(true)
                .resultCode("200")
                .build();
    }
}
