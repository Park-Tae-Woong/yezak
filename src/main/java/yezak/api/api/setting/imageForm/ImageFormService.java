package yezak.api.api.setting.imageForm;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.setting.imageForm.dto.ImageFormReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface ImageFormService {

    ResultResponse<?> saveImageFormAndFile (ImageFormReq imageFormReq, MultipartFile file) throws FileUploadException;
    ResultResponse<?> getFormList(String searchValue);
    ResultResponse<?> deleteForm(List<Long> ids);

}
