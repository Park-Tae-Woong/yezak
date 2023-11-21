package yezak.api.api.reception;

//import kr.or.nhic.api.services.nhic.NhicLocator;
//import kr.or.nhic.api.services.nhic.NhicPort;
import com.amazonaws.services.directory.model.ServiceException;
import kr.or.nhic.api.services.nhic.NhicLocator;
import kr.or.nhic.api.services.nhic.NhicPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
//import vo.maint.yos.wg.nhmp.Msg1Vo;
//import vo.maint.yos.wg.nhmp.Msg2Vo;
import org.springframework.transaction.annotation.Transactional;
import vo.maint.yos.wg.nhmp.Msg1Vo;
import vo.maint.yos.wg.nhmp.Msg2Vo;
import yezak.api.api.reception.dto.*;
import yezak.api.global.common.ResultResponse;

//import javax.xml.rpc.ServiceException;
//import java.rmi.RemoteException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.myHospitalId;
import static yezak.api.config.MyIdConfig.myUserId;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ReceptionServiceImpl implements ReceptionService {

    private final ReceptionMapper mapper;

    private final String Y_KIHO = "12333573"; //요양기관번호
    private final String PGM_TYPE = "3"; //프로그램 타입
    private final String MSG_TYPE = "M1"; //메세지 타입

    @Override
    public ResultResponse selectUserInfoTest(List<ReceptionSujinjaSearchRequest> request) {

        Msg1Vo[] msg1Arr = new Msg1Vo[request.size()];

        for(int i=0; i<request.size(); i++) {
            Msg1Vo vo = new Msg1Vo();
            vo.setSujinjaJuminNm(request.get(i).getSujinjaJuminNm());
            vo.setSujinjaJuminNo(request.get(i).getSujinjaJuminNo());
            vo.setYkiho(Y_KIHO);
            vo.setDate(LocalDateTime.now().toString());
            vo.setPgmType(PGM_TYPE);
            vo.setMsgType(MSG_TYPE);
            msg1Arr[i] = vo;
        }

        try {
            NhicLocator locator = new NhicLocator("http://api.nhic.or.kr:1443/services/nhic?WSDL"); //공단 운영url
//          NhicLocator locator = new NhicLocator("http://ws.nhic.or.kr:1442/services/nhic?WSDL");//공단 개발url
            NhicPort port = (locator).getnhicPort();

    //		String [] result = port.test("test");
    //      Msg2Vo[] result = port.requestQualification(vo);
            Msg2Vo[] result = port.requestQualificationList(msg1Arr);//수진자조회
    //		Msg4Vo [] result = port.requestApprovalList(null);
    //		Msg6Vo [] result = port.cancelApprovalList(null);
    //		boolean result = port.getisKJCall();
    //		String result = port.doNotUseThisMethod(vo);
    //		Msg4Vo result = port.requestApproval(new Msg3Vo());
    //		Msg6Vo result = port.cancelApproval(new Msg5Vo());
    //		port.setisKJCall(true);

            List<HealthInsuranceSujinjaInfo> healthInsuranceSujinjaInfoList = new ArrayList<>();
            List<MedicalBenefitsSujinjaInfo> medicalBenefitsSujinjaInfoList = new ArrayList<>();
            for (Msg2Vo msg2Vo : result) {
                if(Integer.parseInt(msg2Vo.getQlfType()) > 6) {
                    //의료급여 7 or 8
                    medicalBenefitsSujinjaInfoList.add(MedicalBenefitsSujinjaInfo.builder()
                                    .sujinjaJuminNo(msg2Vo.getSujinjaJuminNo())
                                    .sujinjaJuminNm(msg2Vo.getSujinjaJuminNm())
                                    .ykiho(msg2Vo.getYkiho())
                                    .qlfType(msg2Vo.getQlfType())
                                    .qlfChwidukDt(msg2Vo.getQlfChwidukDt())
                                    .sedaejuNm(msg2Vo.getSedaejuNm())
                                    .protAdminSym(msg2Vo.getProtAdminSym())
                                    .asylmSym(msg2Vo.getAsylmSym())
                                    .payRestricDt(msg2Vo.getPayRestricDt())
                                    .sbrdnType(msg2Vo.getSbrdnType())
                                    .cfhcRem(msg2Vo.getCfhcRem())
                                    .dprtYn(msg2Vo.getDprtYn())
                                    .obstRegDt(msg2Vo.getObstRegDt())
                                    .ykiho1(msg2Vo.getYkiho1())
                                    .ykiho2(msg2Vo.getYkiho2())
                                    .ykiho3(msg2Vo.getYkiho3())
                                    .ykiho4(msg2Vo.getYkiho4())
                                    .yoyangNm1(msg2Vo.getYoyangNm1())
                                    .yoyangNm2(msg2Vo.getYoyangNm2())
                                    .yoyangNm3(msg2Vo.getYoyangNm3())
                                    .yoyangNm4(msg2Vo.getYoyangNm4())
                                    .date(msg2Vo.getDate())
                                    .messageCode(msg2Vo.getMessageCode())
                                    .message(msg2Vo.getMessage())
                                    .operatorJuminNo(msg2Vo.getOperatorJuminNo())
                                    .msgType(msg2Vo.getMsgType())
                                    .clientInfo(msg2Vo.getClientInfo())
                            .build());
                } else {
                    //건강보험 1 2 4 5 6
                    healthInsuranceSujinjaInfoList.add(HealthInsuranceSujinjaInfo.builder()
                            .sujinjaJuminNo(msg2Vo.getSujinjaJuminNo())
                            .sujinjaJuminNm(msg2Vo.getSujinjaJuminNm())
                            .ykiho(msg2Vo.getYkiho())
                            .qlfType(msg2Vo.getQlfType())
                            .qlfChwidukDt(msg2Vo.getQlfChwidukDt())
                            .sedaejuNm(msg2Vo.getSedaejuNm())
                            .protAdminSym(msg2Vo.getProtAdminSym())
                            .asylmSym(msg2Vo.getAsylmSym())
                            .payRestricDt(msg2Vo.getPayRestricDt())
                            .sbrdnType(msg2Vo.getSbrdnType())
                            .cfhcRem(msg2Vo.getCfhcRem())
                            .dprtYn(msg2Vo.getDprtYn())
                            .obstRegDt(msg2Vo.getObstRegDt())
                            .ykiho1(msg2Vo.getYkiho1())
                            .ykiho2(msg2Vo.getYkiho2())
                            .ykiho3(msg2Vo.getYkiho3())
                            .ykiho4(msg2Vo.getYkiho4())
                            .yoyangNm1(msg2Vo.getYoyangNm1())
                            .yoyangNm2(msg2Vo.getYoyangNm2())
                            .yoyangNm3(msg2Vo.getYoyangNm3())
                            .yoyangNm4(msg2Vo.getYoyangNm4())
                            .date(msg2Vo.getDate())
                            .messageCode(msg2Vo.getMessageCode())
                            .message(msg2Vo.getMessage())
                            .operatorJuminNo(msg2Vo.getOperatorJuminNo())
                            .msgType(msg2Vo.getMsgType())
                            .clientInfo(msg2Vo.getClientInfo())
                            .build());
                }
            }

            return ResultResponse.builder()
                    .data(ReceptionSujinjaSearchResponse.builder()
                            .medicalBenefitsSujinjaInfoList(medicalBenefitsSujinjaInfoList)
                            .healthInsuranceSujinjaInfoList(healthInsuranceSujinjaInfoList)
                            .build())
                    .result(true)
                    .resultCode("200")
                    .resultMessage("success")
                    .build();

        } catch (ServiceException | RemoteException e) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("500")
                    .resultMessage(e.getMessage())
                    .build();
        } catch (javax.xml.rpc.ServiceException e) {
            throw new RuntimeException(e);
        }
//
//        return ResultResponse.builder()
//                .result(true)
//                .resultCode("200")
//                .resultMessage("success")
//                .build();
    }

    @Override
    public ResultResponse selectPatientsInfo(String searchKeyword) {
        return ResultResponse.builder()
                .data(mapper.selectPatientsInfo(searchKeyword))
                .result(true)
                .resultCode("200")
                .resultMessage("success")
                .build();
    }

    @Override
    public ResultResponse selectTodayReceptionList(String gubun) {
        List<TodayReceptionInfo> todayReceptionInfos = List.of(TodayReceptionInfo.builder().build());
        TodayReceptionReq todayReceptionReq = new TodayReceptionReq(myHospitalId(), gubun);
        var list = mapper.selectTodayReceptionList(todayReceptionReq);
        return ResultResponse.builder()
                .data(list.size() == 0 ? todayReceptionInfos : list)
                .result(true)
                .resultCode("200")
                .resultMessage("success")
                .build();
    }

    @Override
    public ResultResponse selectTodayHospitalStateList(Long roomId) {
        List<TodayHospitalStateInfo> todayHospitalStateInfo = List.of(TodayHospitalStateInfo.builder().build());
        var list  = mapper.selectTodayHospitalStateList(roomId, myHospitalId());
        return ResultResponse.builder()
                .data(list.size() == 0 ? todayHospitalStateInfo : list)
                .select(mapper.selectStatusList())
                .result(true)
                .resultCode("200")
                .resultMessage("success")
                .build();
    }

    @Override
    public ResultResponse selectRoomList() {
        return ResultResponse.builder()
                .data(mapper.selectRoomList())
                .result(true)
                .resultCode("200")
                .resultMessage("success")
                .build();
    }

    @Override
    public ResultResponse updateRoom(UpdateRoomReq updateRoomReq) {
        mapper.updateRoom(updateRoomReq);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse outHospital(Long id) {
        mapper.outHospital(id);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse updateStatus(UpdateStatusReq updateStatusReq) {
        mapper.updateStatus(updateStatusReq);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse deleteReception(Long id) {
        mapper.deleteReception(id);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("취소되었습니다.")
                .build();
    }

    @Override
    public ResultResponse waitReceptionList() {
        List<WaitReceptionListRes> waitReceptionListResList = mapper.waitReceptionList(myHospitalId());
        for (WaitReceptionListRes waitReceptionListRes : waitReceptionListResList) {
            Long receptionId = mapper.findReceptionId(waitReceptionListRes.getPatientId());
            if (receptionId == 1) {
                waitReceptionListRes.setIsNew("신환");
            } else {
                waitReceptionListRes.setIsNew("-");
            }
        }
        return ResultResponse.builder()
                .data(waitReceptionListResList)
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse newPatient(NewPatientReq newPatientReq) {
        String name = newPatientReq.getName();
        String firstRegistrationNumber = newPatientReq.getFirstRegistrationNumber();
        String secondRegistrationNumber = newPatientReq.getSecondRegistrationNumber();
        newPatientReq.setHospitalId(myHospitalId());

        char sex = secondRegistrationNumber.charAt(0);
        if (sex == 1 || sex == 3 || sex == 5 || sex == 7) {
            newPatientReq.setSexId(1L);
        } else {
            newPatientReq.setSexId(2L);
        }
        if (mapper.findPatientByNameAndRegisNum(name, firstRegistrationNumber, secondRegistrationNumber) != null) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("이미 등록된 환자입니다.")
                    .build();
        }
        mapper.newPatient(newPatientReq);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }

    @Override
    public ResultResponse recentReceptionRecord(Long patientId, String start, String end) {

        if(start != null && end != null) {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            long diff = ChronoUnit.DAYS.between(startDate, endDate);

            if(diff >= 365) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("선택 가능한 최대 1년을 초과합니다.")
                        .build();
            }
        }


        RecentReceptionRecordReq recentReceptionRecordReq = RecentReceptionRecordReq.builder()
                .start(start)
                .end(end)
                .patientId(patientId)
                .hospitalId(myHospitalId())
                .build();

        return ResultResponse.builder()
                .data(mapper.recentReceptionRecord(recentReceptionRecordReq))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse patientInfo(Long patientId) {
        return ResultResponse.builder()
                .data(mapper.patientInfo(patientId))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse receptionInfo(Long receptionId) {
        return ResultResponse.builder()
                .data(mapper.receptionInfo(receptionId, myHospitalId()))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse reservationInfo(Long patientId) {
        Map<String, Object> map = new HashMap<>();
        map.put("protectorRelationList", mapper.selectProtectorRelation());
        map.put("doctorList", mapper.selectDoctorList(myHospitalId()));
        return ResultResponse.builder()
                .data(mapper.reservationInfo(patientId))
                .select(map)
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse updatePatientInfo(UpdatePatientInfoReq updatePatientInfoReq) {
        PatientInfoRes patientInfoRes = mapper.patientInfo(updatePatientInfoReq.getPatientId());
        mapper.updatePatientInfo(updatePatientInfoReq);

        List<Long> patientChangeCategoryIds = new ArrayList<>();
        List<String> pastValues = new ArrayList<>();

        if(!updatePatientInfoReq.getName().equals(patientInfoRes.getName())) {
            patientChangeCategoryIds.add(1L);
            pastValues.add(patientInfoRes.getName());
        }
        if(!updatePatientInfoReq.getFirstRegistrationNumber().equals(patientInfoRes.getFirstRegistrationNumber()) || !updatePatientInfoReq.getSecondRegistrationNumber().equals(patientInfoRes.getSecondRegistrationNumber())) {
            patientChangeCategoryIds.add(2L);
            pastValues.add(patientInfoRes.getFirstRegistrationNumber() + "-" + patientInfoRes.getSecondRegistrationNumber());
        }
        if(!updatePatientInfoReq.getPhoneNumber().equals(patientInfoRes.getPhoneNumber())) {
            patientChangeCategoryIds.add(3L);
            pastValues.add(patientInfoRes.getPhoneNumber());
        }
        if(!updatePatientInfoReq.getAddress().equals(patientInfoRes.getAddress()) || !updatePatientInfoReq.getAddressDetail().equals(patientInfoRes.getAddressDetail())) {
            patientChangeCategoryIds.add(4L);
            pastValues.add(patientInfoRes.getAddress() + " " + patientInfoRes.getAddressDetail());
        }

        for (int i = 0; i < patientChangeCategoryIds.size(); i++) {
            Long patientChangeCategoryId = patientChangeCategoryIds.get(i);
            String past = pastValues.get(i);

            CreatePatientLogReq createPatientLogReq = CreatePatientLogReq.builder()
                    .userId(myUserId())
                    .patientId(updatePatientInfoReq.getPatientId())
                    .patientChangeCategoryId(patientChangeCategoryId)
                    .beforeContent(past)
                    .build();
            mapper.createPatientLog(createPatientLogReq);
        }

        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse updateReceptionInfo(UpdateReceptionInfoReq updateReceptionInfoReq) {
        mapper.updateReceptionInfo(updateReceptionInfoReq);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse patientLogList(Long patientId) {
        return ResultResponse.builder()
                .data(mapper.patientLogList(patientId))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse insertPatientMemo(InsertPatientMemoReq insertPatientMemoReq) {
        InsertPatientMemoReq req = InsertPatientMemoReq.builder()
                .userId(myUserId())
                .patientId(insertPatientMemoReq.getPatientId())
                .content(insertPatientMemoReq.getContent())
                .build();
        mapper.insertPatientMemo(req);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }

    @Override
    public ResultResponse deletePatientMemo(Long memoId) {
        mapper.deletePatientMemo(memoId, myUserId());
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("삭제되었습니다.")
                .build();
    }

    @Override
    public ResultResponse updatePatientMemo(UpdatePatientMemoReq updatePatientMemoReq) {
        UpdatePatientMemoReq req = UpdatePatientMemoReq.builder()
                .content(updatePatientMemoReq.getContent())
                .memoId(updatePatientMemoReq.getMemoId())
                .userId(myUserId())
                .build();
        mapper.updatePatientMemo(req);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse patientMemoList(Long patientId) {
        return ResultResponse.builder()
                .data(mapper.patientMemoList(myUserId(), patientId))
                .result(true)
                .resultCode("200")
                .build();
    }


}
