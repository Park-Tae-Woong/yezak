package yezak.api.api.setting.prescription.material;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.setting.prescription.material.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Transactional
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    private final MaterialMapper materialMapper;
    private final Integer materialId = 65;

    @Override
    public ResultResponse<?> MaterialList(String searchValue, Integer pageNum, Integer pageSize) {

        if (myDepth3Id().contains(materialId)) {
            int offset = (pageNum - 1) * pageSize;
            MaterialListReq materialListReq = new MaterialListReq(myHospitalId(), searchValue, offset, pageSize);

            int totalCount = materialMapper.countMaterial(materialListReq);
            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double) totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(materialMapper.MaterialList(materialListReq))
                    .page(page)
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
    public ResultResponse<?> deleteMaterial(List<Long> ids) {
        if (!myDepth3Id().contains(materialId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        for (Long id : ids) {
            Long codeId = materialMapper.findMaterialCodeIdById(id);
            Long hospitalId = materialMapper.findHospitalId(id);
            if (hospitalId == 0) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("등록 주체가 라이브러리인 데이터는 삭제 불가능합니다.")
                        .build();
            }
            materialMapper.deleteMaterial(id, myHospitalId());
            materialMapper.deleteMaterialCode(codeId, myHospitalId());
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("삭제되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> materialDetail(Long id) {

        if (myDepth3Id().contains(materialId)) {
            Long codeId = materialMapper.findMaterialCodeIdById(id);

            Map<String, Object> map = new HashMap<>();

            List<Integer> materialPriceLogs = materialMapper.materialPriceLog(codeId);
            List<Integer> optionPriceLogs = materialMapper.optionPriceLog(codeId);

            List<Integer> prices = new ArrayList<>();

            for (int i = 0; i < Math.max(materialPriceLogs.size(), optionPriceLogs.size()); i++) {
                Integer materialPrice = (i < materialPriceLogs.size()) ? materialPriceLogs.get(i) : null;
                Integer optionPrice = (i < optionPriceLogs.size()) ? optionPriceLogs.get(i) : null;

                if (materialPrice != null) {
                    prices.add(materialPrice);
                }

                if (materialPrice == null && optionPrice != null) {
                    prices.add(optionPrice);
                }
            }

            map.put("materialDetail", materialMapper.materialDetail(id, myHospitalId()));
            map.put("dateChangeLog", materialMapper.dateChangeLog(codeId));
            map.put("priceChangeLog", prices);
            return ResultResponse.builder()
                    .data(map)
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
    public ResultResponse<?> insertMaterials(InsertMaterial insertMaterial) {
        if (myDepth3Id().contains(materialId)) {

            String customCode = insertMaterial.getCustomCode();
            String code = insertMaterial.getCode();

            FindByCustomCode findByCustomCode = new FindByCustomCode(customCode, myHospitalId());
            FindCode findCode = new FindCode(code, myHospitalId());

            if (materialMapper.findByCustomCode(findByCustomCode) != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("사용자코드가 중복됩니다. 사용자코드를 변경해주세요.")
                        .build();
            }
            if (materialMapper.findCode(findCode) != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("재료코드가 종복됩니다. 재료코드를 변경해주세요.")
                        .build();
            }

            IncreaseIdReq increaseIdReq = new IncreaseIdReq();
            increaseIdReq.setHospitalId(myHospitalId());
            increaseIdReq.setCode(insertMaterial.getCode());
            materialMapper.increaseId(increaseIdReq);
            Long newId = increaseIdReq.getId();

            insertMaterial.setMaterialCodeId(newId);
            insertMaterial.setHospitalId(myHospitalId());
            materialMapper.insertMaterialOption(insertMaterial);
            materialMapper.insertMaterial(insertMaterial);

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("등록되었습니다.")
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
    public ResultResponse<?> updateMaterials(UpdateMaterial updateMaterial) {
        if (!myDepth3Id().contains(materialId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        Long hospitalId = materialMapper.findHospitalId(updateMaterial.getId());
        Long codeId = materialMapper.findMaterialCodeIdById(updateMaterial.getId());

        updateMaterial.setMaterialCodeId(codeId);
        updateMaterial.setHospitalId(myHospitalId());

        if (hospitalId != 0) {
            //delete 처리
            FindByCustomCode findByCustomCode = new FindByCustomCode(updateMaterial.getCustomCode(), myHospitalId());

            materialMapper.deleteOld(codeId);

            if (materialMapper.findByCustomCode(findByCustomCode) != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                        .build();
            }
            //생성
            materialMapper.updateMaterialOption(updateMaterial);
            materialMapper.updateMaterial(updateMaterial);

        } else {
            FindByCustomCode findByCustomCode = new FindByCustomCode(updateMaterial.getCustomCode(), myHospitalId());

            materialMapper.deleteOld(codeId);
            if (materialMapper.findByCustomCode(findByCustomCode) != null) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("사용자코드가 중복됩니다.\n사용자코드를 변경해주세요.")
                        .build();
            }

            FindMaterial findMaterial = materialMapper.findMaterial(updateMaterial.getId());
            UpdateMaterial updateMaterial1 = UpdateMaterial.builder()
                    .materialCodeId(codeId)
                    .name(findMaterial.getName())
                    .specification(findMaterial.getSpecification())
                    .unit(findMaterial.getUnit())
                    .manufacturer(findMaterial.getManufacturer())
                    .material(findMaterial.getMaterial())
                    .importer(findMaterial.getImporter())
                    .applicatedDate(updateMaterial.getApplicatedDate())
                    .build();
            materialMapper.updateMaterialOption(updateMaterial);
            materialMapper.updateMaterial(updateMaterial1);
        }

        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }
}
