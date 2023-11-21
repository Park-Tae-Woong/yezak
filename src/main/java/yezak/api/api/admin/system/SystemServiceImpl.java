package yezak.api.api.admin.system;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.admin.system.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.FileService;

import java.util.List;
import java.util.Map;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    private final SystemMapper systemMapper;
    private final FileService fileService;
    private final Integer systemId = 58;

    @Override
    public ResultResponse<?> saveFileInfo(MultipartFile file) throws FileUploadException {

        if (myDepth3Id().contains(systemId)) {
            if (file.getSize() <= 15728640) {
                // 파일을 업로드하고 저장된 정보를 받아옴
                BackgroundAttachmentDto backgroundAttachmentDto = fileService.saveBackgroundFile(file);

                // 저장된 정보와 추가 정보를 합쳐 DB에 저장
                backgroundAttachmentDto.setHospitalId(myHospitalId());
                systemMapper.saveFileInfo(backgroundAttachmentDto);
                return ResultResponse.builder()
                        .data(backgroundAttachmentDto.getFilePath())
                        .result(true)
                        .resultCode("200")
                        .build();

            } else {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("파일 크기가 15MB를 초과합니다.")
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
    public ResultResponse<?> customDepth1(List<SystemDto> systemDtos) {
        if (myDepth3Id().contains(systemId)) {
            for (SystemDto systemDto : systemDtos) {
                String koName = systemMapper.checkOriginName(systemDto.getId());
                Long d1nhId = systemMapper.findByDepthOne(myHospitalId(), systemDto.getId());
                if (d1nhId == null) {
                    InsertCustomDepthOne insertCustomDepthOne = InsertCustomDepthOne.builder()
                            .koName(systemDto.getKoName())
                            .hospitalId(myHospitalId())
                            .depth1NavigationId(systemDto.getId())
                            .build();
                    systemMapper.insertCustom(insertCustomDepthOne);
                } else {
                    if (systemDto.getKoName().equals(koName)) {
                        systemMapper.deleteDepth1Pivot(d1nhId);
                    }
                    systemDto.setId(d1nhId);
                    systemMapper.customDepth1(systemDto);
                }
            }
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> getNaviOneDepthList() {
        List<SystemMenuInfoResponse> systemMenuInfoResponses = systemMapper.getNaviList();
        for (SystemMenuInfoResponse systemMenuInfoResponse : systemMenuInfoResponses) {
            Long naviId = systemMapper.findByDepth1(systemMenuInfoResponse.getId(), myHospitalId());
            if (naviId != null) {
                systemMenuInfoResponse.setKoName(systemMapper.getNaviOneDepthId(myHospitalId(), naviId));
            }
        }
        return ResultResponse.builder()
                .data(systemMenuInfoResponses)
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> getBackgroundInfo() {
        Integer state = systemMapper.checkBackgroundState(myHospitalId());
        String path = null;
        if (state > 0) { // 0 : default / 1 : custom
            Map<String, Object> imgInfo = systemMapper.getBackgroundInfo(myHospitalId());
            path = imgInfo.get("filePath").toString();
        }
        SystemBackgroundImageInfoResponse response = SystemBackgroundImageInfoResponse.builder()
                .customWallpaperState(state)
                .fullPath(path)
                .build();
        return ResultResponse.builder()
                .data(response)
                .result(true)
                .resultCode("200")
                .build();
    }


    @Override
    public ResultResponse<?> modifyBackgroundState(SystemManageBackgroundStateChangeRequest request) {
        if (myDepth3Id().contains(systemId)) {
            systemMapper.modifyBackgroundState(SystemManageBackgroundStateChangeRequest.builder()
                    .customWallpaperState(request.getCustomWallpaperState())
                    .hospitalId(myHospitalId())
                    .build());
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
