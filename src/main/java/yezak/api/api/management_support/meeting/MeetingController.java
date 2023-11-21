package yezak.api.api.management_support.meeting;

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
import yezak.api.api.management_support.meeting.dto.MeetingReq;
import yezak.api.api.management_support.meeting.dto.MultipleProcessing;
import yezak.api.api.management_support.meeting.dto.UpdateConclusionContentReq;
import yezak.api.api.management_support.meeting.dto.UpdateEvaluationReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.FileService;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequestMapping(value = "/api/meeting")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Meeting", description = "경영지원 > 회의록")

public class MeetingController {
    private final MeetingService meetingService;
    private final FileService fileService;

    //회의 리스트
    @GetMapping(value = "/get-meetings")
    @Operation(summary = "회의 리스트", description = "회의 리스트", parameters = {
            @Parameter(name = "startDate", description = "조회 시작 날짜", example = "2002-02-02"),
            @Parameter(name = "endDate", description = "조회 마지막 날짜", example = "2022-05-11"),
            @Parameter(name = "searchValue", description = "검색어", example = "워크샵")
    })
    public ResponseEntity<ApiResponse<?>> getMeetingList(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String searchValue,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        ResultResponse<?> resultResponse = meetingService.getMeetingList(startDate, endDate, searchValue, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //회의 삭제
    @DeleteMapping(value = "/delete-meetings")
    @Operation(summary = "회의 삭제", description = "회의 삭제")
    public ResponseEntity<ApiResponse<?>> deleteMeetings(@RequestParam List<Long> ids) {
        ResultResponse<?> resultResponse = meetingService.deleteMeetings(ids);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //회의 생성
    @PostMapping(value = "/create-meeting")
    @Operation(summary = "회의 생성", description = "회의 생성")
    public ResponseEntity<ApiResponse<?>> createMeeting(@RequestBody MeetingReq meetingReq) {
        ResultResponse<?> resultResponse = meetingService.createMeeting(meetingReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //회의 상세
    @GetMapping(value = "/meeting-detail")
    @Operation(summary = "회의 상세", description = "회의 상세", parameters = {
            @Parameter(name = "id", description = "회의 id", example = "1"),
    })
    public ResponseEntity<ApiResponse<?>> meetingDetail(@RequestParam Long id) {
        ResultResponse<?> resultResponse = meetingService.meetingDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //회의 주최 및 참석 할 사람
    @GetMapping(value = "/active-users")
    @Operation(summary = "회의주최, 참석 가능한 유저 리스트", description = "회의주최, 참석 가능한 유저 리스트")
    public ResponseEntity<ApiResponse<?>> activeUsers() {
        ResultResponse<?> resultResponse = meetingService.activeUsers();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //멀티 처리
    @PostMapping(value = "/update-meeting-conclusion")
    @Operation(summary = "회의내용 수정, 결정사항 멀티처리", description = "회의내용 수정, 결정사항 멀티처리")
    public ResponseEntity<ApiResponse<?>> multiProcess(@RequestBody MultipleProcessing multipleProcessing) {
        ResultResponse<?> resultResponse = meetingService.multiProcess(multipleProcessing);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

//    //파일 저장
//    @PostMapping(value = "/save-meeting-attachment", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @Operation(summary = "회의 파일 저장", description = "회의 파일 저장", parameters = {
//            @Parameter(name = "meetingConclusionId", description = "결정사항 id", example = "1")
//    })
//    public ResponseEntity<ApiResponse<?>> saveFileInfo(@RequestParam("meetingConclusionId") Long meetingConclusionId,
//                                                       @RequestParam("file") MultipartFile file) throws FileUploadException {
//        ResultResponse<?> resultResponse = meetingService.saveFileInfo(meetingConclusionId, file);
//        return ResponseEntity.ok(ApiResponse.success(resultResponse));
//    }

    //일반권한 결정사항 상세
    @GetMapping(value = "/conclusion-detail")
    @Operation(summary = "결정사항 상세", description = "결정사항 상세", parameters = {
            @Parameter(name = "conclusionId", description = "결정사항 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> getConclusionDetail(@RequestParam Long conclusionId) {
        ResultResponse<?> resultResponse =  meetingService.getConclusionDetail(conclusionId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //상세내용 수정
    @PostMapping(value = "/update-conclusion-detail", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "결정사항 상세내용 수정", description = "결정사항 상세내용 수정")
    public ResponseEntity<ApiResponse<?>> updateMeetingConclusionDetail(UpdateConclusionContentReq updateConclusionContentReq,
                                                                        MultipartFile file0,
                                                                        MultipartFile file1,
                                                                        MultipartFile file2,
                                                                        MultipartFile file3,
                                                                        MultipartFile file4,
                                                                        @RequestParam(required = false) List<Long> removeFileIds) throws FileUploadException, UnsupportedEncodingException {
        ResultResponse<?> resultResponse = meetingService.updateConclusionDetail(updateConclusionContentReq, file0, file1, file2, file3, file4, removeFileIds);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


    //관리자 평가 수정
    @PutMapping(value = "/update-evaluation")
    @Operation(summary = "관리자 평가 수정", description = "관리자 평가 수정")
    public ResponseEntity<ApiResponse<?>> updateEvaluation(@RequestBody UpdateEvaluationReq updateEvaluationReq) {
        ResultResponse<?> resultResponse = meetingService.updateEvaluation(updateEvaluationReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

//    @DeleteMapping(value = "/delete-file")
//    @Operation(summary = "파일 삭제", description = "파일 삭제")
//    public ResponseEntity<ApiResponse<?>> deleteConclusionFile(@RequestParam String filePath) {
//        ResultResponse<?> resultResponse = fileService.deleteConclusionFile(filePath);
//        return ResponseEntity.ok(ApiResponse.success(resultResponse));
//    }
}
