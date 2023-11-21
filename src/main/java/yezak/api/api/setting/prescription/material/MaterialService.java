package yezak.api.api.setting.prescription.material;

import yezak.api.api.setting.prescription.material.dto.InsertMaterial;
import yezak.api.api.setting.prescription.material.dto.UpdateMaterial;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface MaterialService {
    ResultResponse<?> MaterialList (String searchValue, Integer pageNum, Integer pageSize);

    ResultResponse<?> deleteMaterial(List<Long> ids);

    ResultResponse<?> materialDetail (Long id);

    ResultResponse<?> insertMaterials (InsertMaterial insertMaterial);

    ResultResponse<?> updateMaterials (UpdateMaterial updateMaterial);

}
