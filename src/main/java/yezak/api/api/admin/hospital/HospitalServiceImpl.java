package yezak.api.api.admin.hospital;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yezak.api.api.admin.hospital.dto.*;
import yezak.api.global.common.ResultResponse;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    private final HospitalMapper hospitalMapper;
    private final Integer hospitalInfoId = 53;

    @Override
    public ResultResponse<?> hospitalInfo() {
        if(myDepth3Id().contains(hospitalInfoId)) {
            return ResultResponse.builder()
                    .data(hospitalMapper.hospitalInfo(myHospitalId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> updateHospitalInfo(UpdateHospitalInfoReq updateHospitalInfoReqs) {
        if(myDepth3Id().contains(hospitalInfoId)) {
            updateHospitalInfoReqs.setId(myHospitalId());
            hospitalMapper.updateHospitalInfo(updateHospitalInfoReqs);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> officeHourInfo() {
        if(myDepth3Id().contains(hospitalInfoId)) {
            OfficeHourResponse officeHourResponse = OfficeHourResponse.builder()
                    .officeHour(hospitalMapper.officeHourInfo(myHospitalId()))
                    .holidayRestYn(hospitalMapper.getHolidayRestYn(myHospitalId()))
                    .build();
            return ResultResponse.builder()
                    .data(officeHourResponse)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> updateOfficeHour(OfficeHourRequest officeHourReqs) {
        if(myDepth3Id().contains(hospitalInfoId)) {
            for (OfficeHourReq officeHourReq : officeHourReqs.getOfficeHour()) {
                officeHourReq.setHospitalId(myHospitalId());
                hospitalMapper.updateOfficeHour(officeHourReq);
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hospitalId", myHospitalId());
            paramMap.put("holidayRestYn", officeHourReqs.getHolidayRestYn());

            hospitalMapper.updateHolidayRestYn(paramMap);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> overtimeInfo() {
        if(myDepth3Id().contains(hospitalInfoId)) {
            return ResultResponse.builder()
                    .data(hospitalMapper.overtimeInfo(myHospitalId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> updateOvertime(List<OvertimeReq> overtimeReqs) {
        if(myDepth3Id().contains(hospitalInfoId)) {
            Map<Long, LocalTime> maxDefaultStartTimes = new HashMap<>();
            maxDefaultStartTimes.put(1L, LocalTime.parse("09:00"));
            maxDefaultStartTimes.put(2L, LocalTime.parse("20:00"));
            maxDefaultStartTimes.put(3L, LocalTime.parse("09:00"));

            Map<Long, LocalTime> maxDefaultEndTimes = new HashMap<>();
            maxDefaultEndTimes.put(1L, LocalTime.parse("18:00"));
            maxDefaultEndTimes.put(2L, LocalTime.parse("07:00"));
            maxDefaultEndTimes.put(3L, LocalTime.parse("13:00"));

            for (OvertimeReq overtimeReq : overtimeReqs) {
                Long id = hospitalMapper.findOvertimeCategoryId(myHospitalId(), overtimeReq.getId());
                LocalTime maxDefaultStartTime = maxDefaultStartTimes.get(id);
                LocalTime maxDefaultEndTime = maxDefaultEndTimes.get(id);
                if (maxDefaultStartTime == null) {
                    throw new IllegalArgumentException("Invalid ID value");
                }

                LocalTime defaultStart = LocalTime.parse(overtimeReq.getDefaultStart());
                LocalTime defaultEnd = LocalTime.parse(overtimeReq.getDefaultEnd());

                if (id == 1) {
                    if (defaultStart.isAfter(maxDefaultStartTime) || defaultEnd.isBefore(maxDefaultEndTime)) {

                        return ResultResponse.builder()
                                .result(false)
                                .resultCode("400")
                                .resultMessage("선정 시간은 9시 이전에서 18시 이후로만 설정할 수 있습니다.")
                                .build();
                    }
                } else if (id == 2) {
                    if (defaultStart.isBefore(maxDefaultStartTime) || defaultEnd.isAfter(maxDefaultEndTime)) {

                        return ResultResponse.builder()
                                .result(false)
                                .resultCode("400")
                                .resultMessage("선정 시간은 20시 이후에서 익일 7시 이내로만 설정할 수 있습니다.")
                                .build();
                    }
                } else if (id == 3) {
                    if (defaultStart.isAfter(maxDefaultStartTime) || defaultEnd.isBefore(maxDefaultEndTime)) {
                        return ResultResponse.builder()
                                .result(false)
                                .resultCode("400")
                                .resultMessage("선정 시간은 9시 이전에서 13시 이후로만 설정할 수 있습니다.")
                                .build();
                    }
                }
                overtimeReq.setHospitalId(myHospitalId());
                hospitalMapper.updateOvertime(overtimeReq);
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> visitPurposeInfo() {
        if(myDepth3Id().contains(hospitalInfoId)) {
            return ResultResponse.builder()
                    .data(hospitalMapper.visitPurposeInfo(myHospitalId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> visitPurposeMulti(VisitPurposeMulti visitPurposeMulti) {
        if(myDepth3Id().contains(hospitalInfoId)) {
            if (visitPurposeMulti.getInsertVisitPurposeReqs() != null) {
                for (InsertVisitPurposeReq insertVisitPurposeReq : visitPurposeMulti.getInsertVisitPurposeReqs()){
                    insertVisitPurposeReq.setHospitalId(myHospitalId());
                    hospitalMapper.insertVisitPurpose(insertVisitPurposeReq);
                }
            }
            if (visitPurposeMulti.getUpdateVisitPurposeReqs() != null) {
                for (UpdateVisitPurposeReq updateVisitPurposeReq : visitPurposeMulti.getUpdateVisitPurposeReqs()) {
                    updateVisitPurposeReq.setHospitalId(myHospitalId());
                    hospitalMapper.updateVisitPurpose(updateVisitPurposeReq);
                }
            }
            if (visitPurposeMulti.getDeleteVisitPurposeReqs() != null) {
                for (DeleteVisitPurposeReq deleteVisitPurposeReq : visitPurposeMulti.getDeleteVisitPurposeReqs()){
                    hospitalMapper.deleteVisitPurpose(deleteVisitPurposeReq);
                }
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }


//    @Override
//    public void processVisitPurposeRegist(InsertVisitPurposeReq insertVisitPurposeReq) {
//        User user = userMapper.findById((Long)request.getAttribute("userId"));
//        Long hospitalId = user.getHospitalId();
//        insertVisitPurposeReq.setHospitalId(hospitalId);
//        hospitalMapper.insertVisitPurpose(insertVisitPurposeReq);
//    }
//
//    @Override
//    public void processVisitPurposeModify(List<UpdateVisitPurposeReq> updateVisitPurposeReqs) {
//        User user = userMapper.findById((Long)request.getAttribute("userId"));
//        Long hospitalId = user.getHospitalId();
//        for(int i=0; i<updateVisitPurposeReqs.size(); i++) {
//            updateVisitPurposeReqs.get(i).setHospitalId(hospitalId);
//        }
//        if (updateVisitPurposeReqs != null && !updateVisitPurposeReqs.isEmpty()) {
//            updateVisitPurpose(updateVisitPurposeReqs);
//        }
//    }
//
//    @Override
//    public void processVisitPurposeDelete(Long id) {
//        hospitalMapper.deleteVisitPurpose(id);
//    }
}
