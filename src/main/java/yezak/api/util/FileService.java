package yezak.api.util;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.management_support.approval.dto.ApprovalFileReq;
import yezak.api.api.schedule.dressing.dto.InsertDressingFileReq;
import yezak.api.api.setting.imageForm.dto.ImageFormAttachmentDto;
import yezak.api.api.management_support.meeting.dto.MeetingAttachmentDto;
import yezak.api.api.admin.system.dto.BackgroundAttachmentDto;
import yezak.api.global.common.ResultResponse;

import java.io.UnsupportedEncodingException;

public interface FileService {

    ImageFormAttachmentDto saveImageFile(MultipartFile file) throws FileUploadException;

    MeetingAttachmentDto saveMeetingFile(MultipartFile file) throws FileUploadException, UnsupportedEncodingException;

    BackgroundAttachmentDto saveBackgroundFile(MultipartFile file) throws FileUploadException;

    ResultResponse<?> getMarketingExcelFile();

    ApprovalFileReq createApprovalFile(MultipartFile file) throws FileUploadException;

    ResultResponse deleteFile(String fileUrl);

    InsertDressingFileReq insertDressingFile(MultipartFile file) throws FileUploadException;



}
