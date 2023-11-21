package yezak.api.api.reception;

import yezak.api.api.reception.dto.*;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface ReceptionService {
    ResultResponse selectUserInfoTest(List<ReceptionSujinjaSearchRequest> request);

    ResultResponse selectPatientsInfo(String searchKeyword);

    ResultResponse selectTodayReceptionList(String gubun);

    ResultResponse selectTodayHospitalStateList(Long roomId);

    ResultResponse selectRoomList();

    ResultResponse updateRoom(UpdateRoomReq updateRoomReq);
    ResultResponse outHospital(Long id);

    ResultResponse updateStatus(UpdateStatusReq updateStatusReq);
    ResultResponse deleteReception(Long id);
    ResultResponse waitReceptionList();

    ResultResponse newPatient(NewPatientReq newPatientReq);

    ResultResponse recentReceptionRecord(Long patientId, String start, String end);
    ResultResponse patientInfo(Long patientId);
    ResultResponse receptionInfo(Long receptionId);
    ResultResponse reservationInfo(Long patientId);
    ResultResponse updatePatientInfo(UpdatePatientInfoReq updatePatientInfoReq);
    ResultResponse updateReceptionInfo(UpdateReceptionInfoReq updateReceptionInfoReq);
    ResultResponse patientLogList(Long patientId);
    ResultResponse insertPatientMemo(InsertPatientMemoReq insertPatientMemoReq);
    ResultResponse deletePatientMemo(Long memoId);
    ResultResponse updatePatientMemo(UpdatePatientMemoReq updatePatientMemoReq);
    ResultResponse patientMemoList(Long patientId);




}
