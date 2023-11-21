package yezak.api.api.management_support.approval;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.management_support.approval.dto.ConfirmApprovalReq;
import yezak.api.api.management_support.approval.dto.CreateApprovalForm;
import yezak.api.api.management_support.approval.dto.CreateApprovalReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface ApprovalService {
    ResultResponse<?> getMyApprovalList(Long askFormId, String start, String end, Long totalConfirmStatuses);
    ResultResponse<?> getFormList();
    ResultResponse<?> getUseAndConfirmUser(Long formId);
    ResultResponse<?> getMyInfo();
    ResultResponse<?> createApprovalAndFile(CreateApprovalReq createApprovalReq, MultipartFile file) throws FileUploadException;
    ResultResponse<?> deleteApproval(Long id);
    ResultResponse<?> getApprovalList(Long askFormId, String start, String end, Long totalConfirmStatuses);
    ResultResponse<?> getApprovalDetail(Long id);
    ResultResponse<?> companionApproval(ConfirmApprovalReq confirmApprovalReq);
    ResultResponse<?> deleteForm(List<Long> ids);
    ResultResponse<?> createForm(CreateApprovalForm createApprovalForm);
    ResultResponse<?> confirmUserList();




}
