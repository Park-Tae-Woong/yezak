package yezak.api.api.management_support.meeting;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.management_support.meeting.dto.*;

import java.util.List;

@Mapper
public interface MeetingMapper {
    List<MeetingListDto> getMeetingList(MeetingListReq meetingListReq);
    Integer countMeetingList(MeetingListReq meetingListReq);

    void deleteMeetings(List<Long> id);

    void createMeeting(MeetingReq meetingReq);

    void createMeetingUser(MeetingReq meetingReq);

    MeetingDetailDto meetingDetail(@Param("id")Long id, @Param("hospitalId") Long hospitalId);
    List<ActiveUserDto> activeUsers(Long hospitalId);


    List<ConclusionListRes> conclusionList(Long conclusionId);

    void updateContent(UpdateContentReq updateContentReq);

    void updateConclusion(UpdateConclusionReq updateConclusionReq);

    void insertConclusion(InsertConclusionReq insertConclusionReq);

    void deleteConclusion(Long conclusionId);

    void saveFileInfo(MeetingAttachmentDto meetingAttachmentDto);

    ConclusionDetailDto normalConclusionDetail(Long conclusionId);

    void updateConclusionDetail(UpdateConclusionContentReq updateConclusionContentReq);

    AdminConclusionDetailDto managerConclusionDetail(Long conclusionId);

    Integer findGrade(Long userId);
    void updateEvaluation(UpdateEvaluationReq updateEvaluationReq);

    List<ConclusionFileListRes> conclusionFileList(Long conclusionId);

    void deleteConclusionFile(Long fileId);

    String findFilePathById(Long fileId);
    Integer countFileId(Long meetingConclusionId);
}
