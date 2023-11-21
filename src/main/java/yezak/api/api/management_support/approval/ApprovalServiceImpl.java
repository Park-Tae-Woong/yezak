package yezak.api.api.management_support.approval;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.management_support.approval.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.FileService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
@Transactional

public class ApprovalServiceImpl implements ApprovalService{
    private final ApprovalMapper approvalMapper;
    private final FileService fileService;
    private final Integer normalApprovalId = 27;
    private final Integer adminApprovalId = 28;

    @Override
    public ResultResponse<?> getMyApprovalList(Long askFormId, String start, String end, Long totalConfirmStatuses) {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            ApprovalListReq req = ApprovalListReq.builder()
                    .askFormId(askFormId)
                    .start(start)
                    .end(end)
                    .totalConfirmStatuses(totalConfirmStatuses)
                    .hospitalId(myHospitalId())
                    .myUserId(myUserId())
                    .build();

            Map<String, Object> map = new HashMap<>();
            map.put("formName", approvalMapper.selectFormName(myHospitalId()));
            map.put("statusName", approvalMapper.selectStatus());

            return ResultResponse.builder()
                    .data(approvalMapper.getMyApprovalList(req))
                    .select(map)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getFormList() {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            return ResultResponse.builder()
                    .data(approvalMapper.getFormList(myHospitalId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getUseAndConfirmUser(Long formId) {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            return ResultResponse.builder()
                    .data(approvalMapper.getUseAndConfirmUser(formId))
                    .result(true)
                    .resultMessage("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getMyInfo() {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            return ResultResponse.builder()
                    .data(approvalMapper.getMyInfo(myUserId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }


    @Override
    public ResultResponse<?> createApprovalAndFile(CreateApprovalReq createApprovalReq, MultipartFile file) throws FileUploadException {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            CreateApprovalReq req = CreateApprovalReq.builder()
                    .askedAt(createApprovalReq.getAskedAt())
                    .title(createApprovalReq.getTitle())
                    .content(createApprovalReq.getContent())
                    .amount(createApprovalReq.getAmount())
                    .date(createApprovalReq.getDate())
                    .remark(createApprovalReq.getRemark())
                    .askUserId(myUserId())
                    .askFormId(createApprovalReq.getAskFormId())
                    .hospitalId(myHospitalId())
                    .build();
            approvalMapper.createApproval(req);

            Long id = req.getId();

            ApprovalFileReq approvalFileReq = fileService.createApprovalFile(file);
            ApprovalFileReq fileReq = ApprovalFileReq.builder()
                    .askId(id)
                    .fileName(approvalFileReq.getFileName())
                    .filePath(approvalFileReq.getFilePath())
                    .fileExtension(approvalFileReq.getFileExtension())
                    .fileSize(approvalFileReq.getFileSize())
                    .build();
            approvalMapper.createApprovalFile(fileReq);

            return ResultResponse.builder()
                    .data(fileReq.getFilePath())
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> deleteApproval(Long id) {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            approvalMapper.deleteApproval(id);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("삭제되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getApprovalList(Long askFormId, String start, String end, Long totalConfirmStatuses) {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            AdminApprovalListReq req = AdminApprovalListReq.builder()
                    .askFormId(askFormId)
                    .start(start)
                    .end(end)
                    .totalConfirmStatuses(totalConfirmStatuses)
                    .hospitalId(myHospitalId())
                    .build();

            Map<String, Object> map = new HashMap<>();
            map.put("formName", approvalMapper.selectFormName(myHospitalId()));
            map.put("statusName", approvalMapper.selectStatus());

            return ResultResponse.builder()
                    .data(approvalMapper.getApprovalList(req))
                    .select(map)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getApprovalDetail(Long id) {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            return ResultResponse.builder()
                    .data(approvalMapper.getApprovalDetail(id))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> companionApproval(ConfirmApprovalReq confirmApprovalReq) {
        if(myDepth3Id().contains(adminApprovalId)) {
            Long formId = approvalMapper.findFormIdById(confirmApprovalReq.getId());
            CheckConfirmStatus checkConfirmStatus = approvalMapper.checkConfirmStatus(confirmApprovalReq.getId());
            CheckConfirmUserRes checkConfirmUserRes = approvalMapper.checkConfirmUser(formId);

            if (checkConfirmUserRes == null) {
                confirmApprovalReq.setChangeConfirmStatuses(2L);
                approvalMapper.firstConfirmApproval(confirmApprovalReq);
                approvalMapper.secondConfirmApproval(confirmApprovalReq);
                approvalMapper.totalConfirmApproval(confirmApprovalReq.getChangeConfirmStatuses(), confirmApprovalReq.getId());
                return ResultResponse.builder()
                        .result(true)
                        .resultCode("200")
                        .resultMessage("자동승인 되었습니다.")
                        .build();
            }

            //1차 승인이 대기상태 및 1차 승인자가 있을때
            if(checkConfirmStatus.getFirstConfirmStatuses() == 1 && checkConfirmUserRes.getFirstConfirmUser() != null) {
                //1차 승인자가 로그인한 유저가 아닐때
                if(!checkConfirmUserRes.getFirstConfirmUser().equals(myUserId())){
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("담당자가 아닙니다.")
                            .build();
                }
                approvalMapper.firstConfirmApproval(confirmApprovalReq);
                //2차 승인자가 없을때 전체 상태 변환
                if (checkConfirmUserRes.getSecondConfirmUser() == null) {
                    approvalMapper.totalConfirmApproval(confirmApprovalReq.getChangeConfirmStatuses(), confirmApprovalReq.getId());
                }
            }
            //2차 승인상태가 대기일때, 2차 승인자가 있을때
            else if (checkConfirmStatus.getSecondConfirmStatuses() == 1 && checkConfirmUserRes.getSecondConfirmUser() != null) {
                //1차 승인상태가 대기나 반려일때
                if (checkConfirmStatus.getFirstConfirmStatuses() == 1) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("1차 승인이 완료되지 않았습니다.")
                            .build();
                }
                if (checkConfirmStatus.getFirstConfirmStatuses() == 2) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("이미 반려된 요청입니다.")
                            .build();
                }
                //2차 승인자가 로그인한 유저가 아닐때
                if (!checkConfirmUserRes.getSecondConfirmUser().equals(myUserId())){
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("담당자가 아닙니다.")
                            .build();
                }
                approvalMapper.secondConfirmApproval(confirmApprovalReq);
                approvalMapper.totalConfirmApproval(confirmApprovalReq.getChangeConfirmStatuses(), confirmApprovalReq.getId());
            }

            String resultMessage;
            if(confirmApprovalReq.getChangeConfirmStatuses() == 2){
                resultMessage = "승인되었습니다.";
            }
            else {
                resultMessage = "반려되었습니다.";

            }

            return ResultResponse.builder()
                    .resultMessage(resultMessage)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> deleteForm(List<Long> ids) {
        if(myDepth3Id().contains(adminApprovalId)) {
            for (Long id : ids) {
                approvalMapper.deleteForm(id);

            }
            return ResultResponse.builder()
                    .resultMessage("삭제되었습니다.")
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> createForm(CreateApprovalForm createApprovalForm) {
        if(myDepth3Id().contains(adminApprovalId)) {
            createApprovalForm.setHospitalId(myHospitalId());
            approvalMapper.createForm(createApprovalForm);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("등록되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> confirmUserList() {
        if(myDepth3Id().contains(normalApprovalId) || myDepth3Id().contains(adminApprovalId)) {
            return ResultResponse.builder()
                    .data(approvalMapper.confirmUserList(myHospitalId()))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

}
