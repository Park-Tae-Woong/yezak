package yezak.api.api.management_support.meeting;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.management_support.meeting.dto.MeetingReq;
import yezak.api.api.management_support.meeting.dto.MultipleProcessing;
import yezak.api.api.management_support.meeting.dto.UpdateConclusionContentReq;
import yezak.api.api.management_support.meeting.dto.UpdateEvaluationReq;
import yezak.api.global.common.ResultResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface MeetingService {
    ResultResponse<?> getMeetingList(String startDate, String endDate, String searchValue, Integer pageNum, Integer pageSize);

    ResultResponse<?> deleteMeetings(List<Long> id);

    ResultResponse<?> activeUsers();

    ResultResponse<?> createMeeting(MeetingReq meetingReq);

    ResultResponse<?> meetingDetail(Long id);

    ResultResponse<?> multiProcess(MultipleProcessing multipleProcessing);

//    ResultResponse<?> saveFileInfo(Long meetingConclusionId, MultipartFile file) throws FileUploadException;

    ResultResponse<?> getConclusionDetail(Long conclusionId);

    ResultResponse<?> updateConclusionDetail(UpdateConclusionContentReq updateConclusionContentReq,
                                             MultipartFile file0,
                                             MultipartFile file1,
                                             MultipartFile file2,
                                             MultipartFile file3,
                                             MultipartFile file4,
                                             List<Long> removeFileIds) throws FileUploadException, UnsupportedEncodingException;

    ResultResponse<?> updateEvaluation(UpdateEvaluationReq updateEvaluationReq);


}
