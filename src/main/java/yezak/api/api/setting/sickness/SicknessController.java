package yezak.api.api.setting.sickness;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.setting.sickness.dto.CreateSicknessReq;
import yezak.api.api.setting.sickness.dto.UpdateSicknessReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/sickness")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Sickness-manage", description = "설정 > 증상 관리")

public class SicknessController {

    private final SicknessService sicknessService;

    // 증상 리스트
    @GetMapping(value = "/get-sick-list")
    @Operation(summary = "증상 리스트" , description = "증상 리스트", parameters = {
            @Parameter(name = "searchValue", description = "검색어", example = "AA")})
    public ResponseEntity<ApiResponse<?>> getSickList(@RequestParam(required = false) String searchValue){
        ResultResponse<?> resultResponse = sicknessService.getSickList(searchValue);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


    //증상 입력
    @PostMapping(value = "/insert-sick")
    @Operation(summary = "증상 입력" , description = "증상 입력")
    public ResponseEntity<ApiResponse<?>> insertSick(@RequestBody CreateSicknessReq createSicknessReq) {
        ResultResponse<?> resultResponse = sicknessService.insertSick(createSicknessReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //증상 수정
    @PutMapping(value = "/update-sick")
    @Operation(summary = "증상 수정" , description = "증상 수정")
    public ResponseEntity<ApiResponse<?>> updateSick(@RequestBody UpdateSicknessReq updateSicknessReq) {
        ResultResponse<?> resultResponse = sicknessService.updateSick(updateSicknessReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //증상 삭제
    @DeleteMapping(value = "/delete-sick")
    @Operation(summary = "증상 삭제" , description = "증상 삭제")
    public ResponseEntity<ApiResponse<?>> deleteSick(@RequestParam List<Long> ids) {
        ResultResponse<?> resultResponse = sicknessService.deleteSick(ids);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
