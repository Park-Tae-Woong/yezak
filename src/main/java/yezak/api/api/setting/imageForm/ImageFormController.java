package yezak.api.api.setting.imageForm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.setting.imageForm.dto.ImageFormReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/image")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Image-form-manage", description = "설정 > 펜차트 서식 관리")

public class ImageFormController {

    private final ImageFormService imageFormService;

    //서식 및 파일 저장
    @PostMapping(value = "/save-image-form", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "파일 업로드" , description = "파일 업로드")
    public ResponseEntity<ApiResponse<?>> saveImageFile(ImageFormReq imageFormReq, MultipartFile file) throws FileUploadException {
        ResultResponse<?> resultResponse = imageFormService.saveImageFormAndFile(imageFormReq, file);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //서식 리스트
    @GetMapping(value = "/get-form-list")
    @Operation(summary = "서식 리스트" , description = "서식 리스트", parameters = {
            @Parameter(name = "name", description = "서식명", example = "회의"),
            })
    public ResponseEntity<ApiResponse<?>> getFormList(@RequestParam(required = false) String searchValue){
        ResultResponse<?> resultResponse = imageFormService.getFormList(searchValue);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //패드 서식 삭제
    @DeleteMapping(value = "/delete-form")
    @Operation(summary = "서식 삭제" , description = "서식 삭제")
    public ResponseEntity<ApiResponse<?>> deleteForm(@RequestParam List<Long> ids) {
        ResultResponse<?> resultResponse = imageFormService.deleteForm(ids);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
