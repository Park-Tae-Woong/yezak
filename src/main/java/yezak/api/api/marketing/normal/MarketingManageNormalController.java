package yezak.api.api.marketing.normal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.marketing.normal.dto.MarketingManageNormalListRequest;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/marketing/manage/normal")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Marketing-manage-normal", description = "마케팅/상담 > DB등록 상담 api")
public class MarketingManageNormalController {

    private final MarketingManageNormalService service;

    @Operation(summary = "예약상태 조회" , description = "예약상태 조회")
    @GetMapping(value = "/reservation-statuses-list")
    public ResponseEntity<ApiResponse<?>> getReservationStatusesList() {
        ResultResponse<?> resultResponse = service.getReservationStatusesList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "대기실 리스트" , description = "대기실 리스트")
    @GetMapping(value = "/room-list")
    public ResponseEntity<ApiResponse<?>> getRoomList() {
        ResultResponse<?> resultResponse = service.getRoomList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "리스트 조회" , description = "리스트 조회")
    @PostMapping(value = "/db-list")
    public ResponseEntity<ApiResponse<?>> getList(@RequestBody MarketingManageNormalListRequest request) {
        ResultResponse<?> resultResponse = service.getDbList(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
