package yezak.api.api.admin.permission.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.admin.permission.auth.dto.ExposedOrNot;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Auth-manage", description = "병원 관리 > 계정/권한 관리 > 권한 관리"
)
public class AuthController {
    private final AuthService authService;

    //실무 메뉴 권한 리스트
    @GetMapping(value = "/get-depth-list")
    @Operation(summary = "권한 관리 리스트" , description = "권한 관리 리스트"
            , parameters = {
            @Parameter(name = "roleId", description = "직군id", example = "1")})
    public ResponseEntity<ApiResponse<?>> allDepthList(Long roleId){
        ResultResponse<?> resultResponse = authService.allDepthList(roleId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //권한 체크
    @PostMapping(value = "/depth3-check")
    @Operation(summary = "상위노출" , description = "상위노출")
    public ResponseEntity<ApiResponse<?>> ShowOrHideCheck(@RequestBody ExposedOrNot exposedOrNot) {
        ResultResponse<?> resultResponse = authService.ShowOrHideCheck(exposedOrNot);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //직군 카테고리
    @GetMapping(value = "/get-role-category-list")
    @Operation(summary = "직군 카테고리 리스트" , description = "직군 카테고리 리스트")
    public ResponseEntity<ApiResponse<?>> getRoleCategories(){
        ResultResponse<?> resultResponse = authService.getRoleCategories();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


}
