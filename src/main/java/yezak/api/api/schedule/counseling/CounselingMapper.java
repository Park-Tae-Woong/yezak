package yezak.api.api.schedule.counseling;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.schedule.counseling.dto.*;
import yezak.api.domain.schedule.counseling.CounselingAttachmentsTbl;
import yezak.api.domain.schedule.counseling.CounselingsTbl;
import yezak.api.domain.schedule.counseling.PatientVitalSignsTbl;

import java.util.List;

@Mapper
public interface CounselingMapper {

    /** 대면상담실 리스트(셀렉트박스) 조회 **/
    List<RoomListResponse> selectCounselingRoomList(Long hospitalId);

    /** 환자 조회 **/
    List<PatientsResponse> selectPatients(SearchPatientsRequest searchPatientsRequest);

    /** 대기리스트 조회 **/
    List<WaitingResponse> selectWaitingList(WaitingListRequest waitingListRequest);

    /** 상태 리스트(셀렉트박스) 조회 **/
    List<StatusListResponse> selectStatusList();

    /** 현재 대기리스트 상태 조회 **/
    Long selectPresentStatus(Long patientId);

    /** 대기리스트 상태 업데이트 **/
    void updateWaitingStatus(UpdateWaitingStatusRequest updateWaitingStatusRequest);

    /** 가족력 조회 **/
    NameInheritFactor selectNameAndInheritFactor(Long patientId);

    /** 바이탈 컬럼 조회 **/
    List<VitalSignColumns> selectVitalColumns(Long hospitalId);

    /** 오늘 바이탈 정보 조회 **/
    List<PatientVitalSignsResponse> selectTodaysVital(Long patientId);

    /** 가장 최근 진료 기록 id 조회 **/
    Long selectRecentMedicalRecordId(Long patientId);

    /** 바이탈 과거이력 조회 **/
    List<VitalHistoryResponse> selectVitalHistory(Long patientId);

    /** 오늘 진료기록 유무 조회 **/
    int selectTodayMedicalRecord(Long patientId);

    /** 오늘 바이탈 등록 **/
    void insertPatientVital(PatientVitalSignsTbl patientVitalSignsTbl);

    /** 대면상담 저장 **/
    /** 상품 리스트 조회 **/
    List<ProductsResponse> selectProducts(SearchProductsRequest searchProductsRequest);

    /** 방 리스트 조회 **/
    List<RoomListResponse> selectRoomList(Long hospitalId);

    /** 상담 내용 insert **/
    void insertCounseling(CounselingsTbl counselingsTbl);

    /** 일정 예약 insert **/
    void insertReservation(ReservationRequest reservationRequest);

    /** 접수 상태 update **/
    void updateReception(UpdateReceptionRequest updateReceptionRequest);

    /** 이미지 insert **/
    void insertCounselingImage(CounselingAttachmentsTbl counselingAttachmentsTbl);

    /** 상담 차트이력 조회 **/
    List<CounselingHistoryResponse> selectCounselingHistoryList(Long patientId);

    /** 상담 내용 update 시 오늘 등록된 상담 내용인지 조회 **/
    Boolean selectCounselingCreatedAtTodayYn(Long id);

    /** 상담 내용 update **/
    void updateCounselingContent(UpdateCounselingRequest updateCounselingRequest);

}
