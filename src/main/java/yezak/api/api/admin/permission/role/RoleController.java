package yezak.api.api.admin.permission.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.admin.permission.role.dto.InsertRoleReq;
import yezak.api.api.admin.permission.role.dto.UpdateRoleReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/role")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Role-manage", description = "병원관리 > 계정/권한관리 > 직군관리")

public class RoleController {

    private final RoleService roleService;

    //직군 카테고리 리스트
    @GetMapping(value = "/get-role-categories")
    @Operation(summary = "직군 카테고리 리스트" , description = "직군 카테고리 리스트")
    public ResponseEntity<ApiResponse<?>> getRoleCategories(){
        ResultResponse<?> resultResponse = roleService.getRoleCategories();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //직군 상세 리스트
    @GetMapping(value = "/get-roles")
    @Operation(summary = "상세 직군" , description = "상세 직군", parameters = {
            @Parameter(name = "id", description = "직군 id", example = "1"),
    })
    public ResponseEntity<ApiResponse<?>> getRoles(@RequestParam Long id){
        ResultResponse<?> resultResponse = roleService.getRoles(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //멀티 처리
    @PostMapping(value = "/insert-role")
    @Operation(summary = "상세직군 설정" , description = "상세직군 설정")
    public ResponseEntity<ApiResponse<?>> insertRole(@RequestBody InsertRoleReq insertRoleReq) {
        ResultResponse<?> resultResponse = roleService.insertRole(insertRoleReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PutMapping(value = "/update-role")
    @Operation(summary = "상세직군 수정" , description = "상세직군 수정")
    public ResponseEntity<ApiResponse<?>> updateRole(@RequestBody UpdateRoleReq updateRoleReq) {
        ResultResponse<?> resultResponse = roleService.updateRole(updateRoleReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @DeleteMapping(value = "/delete-role")
    @Operation(summary = "상세직군 삭제" , description = "상세직군 삭제")
    public ResponseEntity<ApiResponse<?>> deleteRole(@RequestParam Long id) {
        ResultResponse<?> resultResponse = roleService.deleteRole(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
