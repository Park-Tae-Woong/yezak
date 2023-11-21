package yezak.api.api.admin.room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.admin.room.dto.RoomMulti;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/room")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Room-manage", description = "병원관리 > 대기실 관리")

public class RoomController {
    private final RoomService roomService;

    //수납실
    @GetMapping(value = "/storage-room")
    @Operation(summary = "수납실" , description = "수납실")
    public ResponseEntity<ApiResponse<?>> StorageRoom(){
        ResultResponse<?> resultResponse = roomService.StorageRoom();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //대기실 리스트(수납실 제외)
    @GetMapping(value = "/room-list")
    @Operation(summary = "대기실 리스트" , description = "대기실 리스트")
    public ResponseEntity<ApiResponse<?>> roomList(){

        ResultResponse<?> resultResponse = roomService.roomList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //멀티 처리
    @PostMapping(value = "/multi-process")
    @Operation(summary = "대기실 설정" , description = "대기실 설정")
    public ResponseEntity<ApiResponse<?>> multiProcess(@RequestBody RoomMulti roomMulti) {
        ResultResponse<?> resultResponse = roomService.multiProcess(roomMulti);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/get-schedule-categories")
    @Operation(summary = "상위 카테고리 리스트" , description = "상위 카테고리 리스트")
    public ResponseEntity<ApiResponse<?>> getScheduleCategoriesList(){
        ResultResponse<?> resultResponse = roomService.getScheduleCategoriesList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
