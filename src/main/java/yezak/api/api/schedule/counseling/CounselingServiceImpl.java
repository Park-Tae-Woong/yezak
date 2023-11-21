package yezak.api.api.schedule.counseling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.schedule.counseling.dto.*;
import yezak.api.domain.schedule.counseling.CounselingsTbl;
import yezak.api.domain.schedule.counseling.PatientVitalSignsTbl;
import yezak.api.global.common.ResultResponse;
import yezak.api.global.error.ErrorCode;
import yezak.api.util.FileService;

import static yezak.api.config.MyIdConfig.myHospitalId;
import static yezak.api.config.MyIdConfig.myUserId;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class CounselingServiceImpl implements CounselingService {
    private final CounselingMapper counselingMapper;

    private final FileService fileService;

    /**
     * 대면상담실 리스트 조회
     * @param hospitalId
     * @return
     */
    @Override
    public ResultResponse selectCounselingRoomList(Long hospitalId) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {
            hospitalId = myHospitalId();

            List<RoomListResponse> roomList = counselingMapper.selectCounselingRoomList(hospitalId);

            result.setData(roomList);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 환자 조회
     * @param searchPatientsRequest
     * @return
     */
    @Override
    public ResultResponse selectPatients(SearchPatientsRequest searchPatientsRequest) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {
            Long hospitalId = myHospitalId();
            searchPatientsRequest.setHospitalId(hospitalId);
            List<PatientsResponse> patientsList = counselingMapper.selectPatients(searchPatientsRequest);

            result.setData(patientsList);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 대기리스트 조회
     * @param roomId, waitingListRequest
     * @return
     */
    @Override
    public ResultResponse selectWaitingList(Long roomId, WaitingListRequest waitingListRequest) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {
            WaitingListResponse waitingListResponse = new WaitingListResponse();

            waitingListRequest.setRoomId(roomId);

            // 상담실별 대기 리스트 조회
            List<WaitingResponse> waitingList = counselingMapper.selectWaitingList(waitingListRequest);
            waitingListResponse.setWaitingList(waitingList);

            List<StatusListResponse> statusList = counselingMapper.selectStatusList();
            waitingListResponse.setStatusList(statusList);

            result.setData(waitingListResponse);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 대기리스트 상태 업데이트
     * @param updateWaitingStatusRequest
     * @return
     */
    @Override
    public ResultResponse updateWaitingStatus(UpdateWaitingStatusRequest updateWaitingStatusRequest) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {

            Long presentStatusId = counselingMapper.selectPresentStatus(updateWaitingStatusRequest.getPatientId());
            Long toUpdateStatusId = updateWaitingStatusRequest.getStatusId();

            if (presentStatusId != toUpdateStatusId) {
                counselingMapper.updateWaitingStatus(updateWaitingStatusRequest);
            } else {
                result.setResultCode(ErrorCode.DATA_ERROR.getKey());
                result.setResultMessage("현재 상태와 같은 상태로 업데이트할 수 없습니다.");
            }

            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");

        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 환자 정보(가족력/바이탈 정보) 조회
     * @param id
     * @return
     */
    @Override
    public ResultResponse selectPatientInfo(Long id) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {

            Long hospitalId = myHospitalId();

            PatientInfoResponse patientInfo = new PatientInfoResponse();

            // 환자정보, 가족력 조회
            NameInheritFactor nameInheritFactor = counselingMapper.selectNameAndInheritFactor(id);

            if (nameInheritFactor != null) {
                patientInfo.setNameInheritFactor(nameInheritFactor);
            }

            // 바이탈 컬럼 리스트 조회
            List<VitalSignColumns> vitalSignColumns = counselingMapper.selectVitalColumns(hospitalId);
            patientInfo.setVitalSignColumns(vitalSignColumns);

            // 오늘 바이탈 정보 조회
            List<PatientVitalSignsResponse> todaysVital = counselingMapper.selectTodaysVital(id);
            if (todaysVital != null) {
                patientInfo.setTodaysVital(todaysVital);
            }

            result.setData(patientInfo);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 바이탈 과거이력 조회(모달용)
     * @param id
     * @return
     */
    @Override
    public ResultResponse selectVitalHistoryList(Long id) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {

            List<VitalHistoryResponse> vitalHistoryList = counselingMapper.selectVitalHistory(id);

            List<Map<String, Object>> historyList = new ArrayList<>();
            Map<String, Object> historyMap = new HashMap<>();
            String checkCreatedAt = "";
            int checkNum = 0;

            // patient_vital_signs 하나씩 꺼내기
            for (VitalHistoryResponse vitalHistory : vitalHistoryList) {
                checkNum++;

                // 다른 일자
                if(!checkCreatedAt.equals(vitalHistory.getCreatedAt()) && !checkCreatedAt.equals("")) {
                    // 밑에서 담은 historyMap add 하고 historyMap 초기화
                    historyList.add(historyMap);
                    historyMap = new HashMap<>();
                }

                // 첫번째
                if(!checkCreatedAt.equals(vitalHistory.getCreatedAt())) {
                    checkCreatedAt = vitalHistory.getCreatedAt();
                    historyMap.put("createdAt", vitalHistory.getCreatedAt());
                    historyMap.put(String.valueOf(vitalHistory.getVitalId()), vitalHistory.getValue());
                } else {
                    // 두번째~
                    historyMap.put(String.valueOf(vitalHistory.getVitalId()), vitalHistory.getValue());
                }

                // 실제 vitalHistoryList 사이즈와 똑같아질 때 마지막 map도 add
                if (checkNum == vitalHistoryList.size()) {
                    historyList.add(historyMap);
                }

            }

            result.setData(historyList);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 오늘 바이탈 등록
     * @param id, patientVitalRequest
     * @return
     */
    @Override
    public ResultResponse insertPatientVital(Long id, PatientVitalRequest[] patientVitalRequest) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {

            // 오늘 진료기록 유무(>0) 조회
            int mrCnt = 0;
            mrCnt = counselingMapper.selectTodayMedicalRecord(id);

            if (mrCnt > 0) {
                Long mrId = counselingMapper.selectRecentMedicalRecordId(id);

                for (PatientVitalRequest request : patientVitalRequest) {
                    PatientVitalSignsTbl patientVitalSignsTbl = PatientVitalSignsTbl.builder()
                            .vitalSignMasterName(request.getVitalSignMasterName())
                            .medicalRecordId(mrId)
                            .value(request.getValue())
                            .build();

                    counselingMapper.insertPatientVital(patientVitalSignsTbl);
                }

                result.setResult(true);
                result.setResultCode("200");
                result.setResultMessage("성공");
            } else {
                result.setResultCode(ErrorCode.DATA_ERROR.getKey());
                result.setResultMessage("진료 기록이 없어 바이탈 등록이 불가합니다.");
            }

        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 환자 조회
     * @param searchProductsRequest
     * @return
     */
    @Override
    public ResultResponse selectProducts(SearchProductsRequest searchProductsRequest) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {
            Long hospitalId = myHospitalId();
            searchProductsRequest.setHospitalId(hospitalId);
            List<ProductsResponse> products = counselingMapper.selectProducts(searchProductsRequest);

            result.setData(products);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 방 리스트(셀렉트박스용) 조회
     * @param hospitalId
     * @return
     */
    @Override
    public ResultResponse selectRoomList(Long hospitalId) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {
            hospitalId = myHospitalId();

            List<RoomListResponse> roomList = counselingMapper.selectRoomList(hospitalId);

            result.setData(roomList);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 상담 등록
     * @param counselingRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultResponse insertCounseling(CounselingRequest counselingRequest) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {

            Long patientId = counselingRequest.getPatientId();

            // 1. 상담내용, 시/수술 저장
            // TODO: 상품 검색

            String askedProduct = counselingRequest.getAskedProduct();
            if (askedProduct == null || askedProduct.equals("")) {
                askedProduct = null;
            };

            CounselingsTbl counselingsTbl = CounselingsTbl.builder()
                    .patientsId(patientId)
                    .userId(myUserId())
                    .askedProduct(askedProduct)
                    .content(counselingRequest.getContent())
                    .build();

            counselingMapper.insertCounseling(counselingsTbl);

            // 2. 예약 insert
            // TODO: 방 조회
            Long reservedRoomId = counselingRequest.getReservedRoomId();
            if (reservedRoomId != null && !reservedRoomId.equals("")) {
                ReservationRequest reservationRequest = new ReservationRequest();
                reservationRequest.setPatientId(patientId);
                reservationRequest.setRoomId(reservedRoomId);
                reservationRequest.setReservatedAt(Timestamp.valueOf(counselingRequest.getReservatedAt()));

                counselingMapper.insertReservation(reservationRequest);
            }

            // 3. 접수 update
            // TODO: 방, 담당자, 상태 조회
            Long sendRoomId = counselingRequest.getSendRoomId();
            if (sendRoomId != null && !sendRoomId.equals("")) {
                UpdateReceptionRequest updateReceptionRequest = new UpdateReceptionRequest();
                updateReceptionRequest.setPatientId(patientId);
                updateReceptionRequest.setRoomId(sendRoomId);
                // TODO: 담당자 추가

                updateReceptionRequest.setStatusId(counselingRequest.getStatusId());
                updateReceptionRequest.setUpdatedUserId(myUserId());
                counselingMapper.updateReception(updateReceptionRequest);
            }

            // TODO: 4.이미지 업로드 후 이미지 attachments 테이블에 insert

            result.setData(counselingsTbl);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 대면상담 이미지 업로드
     * @param file
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultResponse uploadCounselingImages(MultipartFile file) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {

//            for (MultipartFile file : files) {
//                ImageFormAttachmentDto image = fileService.saveImageFile(file);
//
//                counselingMapper.insertCounselingImage(image);
//            }

            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 상담 차트이력 조회
     * @param id
     * @return
     */
    @Override
    public ResultResponse selectCounselingHistoryList(Long id) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {
            List<CounselingHistoryResponse> counselingHistoryList = counselingMapper.selectCounselingHistoryList(id);

            result.setData(counselingHistoryList);
            result.setResult(true);
            result.setResultCode("200");
            result.setResultMessage("성공");
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 상담 내용 업데이트
     * @param updateCounselingRequest
     * @return
     */
    @Override
    public ResultResponse updateCounselingContent(Long id, UpdateCounselingRequest updateCounselingRequest) {
        ResultResponse result = new ResultResponse();
        result.setResult(false);

        try {

            updateCounselingRequest.setId(id);

            boolean todayYn = counselingMapper.selectCounselingCreatedAtTodayYn(id);

            if (todayYn == true) {
                counselingMapper.updateCounselingContent(updateCounselingRequest);

                result.setResult(true);
                result.setResultCode("200");
                result.setResultMessage("성공");
            } else {
                result.setResultCode(ErrorCode.BAD_REQUEST.getKey());
                result.setResultMessage("오늘 작성한 상담 내용만 업데이트가 가능합니다.");
            }
        } catch (Exception e) {
            result.setResultCode("500");
            result.setResultMessage(e.getMessage());
        }

        return result;
    }

}
