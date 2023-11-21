package yezak.api.api.management_support.meeting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.management_support.meeting.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.FileService;
import yezak.api.util.Page;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    private final MeetingMapper meetingMapper;
    private final FileService fileService;
    private final Integer meetingAuthId = 26;

    @Override
    public ResultResponse<?> getMeetingList(String startDate, String endDate, String searchValue, Integer pageNum, Integer pageSize) {
        if (myDepth3Id().contains(meetingAuthId)) {
            int offset = (pageNum - 1) * pageSize;
            MeetingListReq meetingListReq = new MeetingListReq(startDate, endDate, searchValue, myHospitalId(), offset, pageSize);

            Integer totalCount = meetingMapper.countMeetingList(meetingListReq);

            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double) totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(meetingMapper.getMeetingList(meetingListReq))
                    .page(page)
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
    public ResultResponse<?> deleteMeetings(List<Long> id) {
        if (myDepth3Id().contains(meetingAuthId)) {
            meetingMapper.deleteMeetings(id);
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
    public ResultResponse<?> activeUsers() {
        if (myDepth3Id().contains(meetingAuthId)) {
            return ResultResponse.builder()
                    .data(meetingMapper.activeUsers(myHospitalId()))
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
    public ResultResponse<?> createMeeting(MeetingReq meetingReq) {
        if (myDepth3Id().contains(meetingAuthId)) {
            meetingReq.setHospitalId(myHospitalId());
            meetingMapper.createMeeting(meetingReq);

            Long meetingId = meetingReq.getId();

            meetingReq.setMeetingId(meetingId);
            meetingMapper.createMeetingUser(meetingReq);
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
    public ResultResponse<?> meetingDetail(Long id) {
        if (myDepth3Id().contains(meetingAuthId)) {
            MeetingDetailDto meetingDetailDto = meetingMapper.meetingDetail(id, myHospitalId());
            if (meetingDetailDto != null) {
                List<ConclusionListRes> conclusionListRes = meetingMapper.conclusionList(id);
                meetingDetailDto.setConclusionListRes(conclusionListRes);
            }
            return ResultResponse.builder()
                    .data(meetingDetailDto)
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
    public ResultResponse<?> multiProcess(MultipleProcessing multipleProcessing) {
        if (myDepth3Id().contains(meetingAuthId)) {

            if (multipleProcessing.getUpdateContentReq() != null) {
                meetingMapper.updateContent(multipleProcessing.getUpdateContentReq());
            }

            if (multipleProcessing.getDeleteConclusionReqs() != null) {
                for (Long ConclusionId : multipleProcessing.getDeleteConclusionReqs()) {
                    meetingMapper.deleteConclusion(ConclusionId);
                }
            }

            if (multipleProcessing.getUpdateConclusionReqs() != null) {
                for (UpdateConclusionReq updateConclusionReq : multipleProcessing.getUpdateConclusionReqs()) {
                    meetingMapper.updateConclusion(updateConclusionReq);
                }

            }
            if (multipleProcessing.getInsertConclusionReqs() != null) {
                for (InsertConclusionReq insertConclusionReq : multipleProcessing.getInsertConclusionReqs()) {
                    meetingMapper.insertConclusion(insertConclusionReq);
                }
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

//    @Override
//    public ResultResponse<?> saveFileInfo(Long meetingConclusionId, MultipartFile file) throws FileUploadException {
//        if (myDepth3Id().contains(meetingAuthId)) {
//            if (file.getSize() <= 15728640) {
//                // 파일을 업로드하고 저장된 정보를 받아옴
//                MeetingAttachmentDto meetingAttachmentDto = fileService.saveMeetingFile(file);
//                meetingAttachmentDto.setMeetingConclusionId(meetingConclusionId);
//                meetingMapper.saveFileInfo(meetingAttachmentDto);
//                return ResultResponse.builder()
//                        .data(meetingAttachmentDto.getFilePath())
//                        .result(true)
//                        .resultCode("200")
//                        .build();
//
//            } else {
//                return ResultResponse.builder()
//                        .result(false)
//                        .resultCode("400")
//                        .resultMessage("파일 크기가 15MB를 초과합니다.")
//                        .build();
//            }
//        } else {
//            return ResultResponse.builder()
//                    .result(false)
//                    .resultCode("401")
//                    .resultMessage("권한이 없습니다.")
//                    .build();
//        }
//    }

    @Override
    public ResultResponse<?> getConclusionDetail(Long conclusionId) {
        if (myDepth3Id().contains(meetingAuthId)) {
            Integer gradeId = meetingMapper.findGrade(myUserId());

            ConclusionDetailDto conclusionDetailDto = meetingMapper.normalConclusionDetail(conclusionId);
            conclusionDetailDto.setConclusionFileListResList(meetingMapper.conclusionFileList(conclusionId));
            AdminConclusionDetailDto adminConclusionDetailDto = meetingMapper.managerConclusionDetail(conclusionId);
            adminConclusionDetailDto.setConclusionFileListResList(meetingMapper.conclusionFileList(conclusionId));

            if (gradeId == 1 || gradeId == 2) {
                return ResultResponse.builder()
                        .data(adminConclusionDetailDto)
                        .result(true)
                        .resultCode("200")
                        .build();
            } else {
                return ResultResponse.builder()
                        .data(conclusionDetailDto)
                        .result(true)
                        .resultCode("200")
                        .build();
            }
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> updateConclusionDetail(UpdateConclusionContentReq updateConclusionContentReq,
                                                    MultipartFile file0,
                                                    MultipartFile file1,
                                                    MultipartFile file2,
                                                    MultipartFile file3,
                                                    MultipartFile file4,
                                                    List<Long> removeFileIds) throws FileUploadException, UnsupportedEncodingException {
        if (myDepth3Id().contains(meetingAuthId)) {
            List<MultipartFile> files = Arrays.asList(file0, file1, file2, file3, file4);
            Integer countFileId = meetingMapper.countFileId(updateConclusionContentReq.getConclusionId());

            long uploadFileCount = files.stream()
                    .filter(file -> file != null && !file.isEmpty())
                    .count();

            if (removeFileIds != null) {
                for (Long fileId : removeFileIds) {
                    String filePath = meetingMapper.findFilePathById(fileId);
                    if (filePath == null) {
                        return ResultResponse.builder()
                                .resultCode("400")
                                .result(false)
                                .resultMessage("삭제할 파일이 없습니다.")
                                .build();
                    }

                    if (countFileId - removeFileIds.size() + uploadFileCount > 5) {
                        return ResultResponse.builder()
                                .result(false)
                                .resultCode("400")
                                .resultMessage("파일 등록은 5개까지만 가능합니다.1" + removeFileIds.size() + uploadFileCount)
                                .build();
                    }
                    fileService.deleteFile(filePath);
                    meetingMapper.deleteConclusionFile(fileId);
                }
            } else {
                if (countFileId >= 5) {
                    return ResultResponse.builder()
                            .result(false)
                            .resultCode("400")
                            .resultMessage("파일 등록은 5개까지만 가능합니다.2")
                            .build();
                }
            }

            List<String> fileInfo = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {

                    MeetingAttachmentDto meetingAttachmentDto = fileService.saveMeetingFile(file);
                    meetingAttachmentDto.setMeetingConclusionId(updateConclusionContentReq.getConclusionId());
                    meetingMapper.saveFileInfo(meetingAttachmentDto);
                    fileInfo.add(meetingAttachmentDto.getFilePath());
                }
            }

            meetingMapper.updateConclusionDetail(updateConclusionContentReq);

            return ResultResponse.builder()
                    .data(fileInfo)
                    .result(true)
                    .resultCode("200")
                    .resultMessage("저장되었습니다.")
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
    public ResultResponse<?> updateEvaluation(UpdateEvaluationReq updateEvaluationReq) {
        if (myDepth3Id().contains(meetingAuthId)) {
            meetingMapper.updateEvaluation(updateEvaluationReq);
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
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
