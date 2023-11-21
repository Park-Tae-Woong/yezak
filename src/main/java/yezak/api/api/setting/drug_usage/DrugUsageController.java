package yezak.api.api.setting.drug_usage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.setting.drug_usage.dto.InsertDrugUsageReq;
import yezak.api.api.setting.drug_usage.dto.UpdateDrugUsageReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/drug-usage")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Drug-usage-manage", description = "설정 > 용법 관리")
public class DrugUsageController {
    private final DrugUsageService drugUsageService;

    //용법 리스트
    @GetMapping(value = "/usage-list")
    @Operation(summary = "용법 리스트" , description = "용법 리스트",
            parameters = {
                    @Parameter(name = "drugUsageTypeId", description = "구분", example = "1"),
                    @Parameter(name = "searchValue", description = "검색", example = "#1"),
                    @Parameter(name = "pageNum", description = "페이지 번호", example = "1"),
                    @Parameter(name = "pageSize", description = "행 갯수", example = "15")
            })
    public ResponseEntity<ApiResponse<?>> drugUsageList(@RequestParam(required = false) Long drugUsageTypeId,
                                                     @RequestParam(required = false) String searchValue,
                                                     @RequestParam Integer pageNum,
                                                     @RequestParam Integer pageSize) {

        ResultResponse<?> resultResponse = drugUsageService.drugUsageList(drugUsageTypeId, searchValue, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //용법 업데이트
    @PutMapping(value = "/update-usage")
    @Operation(summary = "용법 수정" , description = "용법 수정")
    public ResponseEntity<ApiResponse<?>> updateUsage(@RequestBody UpdateDrugUsageReq updateDrugUsageReq) {
        ResultResponse<?> resultResponse = drugUsageService.updateUsage(updateDrugUsageReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //용법 신규
    @PostMapping(value = "/insert-usage")
    @Operation(summary = "용법 신규 등록" , description = "용법 신규 등록")
    public ResponseEntity<ApiResponse<?>> insertUsage(@RequestBody InsertDrugUsageReq insertDrugUsageReq) {
        ResultResponse<?> resultResponse = drugUsageService.insertUsage(insertDrugUsageReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
