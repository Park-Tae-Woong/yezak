package yezak.api.api.setting.prescription.fee;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.setting.prescription.fee.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FeeMapper {
    List<FeeListRes> getFeeList(FeeListReq feeListReq);
    int getFeeCount(FeeListReq feeListReq);
    List<Map<String, Object>> selectSubdivisionList(Long codeTypeCategoryId);
    List<Map<String, Object>> selectCategoryList();
    FeeDetailRes getFeeDetail (Long id);
    List<Map<String, Object>> applicatedChangeLog(Long id);
    CustomFeeDetailRes getCustomFeeDetail(Long id);
//    List<Map<String, Object>> customApplicatedChangeLog(Long id);

    List<String> customApplicatedDateLog(Long codeId);
    List<Integer> customPriceLog(Long codeId);

    Long getHospitalId(Long id);
    InspectionFeeDetailRes getInspectionFeeDetail(Long id);
    Long findCodeTypeCategoryId(Long id);
    NormalStandardRes normalStandard(Long id);

    List<Map<String, Object>> selectPayTypeList();
    List<Map<String, Object>> selectSpecimen();
    List<Map<String, Object>> selectExaminationType();
    List<Map<String, Object>> selectConsignment();
    InspectionCustomFeeDetailRes getInspectionCustomFeeDetail(Long id);
    SurgeryFeeDetailRes getSurgeryFeeDetail(Long id);
    SurgeryCustomFeeDetailRes getSurgeryCustomFeeDetail(Long id);

    void insertPc(InsertFeeReq insertFeeReq);
    void insertP(InsertFeeReq insertFeeReq);
    void insertPo(InsertFeeReq insertFeeReq);

    void insertInspectionPc(InsertInspectionFeeReq insertInspectionFeeReq);
    void insertInspectionP(InsertInspectionFeeReq insertInspectionFeeReq);
    void insertInspectionPo(InsertInspectionFeeReq insertInspectionFeeReq);

    void insertSurgeryPc(InsertSurgeryReq insertSurgeryReq);
    void insertSurgeryP(InsertSurgeryReq insertSurgeryReq);
    void insertSurgeryPo(InsertSurgeryReq insertSurgeryReq);

    Long findCustomCode(FindCustomCodeReq findCustomCodeReq);

    void deleteFee(Long id);
    void updateLibraryFee(UpdateFeeReq updateFeeReq);
    void updateSelfFeeP(UpdateFeeReq updateFeeReq);
    void updateSelfFeePo(UpdateFeeReq updateFeeReq);

    void updateLibraryInspectionFee(UpdateFeeReq updateFeeReq);
    void updateSelfInspectionFeeP(UpdateFeeReq updateFeeReq);
    void updateSelfInspectionFeePo(UpdateFeeReq updateFeeReq);

    void updateLibrarySurgeryFee(UpdateFeeReq updateFeeReq);
    void updateSelfSurgeryFeeP(UpdateFeeReq updateFeeReq);
    void updateSelfSurgeryFeePo(UpdateFeeReq updateFeeReq);

    Long findPrescriptionCodeIdById(Long id);

    void updateLibraryDelete(Long prescriptionId);
    void updateSelfDelete(@Param("prescriptionId")Long prescriptionId, @Param("hospitalId")Long hospitalId);

}
