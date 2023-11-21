package yezak.api.api.pad;

import yezak.api.api.pad.dto.NewPatientAndVisitPurpose;
import yezak.api.api.pad.dto.VisitPurposeReq;
import yezak.api.global.common.ResultResponse;

public interface PadService {
    ResultResponse padReception (String name, String phoneNumber);
    ResultResponse newPatientAndVisitPurpose (NewPatientAndVisitPurpose newPatientAndVisitPurpose);
    ResultResponse oldPatientVisitPurpose (VisitPurposeReq visitPurposeReq);

}
