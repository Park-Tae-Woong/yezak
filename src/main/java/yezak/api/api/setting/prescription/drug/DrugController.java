package yezak.api.api.setting.prescription.drug;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.setting.prescription.drug.dto.InsertDrugReq;
import yezak.api.api.setting.prescription.drug.dto.UpdateDrugReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/drug")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Drug-manage", description = "설정 > 처방관리 > 약가")
public class DrugController {

    private final DrugService drugService;

    @GetMapping(value = "/list")
    @Operation(summary = "약가 리스트" , description = "약가 리스트", parameters = {
            @Parameter(name = "dosageForm", description = "상세구분", example = "내복"),
            @Parameter(name = "searchValue", description = "검색", example = "0505"),
            @Parameter(name = "pageNum", description = "페이지 번호", example = "1"),
            @Parameter(name = "pageSize", description = "페이지 크기", example = "10")
    })
    public ResponseEntity<ApiResponse<?>> drugList(@RequestParam(required = false) String dosageForm,
                                                   @RequestParam(required = false) String searchValue,
                                                   @RequestParam Integer pageNum,
                                                   @RequestParam Integer pageSize){
        ResultResponse<?> resultResponse = drugService.drugList(dosageForm, searchValue, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

    @GetMapping(value = "/detail")
    @Operation(summary = "약가 세부" , description = "약가 세부")
    public ResponseEntity<ApiResponse<?>> drugDetail(@RequestParam Long id){
        ResultResponse<?> resultResponse = drugService.drugDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PostMapping(value = "/insert")
    @Operation(summary = "약가 신규" , description = "약가 신규, id와 drugCodeId는 입력 받지 않음")
    public ResponseEntity<ApiResponse<?>> createDrug(@RequestBody InsertDrugReq insertDrugReq){
        ResultResponse<?> resultResponse = drugService.createDrug(insertDrugReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "약가 수정" , description = "약가 수정")
    public ResponseEntity<ApiResponse<?>> updateDrug(@RequestBody UpdateDrugReq updateDrugReq){
        ResultResponse<?> resultResponse = drugService.updateDrug(updateDrugReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "약가 삭제" , description = "약가 삭제")
    public ResponseEntity<ApiResponse<?>> deleteDrug(@RequestParam List<Long> ids){
        ResultResponse<?> resultResponse = drugService.deleteDrug(ids);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
