package yezak.api.api.pad;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.pad.dto.NewPatientAndVisitPurpose;
import yezak.api.api.pad.dto.NewPatientReq;
import yezak.api.api.pad.dto.PadReceptionReq;
import yezak.api.api.pad.dto.VisitPurposeReq;
import yezak.api.global.common.ResultResponse;

import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class PadServiceImpl implements PadService {

    private final PadMapper padMapper;
    @Override
    public ResultResponse padReception(String name, String phoneNumber) {
        PadReceptionReq padReceptionReq = PadReceptionReq.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .hospitalId(myHospitalId())
                .build();

        Long patientId = padMapper.padReception(padReceptionReq);

        if (patientId == null) {
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("신규환자")
                    .select(padMapper.selectVisitPurpose(myHospitalId()))
                    .build();
        } else {
            return ResultResponse.builder()
                    .data(patientId)
                    .result(true)
                    .resultCode("201")
                    .resultMessage("기존환자")
                    .select(padMapper.selectVisitPurpose(myHospitalId()))
                    .build();
        }
    }

    @Override
    public ResultResponse newPatientAndVisitPurpose(NewPatientAndVisitPurpose newPatientAndVisitPurpose) {
        NewPatientReq newPatientReq = NewPatientReq.builder()
                .name(newPatientAndVisitPurpose.getName())
                .phoneNumber(newPatientAndVisitPurpose.getPhoneNumber())
                .firstRegistrationNumber(newPatientAndVisitPurpose.getFirstRegistrationNumber())
                .secondRegistrationNumber(newPatientAndVisitPurpose.getSecondRegistrationNumber())
                .address(newPatientAndVisitPurpose.getAddress())
                .addressDetail(newPatientAndVisitPurpose.getAddressDetail())
                .agreedPersonal(newPatientAndVisitPurpose.getAgreedPersonal())
                .agreedMarketing(newPatientAndVisitPurpose.getAgreedMarketing())
                .agreedSettlement(newPatientAndVisitPurpose.getAgreedSettlement())
                .build();
        padMapper.newPatient(newPatientReq);
        Long patientId = newPatientReq.getId();

        VisitPurposeReq visitPurposeReq = VisitPurposeReq.builder()
                .patientId(patientId)
                .visitPurposeId(newPatientAndVisitPurpose.getVisitPurposeId())
                .build();
        padMapper.visitPurpose(visitPurposeReq);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }

    @Override
    public ResultResponse oldPatientVisitPurpose(VisitPurposeReq visitPurposeReq) {
        padMapper.visitPurpose(visitPurposeReq);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }


}
