package yezak.api.api.management_support.inventory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.management_support.inventory.dto.CreateInventoryLogReq;
import yezak.api.api.management_support.inventory.dto.UseMaterialReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/inventory")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "inventory", description = "경영지원 > 재고관리")

public class InventoryController {

    private final InventoryService inventoryService;

    //회의 리스트
    @GetMapping(value = "/get-inventory-list")
    @Operation(summary = "재고 리스트", description = "재고 리스트", parameters = {
            @Parameter(name = "searchValue", description = "검색어", example = "AA"),
            @Parameter(name = "sortType", description = "정렬 기준", example = "asc or desc"),
            @Parameter(name = "pageNum", description = "페이지번호", example = "1"),
            @Parameter(name = "pageSize", description = "페이지크기", example = "15")
    })
    public ResponseEntity<ApiResponse<?>> getInventoryList(
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) String sortType,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        ResultResponse<?> resultResponse = inventoryService.getInventoryList(searchValue, sortType, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
    @GetMapping(value = "/get-inventory-detail")
    @Operation(summary = "재고 상세", description = "재고 상세", parameters = {
            @Parameter(name = "id", description = "1", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> getInventoryDetail(@RequestParam Long id){
        ResultResponse<?> resultResponse = inventoryService.getInventoryDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PostMapping(value = "/create-inventory-log")
    @Operation(summary = "재고 조정", description = "재고 조정")
    public ResponseEntity<ApiResponse<?>> createInventoryLog(@RequestBody CreateInventoryLogReq createInventoryLogReq){
        ResultResponse<?> resultResponse = inventoryService.createInventoryLog(createInventoryLogReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/get-material-list")
    @Operation(summary = "재료대 리스트", description = "재료대 리스트")
    public ResponseEntity<ApiResponse<?>> getMaterialList(@RequestParam(required = false) String searchValue, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        ResultResponse<?> resultResponse = inventoryService.getMaterialList(searchValue, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PostMapping(value = "/use-inventory")
    @Operation(summary = "사용할 재고 등록", description = "사용할 재고 등록")
    public ResponseEntity<ApiResponse<?>> useMaterial(@RequestBody List<UseMaterialReq> useMaterialReqs){
        ResultResponse<?> resultResponse = inventoryService.useMaterial(useMaterialReqs);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/get-inventory-log")
    @Operation(summary = "재고 로그", description = "재고 리스트", parameters = {
            @Parameter(name = "start", description = "시작날짜", example = "2022-03-03"),
            @Parameter(name = "end", description = "끝날짜", example = "2023-08-01"),
            @Parameter(name = "controlTypeId", description = "입고, 사용", example = "1"),
            @Parameter(name = "searchValue", description = "검색어", example = "A"),
            @Parameter(name = "pageNum", description = "페이지번호", example = "1"),
            @Parameter(name = "pageSize", description = "페이지크기", example = "15")
    })
    public ResponseEntity<ApiResponse<?>> inventoryLogList(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) Long controlTypeId,
            @RequestParam(required = false) String searchValue,
            @RequestParam int pageNum,
            @RequestParam int pageSize) {
        ResultResponse<?> resultResponse = inventoryService.inventoryLogList(start, end, controlTypeId, searchValue, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
