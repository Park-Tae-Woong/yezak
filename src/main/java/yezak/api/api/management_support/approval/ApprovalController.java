package yezak.api.api.management_support.approval;

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
import yezak.api.api.management_support.approval.dto.ConfirmApprovalReq;
import yezak.api.api.management_support.approval.dto.CreateApprovalForm;
import yezak.api.api.management_support.approval.dto.CreateApprovalReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/approval")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Approval", description = "경영지원 > 결재관리")
public class ApprovalController {

    private final ApprovalService approvalService;
    @GetMapping(value = "/my-approval-List")
    @Operation(summary = "나의 결재요청 리스트", description = "나의 결재요청 리스트", parameters = {
            @Parameter(name = "askFormId", description = "서식명 id", example = "1"),
            @Parameter(name = "start", description = "조회 시작 날짜", example = "2023-03-03"),
            @Parameter(name = "end", description = "조회 끝 날짜", example = "2023-05-05"),
            @Parameter(name = "totalConfirmStatuses", description = "진행상태", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> getMyApprovalList(@RequestParam(required = false) Long askFormId, @RequestParam(required = false) String start,
                                                         @RequestParam(required = false) String end, @RequestParam(required = false) Long totalConfirmStatuses){
        ResultResponse<?> resultResponse = approvalService.getMyApprovalList(askFormId, start, end, totalConfirmStatuses);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/get-form-List")
    @Operation(summary = "서식 리스트", description = "서식 리스트")
    public ResponseEntity<ApiResponse<?>> getFormList(){
        ResultResponse<?> resultResponse = approvalService.getFormList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
    @GetMapping(value = "/get-use-confirm")
    @Operation(summary = "필드 사용 여부 및 승인 참조 대상", description = "필드 사용 여부 및 승인 참조 대상")
    public ResponseEntity<ApiResponse<?>> getUseAndConfirmUser(@RequestParam Long formId){
        ResultResponse<?> resultResponse = approvalService.getUseAndConfirmUser(formId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/get-my-info")
    @Operation(summary = "작성자 정보", description = "작성자 정보")
    public ResponseEntity<ApiResponse<?>> getMyInfo(){
        ResultResponse<?> resultResponse = approvalService.getMyInfo();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //서식 및 파일 저장
    @PostMapping(value = "/save-form-file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "파일 업로드 및 결재요청 생성" , description = "파일 업로드 및 결재요청 생성")
    public ResponseEntity<ApiResponse<?>> createApprovalAndFile(CreateApprovalReq createApprovalReq, MultipartFile file) throws FileUploadException {
        ResultResponse<?> resultResponse = approvalService.createApprovalAndFile(createApprovalReq, file);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @DeleteMapping(value = "/delete-approval")
    @Operation(summary = "결재요청 삭제", description = "결재요청 삭제", parameters = {
            @Parameter(name = "id", description = "결재요청 Id", example = "2")
    })
    public ResponseEntity<ApiResponse<?>> deleteApproval(Long id){
        ResultResponse<?> resultResponse = approvalService.deleteApproval(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/approval-List")
    @Operation(summary = "결재요청 리스트", description = "결재요청 리스트", parameters = {
            @Parameter(name = "askFormId", description = "서식명 id", example = "1"),
            @Parameter(name = "start", description = "조회 시작 날짜", example = "2023-03-03"),
            @Parameter(name = "end", description = "조회 끝 날짜", example = "2023-05-05"),
            @Parameter(name = "totalConfirmStatuses", description = "진행상태", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> getApprovalList(@RequestParam(required = false) Long askFormId, @RequestParam(required = false) String start,
                                                            @RequestParam(required = false) String end, @RequestParam(required = false) Long totalConfirmStatuses){
        ResultResponse<?> resultResponse = approvalService.getApprovalList(askFormId, start, end, totalConfirmStatuses);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/approval-detail")
    @Operation(summary = "결재요청 상세", description = "결재요청 상세", parameters = {
            @Parameter(name = "id", description = "결재id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> getApprovalList(@RequestParam Long id){
        ResultResponse<?> resultResponse = approvalService.getApprovalDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PostMapping(value = "/confirm-approval")
    @Operation(summary = "승인 및 반려" , description = "승인 및 반려")
    public ResponseEntity<ApiResponse<?>> companionApproval(@RequestBody ConfirmApprovalReq confirmApprovalReq) {
        ResultResponse<?> resultResponse = approvalService.companionApproval(confirmApprovalReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @DeleteMapping(value = "/delete-form")
    @Operation(summary = "서식 삭제", description = "서식 삭제", parameters = {
            @Parameter(name = "ids", description = "서식 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> deleteForm(@RequestParam List<Long> ids){
        ResultResponse<?> resultResponse = approvalService.deleteForm(ids);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
    @PostMapping(value = "/create-approval")
    @Operation(summary = "서식 생성" , description = "서식 생성")
    public ResponseEntity<ApiResponse<?>> createForm(@RequestBody CreateApprovalForm createApprovalForm) {
        ResultResponse<?> resultResponse = approvalService.createForm(createApprovalForm);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/user-list")
    @Operation(summary = "승인대상 유저 리스트", description = "승인대상 유저 리스트")
    public ResponseEntity<ApiResponse<?>> confirmUserList(){
        ResultResponse<?> resultResponse = approvalService.confirmUserList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


}
