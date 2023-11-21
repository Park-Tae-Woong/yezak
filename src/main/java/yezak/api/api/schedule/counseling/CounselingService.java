package yezak.api.api.schedule.counseling;

import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.schedule.counseling.dto.*;
import yezak.api.global.common.ResultResponse;

public interface CounselingService {

    /**
     * 대면상담실 리스트(셀렉트박스용) 조회
     */
    ResultResponse selectCounselingRoomList(Long hospitalId);

    /**
     * 환자 조회
     */
    ResultResponse selectPatients(SearchPatientsRequest searchPatientsRequest);

    /**
     * 대기리스트 조회
     */
    ResultResponse selectWaitingList(Long roomId, WaitingListRequest waitingListRequest);

    /**
     * 대기리스트 상태 업데이트
     */
    ResultResponse updateWaitingStatus(UpdateWaitingStatusRequest updateWaitingStatusRequest);

    /**
     * 가족력, 바이탈 정보 조회
     */
    ResultResponse selectPatientInfo(Long id);

    /**
     * 바이탈 과거이력 조회(모달용)
     */
    ResultResponse selectVitalHistoryList(Long id);

    /**
     * 바이탈 등록
     */
    ResultResponse insertPatientVital(Long id, PatientVitalRequest[] patientVitalRequest);

    /**
     * 병원 상품 조회
     */
    ResultResponse selectProducts(SearchProductsRequest searchProductsRequest);

    /**
     * 방 리스트(셀렉트박스용) 조회
     */
    ResultResponse selectRoomList(Long hospitalId);

    /**
     * 대면상담 저장
     */
    ResultResponse insertCounseling(CounselingRequest counselingRequest);

    /**
     * 대면상담 이미지 업로드
     */
    ResultResponse uploadCounselingImages(MultipartFile file);

    /**
     * 상담 차트이력 조회
     */
    ResultResponse selectCounselingHistoryList(Long id);

    /**
     * 상담 내용 업데이트
     */
    ResultResponse updateCounselingContent(Long id, UpdateCounselingRequest updateCounselingRequest);
}
