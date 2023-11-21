package yezak.api.api.marketing.admin;

import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.marketing.admin.dto.*;
import yezak.api.global.common.ResultResponse;

public interface MarketingManageAdminService {
    ResultResponse<?> getUserList();

    ResultResponse<?> getTotalDbCount(String startDate, String endDate);

    ResultResponse<?> getReservationCount(String startDate, String endDate);

    ResultResponse<?> getReservationFeeAmount(String startDate, String endDate);

    ResultResponse<?> getCounselingStatusesList();

    ResultResponse<?> deleteData(String idArr);

    ResultResponse<?> setManualUserPatient(MarketingManageAdminSetManualUserPatientRequest request);

    ResultResponse<?> searchPatient(String param);

//    void registPatient(MarketingManageAdminRegistPatientRequest request);

    ResultResponse<?> getAccessRootList();

    ResultResponse<?> setAutoUserPatient(MarketingManageAdminSetAutoUserPatientRequest request);

    ResultResponse<?> getGenderAgeCount(String startDate, String endDate);

    ResultResponse<?> getAccessRootCount(String startDate, String endDate);

    ResultResponse<?> getDbCurrentStateCount(String startDate, String endDate);

    ResultResponse<?> getDbChargeStateCount(String startDate, String endDate);

    ResultResponse<?> getPatientDetail(Long id);

    ResultResponse<?> modifyReservationStatus(MarketingManageReservationCancelRequest request);

    ResultResponse<?> excelUpload(MultipartFile file);

//    String getExcelFile();

    ResultResponse<?> registPatientInfo(MarketingManageRegistPatientInfoRequest request);

    ResultResponse<?> registCounselingInfo(MarketingManageRegistCounselingInfoRequest request);

    ResultResponse<?> registReservationInfo(MarketingManageRegistReservationInfoRequest request);

    ResultResponse<?> modifyPatientInfo(MarketingManageModifyPatientInfoRequest request);

//    List<MarketingManageHospitalProductInfo> getHospitalProductList(int hospitalId);
}
