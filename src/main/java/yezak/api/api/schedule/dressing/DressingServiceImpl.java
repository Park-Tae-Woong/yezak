package yezak.api.api.schedule.dressing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.schedule.dressing.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.FileService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class DressingServiceImpl implements DressingService {
    private final DressingMapper dressingMapper;
    private final FileService fileService;

    private final Integer dressing = 7;

    @Override
    public ResultResponse selectPatientsInfo(String searchValue) {
        if (!myDepth3Id().contains(dressing)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(dressingMapper.selectPatientsInfo(searchValue))
                .build();
    }

    @Override
    public ResultResponse getWaitList(Long roomId) {
        if (!myDepth3Id().contains(dressing)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(dressingMapper.getWaitList(myHospitalId(), roomId))
                .build();
    }

    @Override
    public ResultResponse selectRoomList() {
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(dressingMapper.selectRoomList(myHospitalId()))
                .build();
    }

    @Override
    public ResultResponse operationBasicInfo(Long patientId, Long operationId, String date) {
        if (!myDepth3Id().contains(dressing)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        OperationBasicInfoReq infoReq = OperationBasicInfoReq.builder()
                .patientId(patientId)
                .operationId(operationId)
                .hospitalId(myHospitalId())
                .date(date)
                .build();

        Map<String, Object> map = new HashMap<>();
        map.put("operationBasicInfo", dressingMapper.operationBasicInfo(infoReq));
        map.put("feeList", dressingMapper.operationBasicInfoList(infoReq));

        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(map)
                .build();
    }

    @Override
    public ResultResponse selectOperationDateList(Long patientId) {
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(dressingMapper.selectOperationDateList(patientId))
                .build();
    }

    @Override
    public ResultResponse selectDressingDateList(Long patientId) {
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(dressingMapper.selectDressingDateList(patientId))
                .build();
    }

    @Override
    public ResultResponse patientVital(Long patientId) {
        if (!myDepth3Id().contains(dressing)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(dressingMapper.patientVital(patientId))
                .build();
    }

    @Override
    public ResultResponse dressingDetail(Long dressingId) {
        if (!myDepth3Id().contains(dressing)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .data(dressingMapper.dressingDetail(dressingId))
                .build();
    }

    @Override
    public ResultResponse insertDressing(InsertDressingReq insertDressingReq,
                                         List<InsertDressingUserReq> insertDressingUserReqs,
                                         MultipartFile file0,
                                         MultipartFile file1,
                                         MultipartFile file2,
                                         MultipartFile file3,
                                         MultipartFile file4) throws FileUploadException {
        if (!myDepth3Id().contains(dressing)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

        dressingMapper.insertDressing(insertDressingReq);

        Long dressingId = insertDressingReq.getId();

        List<MultipartFile> files = Arrays.asList(file0, file1, file2, file3, file4);
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                InsertDressingFileReq insertDressingFileReq = fileService.insertDressingFile(file);
                insertDressingFileReq.setDressingId(dressingId);
                dressingMapper.insertDressingFile(insertDressingFileReq);
            }
        }

        for (InsertDressingUserReq insertDressingUserReq : insertDressingUserReqs){
            insertDressingUserReq.setDressingId(dressingId);
            dressingMapper.insertDressingUser(insertDressingUserReq);
        }

        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("저장되었습니다.")
                .build();
    }
}

