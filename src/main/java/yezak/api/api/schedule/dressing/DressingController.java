package yezak.api.api.schedule.dressing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.schedule.dressing.dto.InsertDressingReq;
import yezak.api.api.schedule.dressing.dto.InsertDressingUserReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/dressing")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "dressing", description = "스케줄 > 드레싱실")
public class DressingController {
    private final DressingService dressingService;

    @GetMapping(value = "/search-patient")
    @Operation(summary = "환자 검색" , description = "환자 검색", parameters = {
            @Parameter(name = "searchValue", description = "검색어", example = "박태웅")
    })
    public ResponseEntity<ApiResponse<?>> selectPatientsInfo(@RequestParam(required = false) String searchValue){
        ResultResponse<?> resultResponse = dressingService.selectPatientsInfo(searchValue);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/wait-list")
    @Operation(summary = "대기리스트" , description = "대기리스트")
    public ResponseEntity<ApiResponse<?>> getWaitList(Long roomId){
        ResultResponse<?> resultResponse = dressingService.getWaitList(roomId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/select-room-list")
    @Operation(summary = "select용 대기실리스트" , description = "select용 대기실리스트")
    public ResponseEntity<ApiResponse<?>> selectRoomList(){
        ResultResponse<?> resultResponse = dressingService.selectRoomList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/basic-info")
    @Operation(summary = "수술기본정보" , description = "수술기본정보", parameters = {
            @Parameter(name = "patientId", description = "환자 id", example = "1"),
            @Parameter(name = "operationId", description = "수술 id", example = "1"),
            @Parameter(name = "date", description = "조회할 날짜", example = "2023-10-13")
    })
    public ResponseEntity<ApiResponse<?>> operationBasicInfo(@RequestParam Long patientId,
                                                             @RequestParam Long operationId,
                                                             @RequestParam String date){
        ResultResponse<?> resultResponse = dressingService.operationBasicInfo(patientId, operationId, date);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/select-operation-date-list")
    @Operation(summary = "select용 수술일자 리스트" , description = "select용 수술일자 리스트", parameters = {
            @Parameter(name = "patientId", description = "환자 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> selectOperationDateList(@RequestParam Long patientId){
        ResultResponse<?> resultResponse = dressingService.selectOperationDateList(patientId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/select-dressing-date-list")
    @Operation(summary = "select용 드레싱일자 리스트" , description = "select용 드레싱일자 리스트", parameters = {
            @Parameter(name = "patientId", description = "환자 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> selectDressingDateList(@RequestParam Long patientId){
        ResultResponse<?> resultResponse = dressingService.selectDressingDateList(patientId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/patient-vital")
    @Operation(summary = "환자 바이탈" , description = "환자 바이탈", parameters = {
            @Parameter(name = "patientId", description = "환자 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> patientVital(@RequestParam Long patientId){
        ResultResponse<?> resultResponse = dressingService.patientVital(patientId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/detail")
    @Operation(summary = "드레싱 세부" , description = "드레싱 세부", parameters = {
            @Parameter(name = "dressingId", description = "드레싱id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> dressingDetail(@RequestParam Long dressingId){
        ResultResponse<?> resultResponse = dressingService.dressingDetail(dressingId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PostMapping(value = "/insert", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "드레싱 차트 생성" , description = "드레싱 차트 생성")
    public ResponseEntity<ApiResponse<?>> insertDressing(@RequestPart InsertDressingReq insertDressingReq,
                                                         @RequestPart List<InsertDressingUserReq> insertDressingUserReqs,
                                                         @RequestPart(required = false) MultipartFile file0,
                                                         @RequestPart(required = false) MultipartFile file1,
                                                         @RequestPart(required = false) MultipartFile file2,
                                                         @RequestPart(required = false) MultipartFile file3,
                                                         @RequestPart(required = false) MultipartFile file4) throws FileUploadException {
        ResultResponse<?> resultResponse = dressingService.insertDressing(insertDressingReq, insertDressingUserReqs, file0, file1, file2, file3, file4);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
