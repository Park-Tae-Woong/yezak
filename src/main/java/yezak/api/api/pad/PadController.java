package yezak.api.api.pad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.pad.dto.NewPatientAndVisitPurpose;
import yezak.api.api.pad.dto.VisitPurposeReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/pad")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "패드", description = "패드")
public class PadController {

    private final PadService padService;

    @Operation(summary = "접수 초기화면",
            parameters = {
                    @Parameter(name = "name", description = "이름", example = "박태웅"),
                    @Parameter(name = "phoneNumber", description = "전화번호", example = "01042845560")
            })
    @GetMapping(value = "/reception")
    public ResponseEntity<ApiResponse> padReception(@RequestParam String name, @RequestParam String phoneNumber){
        ResultResponse result = padService.padReception(name, phoneNumber);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "신규환자 패드 접수")
    @PostMapping(value = "/new-patient")
    public ResponseEntity<ApiResponse> newPatientAndVisitPurpose(@RequestBody NewPatientAndVisitPurpose newPatientAndVisitPurpose){
        ResultResponse result = padService.newPatientAndVisitPurpose(newPatientAndVisitPurpose);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "기존환자 패드 접수")
    @PostMapping(value = "/old-patient")
    public ResponseEntity<ApiResponse> oldPatientVisitPurpose(@RequestBody VisitPurposeReq visitPurposeReq){
        ResultResponse result = padService.oldPatientVisitPurpose(visitPurposeReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
