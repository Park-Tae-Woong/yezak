package yezak.api.api.admin.hospital;

import yezak.api.api.admin.hospital.dto.OfficeHourRequest;
import yezak.api.api.admin.hospital.dto.OvertimeReq;
import yezak.api.api.admin.hospital.dto.UpdateHospitalInfoReq;
import yezak.api.api.admin.hospital.dto.VisitPurposeMulti;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface HospitalService {
    ResultResponse<?> hospitalInfo();

    ResultResponse<?> updateHospitalInfo (UpdateHospitalInfoReq updateHospitalInfoReqs);

    ResultResponse<?> officeHourInfo();

    ResultResponse<?> updateOfficeHour(OfficeHourRequest officeHourReq);

    ResultResponse<?> overtimeInfo();

    ResultResponse<?> updateOvertime(List<OvertimeReq> overtimeReqs);

    ResultResponse<?> visitPurposeInfo();

    ResultResponse<?> visitPurposeMulti(VisitPurposeMulti visitPurposeMulti);
//    void processVisitPurposeRegist(InsertVisitPurposeReq insertVisitPurposeReq);
//
//    void processVisitPurposeModify(List<UpdateVisitPurposeReq> updateVisitPurposeReqs);
//
//    void processVisitPurposeDelete(Long id);
}
