package yezak.api.api.setting.prescription.drug;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.setting.prescription.drug.dto.*;

import java.util.List;

@Mapper
public interface DrugMapper {
    List<DrugListRes> drugList(DrugListReq drugListReq);
    int countDrug(DrugListReq drugListReq);
    SystemDrugDetailRes systemDrugDetail(Long id);

    void drugOption(InsertDrugReq insertDrugReq);
    void drugCode(InsertDrugReq insertDrugReq);
    void insertDrug(InsertDrugReq insertDrugReq);
    MyHospitalDrugDetailRes myHospitalDrugDetail (Long id);
    Long findHospitalId(Long id);

    void updateSystemDrug (UpdateDrugReq updateDrugReq);

    FindCodeAndHosByDrugIdRes findCodeAndHosByDrugId (Long id);

    void updateDrugOption (UpdateDrugReq updateDrugReq);
    void updateDrug (UpdateDrugReq updateDrugReq);

    void deleteOld (@Param("id") Long id, @Param("hospitalId") Long hospitalId);

    Long findCustomCode(FindCustomCode findCustomCode);
    Long findDrugCode(FindDrugCode findDrugCode);

    void deleteOldOption (@Param("drugCodeId") Long drugCodeId, @Param("hospitalId") Long hospitalId);

    List<String> dateChangeLog(Long codeId);
    List<Integer> priceChangeLog(Long codeId);

    List<MaxValueChangeLogRes> maxValueChangeLog (Long codeId);
    void deleteDrug (@Param("id") Long id, @Param("hospitalId") Long hospitalId);

}
