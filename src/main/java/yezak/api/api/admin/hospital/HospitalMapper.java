package yezak.api.api.admin.hospital;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.admin.hospital.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface HospitalMapper {
    HospitalInfoRes hospitalInfo(Long id);

    void updateHospitalInfo(UpdateHospitalInfoReq updateHospitalInfoReq);

    List<OfficeHourRes> officeHourInfo(Long id);

    void updateOfficeHour(OfficeHourReq officeHourReq);

    List<OvertimeRes> overtimeInfo(Long hospitalId);

    void updateOvertime(OvertimeReq overtimeReq);
    Long findOvertimeCategoryId(@Param("hospitalId")Long hospitalId, @Param("id") Long id);

    List<VisitPurposeRes> visitPurposeInfo(Long hospitalId);

    void insertVisitPurpose(InsertVisitPurposeReq insertVisitPurposeReq);

    void deleteVisitPurpose(DeleteVisitPurposeReq deleteVisitPurposeReq);

    void updateVisitPurpose(UpdateVisitPurposeReq updateVisitPurposeReq);

    Integer getHolidayRestYn(Long id);

    void updateHolidayRestYn(Map<String, Object> paramMap);
}
