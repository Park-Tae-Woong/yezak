package yezak.api.api.admin.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.admin.system.dto.SystemDto;
import yezak.api.api.admin.system.dto.SystemManageBackgroundStateChangeRequest;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/system")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "System-manage", description = "병원관리 > 시스템 관리")

public class SystemController {
    private final SystemService systemService;

    @GetMapping(value = "/get-background")
    @Operation(summary = "시스템 배경 정보 조회", description = "시스템 배경 정보 조회")
    public ResponseEntity<ApiResponse<?>> getBackgroundInfo () {
        ResultResponse<?> resultResponse = systemService.getBackgroundInfo();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PutMapping(value = "/modify-background-state")
    @Operation(summary = "시스템 배경 지정 상태 수정", description = "시스템 배경 지정 상태 수정")
    public ResponseEntity<ApiResponse<?>> modifyBackgroundState(@RequestBody SystemManageBackgroundStateChangeRequest request) {

        ResultResponse<?> resultResponse = systemService.modifyBackgroundState(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //파일 저장
    @PostMapping(value = "/save-background", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "이미지 업로드" , description = "이미지 업로드")
    public ResponseEntity<ApiResponse<?>> saveFileInfo (@RequestParam("file") MultipartFile file) throws FileUploadException {
        ResultResponse<?> resultResponse = systemService.saveFileInfo(file);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //navi 1뎁스 수정
    @PutMapping(value = "/update-navi1")
    @Operation(summary = "navi 1뎁스 수정" , description = "navi 1뎁스 수정")
    public ResponseEntity<ApiResponse<?>> customDepth1(@RequestBody List<SystemDto> systemDto) {
        ResultResponse<?> resultResponse = systemService.customDepth1(systemDto);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/get-navi1")
    @Operation(summary = "navi 1뎁스 리스트 조회", description = "navi 1뎁스 리스트 조회")
    public ResponseEntity<ApiResponse<?>> getNaviOneDepthList () {
        ResultResponse<?> resultResponse = systemService.getNaviOneDepthList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
