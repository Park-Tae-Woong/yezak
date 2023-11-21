package yezak.api.api.management_support.emergency_contact_list;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/emergency-contact-list")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Emergency-contact-list", description = "경영지원 > 비상연락망")
public class EmergencyContactListController {

    private final EmergencyContactListService emergencyContactListService;

    //비상연락망 리스트
    @GetMapping(value = "")
    @Operation(summary = "비상연락망 리스트" , description = "비상연락망 리스트",
            parameters = {
                    @Parameter(name = "roleCategoryId", description = "직군카테고리id", example = "1"),
                    @Parameter(name = "employmentTypeId", description = "근무형태id", example = "1"),
                    @Parameter(name = "searchValue", description = "검색", example = "홍길동")
            })
    public ResponseEntity<ApiResponse<?>> drugUsageList(@RequestParam(required = false) Long roleCategoryId,
                                                     @RequestParam(required = false) Long employmentTypeId,
                                                     @RequestParam(required = false) String searchValue){
        ResultResponse<?> resultResponse =  emergencyContactListService.emergencyContactList(roleCategoryId, employmentTypeId, searchValue);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
