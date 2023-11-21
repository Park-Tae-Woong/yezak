package yezak.api.api.setting.disease;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.setting.disease.dto.UpdateDiseaseReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/disease")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Disease-manage", description = "설정 > 병명관리")
public class DiseaseController {

    private final DiseaseService diseaseService;

    //상병 리스트
    @GetMapping(value = "/disease-list")
    @Operation(summary = "병명 리스트" , description = "병명 리스트"
            , parameters = {
            @Parameter(name = "perfectCodeChecker", description = "완전 구분", example = "N or null or 미입력"),
            @Parameter(name = "searchValue", description = "검색", example = "AA00"),
            @Parameter(name = "pageNum", description = "페이지 번호", example = "1"),
            @Parameter(name = "pageSize", description = "페이지 갯수", example = "15")
    })
    public ResponseEntity<ApiResponse<?>> diseaseList(@RequestParam(required = false) String perfectCodeChecker,
                                                   @RequestParam(required = false) String searchValue,
                                                   @RequestParam Integer pageNum,
                                                   @RequestParam Integer pageSize){
        ResultResponse<?> resultResponse = diseaseService.diseaseList(perfectCodeChecker, searchValue, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //상병 개별 조회
    @GetMapping(value = "/disease-one")
    @Operation(summary = "병명 개별 조회" , description = "병명 리스트"
            , parameters = {
            @Parameter(name = "id", description = "상병 id", example = "1")
    })
    public ResponseEntity<ApiResponse<?>> findByDiseaseCode(@RequestParam Long id){
        ResultResponse<?> resultResponse = diseaseService.findByDiseaseCode(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

    //사용자 코드 업데이트
    @PutMapping(value = "/update-custom-code")
    @Operation(summary = "사용자 코드 수정" , description = "사용자 코드 수정")
    public ResponseEntity<ApiResponse<?>> updateCode(@RequestBody UpdateDiseaseReq updateDiseaseReq) {
        ResultResponse<?> resultResponse = diseaseService.updateCode(updateDiseaseReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

}
