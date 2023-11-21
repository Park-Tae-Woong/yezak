package yezak.api.api.setting.prescription.material;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.setting.prescription.material.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialMapper {
    List<MaterialListRes> MaterialList (MaterialListReq materialListReq);

    Integer countMaterial(MaterialListReq materialListReq);

    void deleteMaterial(@Param("id") Long id, @Param("hospitalId") Long hospitalId);
    void deleteMaterialCode(@Param("id") Long id, @Param("hospitalId") Long hospitalId);

    SystemMaterialDetailRes materialDetail (@Param("id") Long id, @Param("hospitalId") Long hospitalId);

    List<String> dateChangeLog(Long id);
    List<Integer> materialPriceLog(Long codeId);
    List<Integer> optionPriceLog(Long codeId);

    String findByCustomCode(FindByCustomCode findByCustomCode);

    void insertMaterialOption (InsertMaterial insertMaterial);
    void insertMaterial (InsertMaterial insertMaterial);
    void increaseId(IncreaseIdReq increaseIdReq);

    void updateMaterialOption (UpdateMaterial updateMaterial);
    void updateMaterial (UpdateMaterial updateMaterial);
    void deleteOld (Long id);

    Long findMaterialCodeIdById (Long id);
    Long findCode (FindCode findCode);
    Long findHospitalId(Long id);
    FindMaterial findMaterial(Long id);


}
