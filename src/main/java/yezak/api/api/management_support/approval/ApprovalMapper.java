package yezak.api.api.management_support.approval;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.management_support.approval.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApprovalMapper {
    List<ApprovalListRes> getMyApprovalList(ApprovalListReq approvalListReq);
    List<Map<String, Object>> selectFormName(Long hospitalId);
    List<Map<String, Object>> selectStatus();
    List<Map<String, Object>> getFormList(Long hospitalId);
    UseAndConfirmUserRes getUseAndConfirmUser(Long formId);
    Map<String, Object> getMyInfo(Long id);
    void createApproval(CreateApprovalReq createApprovalReq);
    void createApprovalFile(ApprovalFileReq approvalFileReq);
    void deleteApproval(Long id);
    ApprovalListRes getApprovalList(AdminApprovalListReq approvalListReq);
    ApprovalDetailRes getApprovalDetail(Long id);
    CheckConfirmStatus checkConfirmStatus(Long id);
    CheckConfirmUserRes checkConfirmUser(Long askFormId);
    Long findFormIdById(Long id);
    void firstConfirmApproval(ConfirmApprovalReq confirmApprovalReq);
    void secondConfirmApproval(ConfirmApprovalReq confirmApprovalReq);
    void totalConfirmApproval(@Param("changeConfirmStatuses")Long changeConfirmStatuses, @Param("id") Long id);
    void deleteForm(Long id);
    void createForm(CreateApprovalForm createApprovalForm);
    List<Map<String, Object>> confirmUserList(Long hospitalId);
}
