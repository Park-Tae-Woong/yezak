package yezak.api.api.setting.imageForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.setting.imageForm.dto.ImageFormAttachmentDto;
import yezak.api.api.setting.imageForm.dto.ImageFormFilter;
import yezak.api.api.setting.imageForm.dto.ImageFormReq;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.FileService;

import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class ImageFormServiceImpl implements ImageFormService{

    private final ImageFormMapper imageFormMapper;
    private final FileService fileService;
    private final Integer imageFormId = 60;

    @Override
    public ResultResponse<?> saveImageFormAndFile(ImageFormReq imageFormReq, MultipartFile file) throws FileUploadException {
        if(myDepth3Id().contains(imageFormId)) {
            imageFormReq.setHospitalId(myHospitalId());

            if (imageFormMapper.findDuplicateName(imageFormReq.getName()) != null) {
                throw new IllegalArgumentException("같은 이름의 파일이 있습니다.");
            }
            imageFormMapper.insertForm(imageFormReq);
            Long imageFormId = imageFormReq.getId();
            ImageFormAttachmentDto imageFormAttachmentDto = fileService.saveImageFile(file);

            imageFormAttachmentDto.setImageFormId(imageFormId);
            imageFormMapper.saveFileInfo(imageFormAttachmentDto);

            return ResultResponse.builder()
                    .data(imageFormAttachmentDto.getFilePath())
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
    public ResultResponse<?> getFormList(String searchValue) {
        if(myDepth3Id().contains(imageFormId)) {
            ImageFormFilter imageFormFilter = new ImageFormFilter(myHospitalId(), searchValue);
            return ResultResponse.builder()
                    .data(imageFormMapper.getFormList(imageFormFilter))
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
    public ResultResponse<?> deleteForm(List<Long> ids) {
        if(myDepth3Id().contains(imageFormId)) {
            for (Long id : ids) {
                imageFormMapper.deleteForm(id);
            }
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

}
