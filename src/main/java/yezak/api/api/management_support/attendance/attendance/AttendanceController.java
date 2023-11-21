package yezak.api.api.management_support.attendance.attendance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.ExcelUploadService;

@RequestMapping(value = "/api/attendance")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Attendance", description = "경영지원 > 근태관리 > 근무일정")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final ExcelUploadService excelUploadService;

    @GetMapping(value = "/record")
    @Operation(summary = "근무일정 조회" , description = "근무일정 조회",parameters = {
            @Parameter(name = "name", description = "이름", example = "박태웅"),
            @Parameter(name = "start", description = "날짜", example = "2022-05-11"),
            @Parameter(name = "end", description = "날짜", example = "2022-05-11")
    })
    public ResponseEntity<ApiResponse<?>> attendanceRecord(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end) {
        ResultResponse<?> resultResponse = attendanceService.attendanceRecord(name, start, end);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


    //엑셀 업로드
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "엑셀 업로드" , description = "엑셀 업로드")
    public ResponseEntity<ApiResponse<?>> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            ResultResponse<?> resultResponse = ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("파일이 없습니다.")
                    .build();
            return ResponseEntity.ok(ApiResponse.success(resultResponse));
        }
        try {
            ResultResponse<?> resultResponse = excelUploadService.uploadExcelFile(file);
            return ResponseEntity.ok(ApiResponse.success(resultResponse));
        } catch (Exception e) {
            e.printStackTrace();
            ResultResponse<?> resultResponse = ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("업로드 실패")
                    .build();
            return ResponseEntity.ok(ApiResponse.success(resultResponse));
        }
    }

}
