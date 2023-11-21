package yezak.api.api.admin.system;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.admin.system.dto.SystemDto;
import yezak.api.api.admin.system.dto.SystemManageBackgroundStateChangeRequest;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface SystemService {

    ResultResponse<?> saveFileInfo(MultipartFile file) throws FileUploadException;

    ResultResponse<?> customDepth1(List<SystemDto> systemDto);


    ResultResponse<?> getNaviOneDepthList();

    ResultResponse<?> getBackgroundInfo();

    ResultResponse<?> modifyBackgroundState(SystemManageBackgroundStateChangeRequest request);
}
