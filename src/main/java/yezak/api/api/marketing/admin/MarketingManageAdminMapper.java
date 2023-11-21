package yezak.api.api.marketing.admin;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.marketing.admin.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MarketingManageAdminMapper {
    List<MarketingManageUserResponse> getUserList(Long hospitalId);

    List<MarketingManageCounselingStatusesResponse> getCounselingStatusesList();
    List<HashMap> getDashboardGenderAgeCount(Map<String,Object> paramMap);

    List<MarketingManageAdminDashboardAccessRootCount> getAccessRootCount(Map<String,Object> paramMap);

    MarketingManageAdminDbCurrentStateCount getDbCurrentStateCount(Map<String,Object> paramMap);

    List<MarketingManageAdminDbChargeCurrentStateCount> getDbChargeCurrentStateCount(Map<String,Object> paramMap);

    void deleteData(String id);

    void insertUserPatient(Map paramMap);

    void updateUserPatient(Map paramMap);

    List<MarketingManageAdminSearchPatientInfo> searchPatient(String param);

    void registPatient(MarketingManageAdminRegistPatientRequest request);

    void registCounselings(MarketingManageAdminRegistPatientRequest request);

    List<AccessRoot> getAccessRootList(Long hospitalId);

    void registPatientCounselingInfos(MarketingManageAdminRegistPatientRequest request);

    List<MarketingManageAdminCounselHistoryInfo> searchPatientCounselingInfo(Long patientId);

    Map<String, Object> getPatientInfo(Integer id);

    List<HashMap> getDbChargeInfo(Long hospitalId);

    HashMap<String, Integer> getCntTest(Integer id);

    Integer getTotalDbCount(Map<String, Object> paramMap);

    Integer getReservationCount(Map<String, Object> paramMap);

    Integer getReservationFeeAmount(Map<String, Object> paramMap);

    MarketingManageAdminSearchPatientInfo searchPatientById(Long id);

    List<MarketingManageAdminCounselHistoryInfo> searchPatientCounselingInfoById(Long id);

//    void registReservationInfo(MarketingManageAdminRegistPatientRequest request);
    void registReservationInfo(MarketingManageRegistReservationInfoRequest request);

    void registReservationFee(Map<String, Object> paramMap);

    List<MarketingManageReservationInfo> reservationInfo(Long patientId);

    void modifyReservationStatus(MarketingManageReservationCancelRequest request);

//    List<MarketingManageHospitalProductInfo> getHospitalProductList(int hospitalId);

    Integer checkDbData(Map<String, Object> map);

    void registPatientInfo(MarketingManageRegistPatientInfoRequest request);

    void registPatientCounselingInfo(MarketingManageRegistPatientInfoRequest request);

    Integer getPatientId(Map<String, Object> map);

    void registCounselingInfo(MarketingManageRegistCounselingInfoRequest request);

    void modifyPatientInfo(MarketingManageModifyPatientInfoRequest request);

    void modifyPatientCounselingInfo(MarketingManageModifyPatientInfoRequest request);

    List<Integer> getPatientCounselingInfosIdList(Long hospitalId);

    Integer checkPatientCounselingInfo(MarketingManageRegistPatientInfoRequest request);

    Integer checkPciInfo(Map<String, Object> checkParamMap);

    void insertUserPatientManual(Map paramMap);
}
