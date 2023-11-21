package yezak.api.api.pad;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.pad.dto.NewPatientReq;
import yezak.api.api.pad.dto.PadReceptionReq;
import yezak.api.api.pad.dto.VisitPurposeReq;

import java.util.List;
import java.util.Map;

@Mapper
public interface PadMapper {
    Long padReception (PadReceptionReq padReceptionReq);
    List<Map<String, Object>> selectVisitPurpose(Long hospitalId);

    void newPatient(NewPatientReq newPatientReq);
    void visitPurpose(VisitPurposeReq visitPurposeReq);




}
