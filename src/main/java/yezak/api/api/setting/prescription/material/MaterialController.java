package yezak.api.api.setting.prescription.material;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.setting.prescription.material.dto.InsertMaterial;
import yezak.api.api.setting.prescription.material.dto.UpdateMaterial;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/material")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Material-manage", description = "설정 > 처방 관리 > 재료대")
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping(value = "/list")
    @Operation(summary = "재료대 리스트" , description = "재료대 리스트", parameters = {
            @Parameter(name = "searchValue", description = "검색", example = ""),
            @Parameter(name = "pageNum", description = "페이지 번호", example = "1"),
            @Parameter(name = "pageSize", description = "페이지 크기", example = "10")
    })
    public ResponseEntity<ApiResponse<?>> attendanceRecord(@RequestParam(required = false) String searchValue,
                                                        @RequestParam Integer pageNum,
                                                        @RequestParam Integer pageSize) {
        ResultResponse<?> resultResponse = materialService.MaterialList(searchValue, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "재료대 삭제" , description = "재료대 삭제", parameters = {
            @Parameter(name = "id", description = "삭제할 재료대 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> deleteMaterial(@RequestParam List<Long> ids) {
        ResultResponse<?> resultResponse = materialService.deleteMaterial(ids);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/detail")
    @Operation(summary = "재료대 상세" , description = "재료대 상세", parameters = {
            @Parameter(name = "id", description = "재료대 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> chooseDate(@RequestParam Long id) {
        ResultResponse<?> resultResponse = materialService.materialDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PostMapping(value = "/insert")
    @Operation(summary = "재료대 생성" , description = "재료대 생성")
    public ResponseEntity<ApiResponse<?>> insertMaterials(@RequestBody InsertMaterial insertMaterial) {
        ResultResponse<?> resultResponse = materialService.insertMaterials(insertMaterial);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "재료대 수정" , description = "재료대 수정")
    public ResponseEntity<ApiResponse<?>> insertMaterials(@RequestBody UpdateMaterial updateMaterial) {
        ResultResponse<?> resultResponse = materialService.updateMaterials(updateMaterial);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
