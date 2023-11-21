package yezak.api.api.setting.vital;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.setting.vital.dto.MultiVitalReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/vital")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Vital-manage", description = "설정 > 바이탈 관리")
public class VitalController {

    private final VitalService vitalService;

    //바이탈 리스트
    @GetMapping(value = "/get-vital-list")
    @Operation(summary = "바이탈 리스트" , description = "바이탈 리스트")
    public ResponseEntity<ApiResponse<?>> getVitalList(){
        ResultResponse<?> resultResponse = vitalService.getVitalList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //바이탈 수정 및 등록
    @PostMapping(value = "/post-update-vital")
    @Operation(summary = "바이탈 수정 및 등록" , description = "바이탈 수정 및 등록")
    public ResponseEntity<ApiResponse<?>> insertAndUpdateVital(@RequestBody MultiVitalReq multiVitalReq){
        ResultResponse<?> resultResponse = vitalService.insertAndUpdateVital(multiVitalReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
