package yezak.api.api.reception;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.reception.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReceptionMapper {

    List<ReceptionPatientInfo> selectPatientsInfo(String searchKeyword);

    List<TodayReceptionInfo> selectTodayReceptionList(TodayReceptionReq todayReceptionReq);

    List<TodayHospitalStateInfo> selectTodayHospitalStateList(@Param("roomId") Long roomId, @Param("hospitalId") Long hospitalId);

    List<ReceptionRoomListInfo> selectRoomList();

    void updateRoom(UpdateRoomReq updateRoomReq);
    void outHospital(Long id);
    void updateStatus(UpdateStatusReq updateStatusReq);
    void deleteReception(Long id);

    List<Map<String, Object>> selectStatusList();
    List<WaitReceptionListRes> waitReceptionList(Long hospitalId);

    Long findReceptionId(Long patientId);

    Long findPatientByNameAndRegisNum(@Param("name") String name, @Param("firstRegistrationNumber") String firstRegistrationNumber, @Param("secondRegistrationNumber") String secondRegistrationNumber);
    void newPatient(NewPatientReq newPatientReq);

    List<RecentReceptionRecordRes> recentReceptionRecord (RecentReceptionRecordReq recentReceptionRecordReq);

    PatientInfoRes patientInfo(Long patientId);
    ReceptionInfoRes receptionInfo(@Param("receptionId") Long receptionId, @Param("hospitalId") Long hospitalId);
    ReservationInfoRes reservationInfo(Long patientId);

    void updatePatientInfo(UpdatePatientInfoReq updatePatientInfoReq);
    void updateReceptionInfo(UpdateReceptionInfoReq updateReceptionInfoReq);
    List<Map<String, Object>> selectProtectorRelation();
    List<Map<String, Object>> selectDoctorList(Long hospitalId);
    void createPatientLog (CreatePatientLogReq createPatientLogReq);
    List<PatientLogListRes> patientLogList(Long patientId);

    void insertPatientMemo(InsertPatientMemoReq insertPatientMemoReq);
    void deletePatientMemo(@Param("memoId") Long memoId, @Param("userId") Long userId);
    void updatePatientMemo(UpdatePatientMemoReq updatePatientMemoReq);
    List<PatientMemoListRes> patientMemoList(@Param("userId")Long userId, @Param("patientId")Long patientId);
}
