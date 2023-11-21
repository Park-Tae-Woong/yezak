package yezak.api.api.marketing.landing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.marketing.landing.dto.MarketingLandingModifyRequest;
import yezak.api.api.marketing.landing.dto.MarketingLandingRegistRequest;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/marketing/landing")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Marketing-landing", description = "마케팅/상담 > 랜딩페이지 관리 api")
public class MarketingLandingController {

    private final MarketingLandingService service;

    @Operation(summary = "리스트 조회" , description = "리스트 조회",
            parameters = {
                @Parameter(name = "searchKeyword", description = "검색어(유입경로명 or 비고내용)", example = "수술")
    })
    @GetMapping(value = "/get-list")
    public ResponseEntity<ApiResponse<?>> getList(@RequestParam(required = false) String searchKeyword) {
        ResultResponse<?> resultResponse = service.getList(searchKeyword);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "선택 삭제" , description = "선택 삭제",
            parameters = {
                    @Parameter(name = "idArr", description = "row ids", example = "1,2,3")
            })
    @DeleteMapping(value = "/delete-row")
    public ResponseEntity<ApiResponse<?>> deleteRow(@RequestParam String idArr) {
        ResultResponse<?> resultResponse = service.deleteRow(idArr);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "상세 페이지" , description = "상세 페이지",
            parameters = {
                    @Parameter(name = "id", description = "row id", example = "1")
            })
    @GetMapping(value = "/get-detail")
    public ResponseEntity<ApiResponse<?>> getDetail(@RequestParam Integer id) {
        ResultResponse<?> resultResponse = service.getDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "정보 수정" , description = "정보 수정")
    @PutMapping(value = "/modify-data")
    public ResponseEntity<ApiResponse<?>> modifyData(@RequestBody MarketingLandingModifyRequest request) throws Exception {
        ResultResponse<?> resultResponse = service.modifyData(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "신규 등록" , description = "신규 등록")
    @PostMapping(value = "/regist-data")
    public ResponseEntity<ApiResponse<?>> registData(@RequestBody MarketingLandingRegistRequest request) throws Exception {
        ResultResponse<?> resultResponse = service.registData(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


}
