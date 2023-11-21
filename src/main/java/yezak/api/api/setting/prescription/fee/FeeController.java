package yezak.api.api.setting.prescription.fee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.setting.prescription.fee.dto.InsertFeeReq;
import yezak.api.api.setting.prescription.fee.dto.InsertInspectionFeeReq;
import yezak.api.api.setting.prescription.fee.dto.InsertSurgeryReq;
import yezak.api.api.setting.prescription.fee.dto.UpdateFeeReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/fee")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "fee-manage", description = "설정 > 처방관리 > 수가")
public class FeeController {

    private final FeeService feeService;

    @GetMapping(value = "/select-code-type-list")
    @Operation(summary = "분류 및 세부구분 리스트" , description = "분류 및 세부구분 리스트")
    public ResponseEntity<ApiResponse<?>> selectCodeTypeList(){
        ResultResponse<?> resultResponse = feeService.selectCodeTypeList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

    @GetMapping(value = "/list")
    @Operation(summary = "수가 리스트" , description = "수가 리스트", parameters = {
            @Parameter(name = "categoryId", description = "분류", example = "1"),
            @Parameter(name = "subdivisionId", description = "세부구분", example = "1"),
            @Parameter(name = "searchValue", description = "검색", example = "제거술"),
            @Parameter(name = "pageNum", description = "페이지 번호", example = "1"),
            @Parameter(name = "pageSize", description = "페이지 크기", example = "10")
    })
    public ResponseEntity<ApiResponse<?>> getFeeList(@RequestParam(required = false) Long categoryId,
                                                     @RequestParam(required = false) Long subdivisionId,
                                                     @RequestParam(required = false) String searchValue,
                                                     @RequestParam Integer pageNum,
                                                     @RequestParam Integer pageSize){
        ResultResponse<?> resultResponse = feeService.getFeeList(categoryId,subdivisionId,searchValue,pageNum,pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

    @GetMapping(value = "/detail")
    @Operation(summary = "수가 상세" , description = "수가 상세", parameters = {
            @Parameter(name = "id", description = "수가 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> getFeeList(@RequestParam Long id){
        ResultResponse<?> resultResponse = feeService.getFeeDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

    @PostMapping(value = "/insert")
    @Operation(summary = "수가 일반 신규" , description = "수가 일반 신규")
    public ResponseEntity<ApiResponse<?>> insertFee(@RequestBody InsertFeeReq insertFeeReq){
        ResultResponse<?> resultResponse = feeService.insertFee(insertFeeReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PostMapping(value = "/insert-inspection")
    @Operation(summary = "수가 검사료 신규" , description = "수가 검사료 신규")
    public ResponseEntity<ApiResponse<?>> insertInspectionFee(@RequestBody InsertInspectionFeeReq insertInspectionFeeReq){
        ResultResponse<?> resultResponse = feeService.insertInspectionFee(insertInspectionFeeReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
    @PostMapping(value = "/insert-surgery")
    @Operation(summary = "수가 수술 신규" , description = "수가 수술 신규")
    public ResponseEntity<ApiResponse<?>> insertSurgeryFee(@RequestBody InsertSurgeryReq insertSurgeryReq){
        ResultResponse<?> resultResponse = feeService.insertSurgeryFee(insertSurgeryReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "수가 삭제" , description = "수가 삭제")
    public ResponseEntity<ApiResponse<?>> deleteFee(@RequestParam List<Long> ids){
        System.out.println(ids);
        for (Long a : ids) {
            System.out.println(a);
        }
        ResultResponse<?> resultResponse = feeService.deleteFee(ids);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "수가 수정" , description = "수가 수정")
    public ResponseEntity<ApiResponse<?>> updateFee(@RequestBody UpdateFeeReq updateFeeReq){
        ResultResponse<?> resultResponse = feeService.updateFee(updateFeeReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/select-list")
    @Operation(summary = "검체, 수탁기관, 수치/소견, 보험률" , description = "검체, 수탁기관, 수치/소견, 보험률")
    public ResponseEntity<ApiResponse<?>> selectList(){
        ResultResponse<?> resultResponse = feeService.selectList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

}
