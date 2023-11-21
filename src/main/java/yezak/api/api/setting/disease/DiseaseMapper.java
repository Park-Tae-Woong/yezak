package yezak.api.api.setting.disease;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.setting.disease.dto.*;

import java.util.List;

@Mapper
public interface DiseaseMapper {

    List<DiseaseDto> diseaseList(FilterDiseaseReq filterDiseaseReq);

    String customCodeByHospitalId(@Param("hospitalId") Long hospitalId, @Param("diseaseId") Long diseaseId);
    DiseaseDto findByDiseaseCode(Long id);
    int countTotal(FilterDiseaseReq filterDiseaseReq);
    String findByCustomCode(CustomCodeDuplicateCheck customCodeDuplicateCheck);

    void insertDiseaseOption (InsertDiseaseOptionReq insertDiseaseOptionReq);
    void updateCode(UpdateDiseaseReq updateDiseaseReq);

    Long findDiseaseOption(@Param("diseaseId") Long diseaseId, @Param("hospitalId") Long hospitalId);

}
