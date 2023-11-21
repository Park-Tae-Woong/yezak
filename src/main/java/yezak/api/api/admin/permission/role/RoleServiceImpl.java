package yezak.api.api.admin.permission.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.admin.permission.role.dto.InsertRoleReq;
import yezak.api.api.admin.permission.role.dto.UpdateRoleReq;
import yezak.api.global.common.ResultResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final Integer roleId = 56;
    @Override
    public ResultResponse<?> getRoleCategories() {
        if(myDepth3Id().contains(roleId)) {
            return ResultResponse.builder()
                    .data(roleMapper.getRoleCategories(myHospitalId()))
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
    public ResultResponse<?> getRoles(Long id) {
        if(myDepth3Id().contains(roleId)) {
            return ResultResponse.builder()
                    .data(roleMapper.getRoles(id, myHospitalId()))
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
    public ResultResponse<?> insertRole(InsertRoleReq insertRoleReq) {
        if(myDepth3Id().contains(roleId)) {
            String name = roleMapper.getKoName(insertRoleReq.getKoName());
            if (name != null){
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("중복된 이름입니다.")
                        .build();
            }
            InsertRoleReq insert = InsertRoleReq.builder()
                    .roleCategoryId(insertRoleReq.getRoleCategoryId())
                    .gradeId(insertRoleReq.getGradeId())
                    .hospitalId(myHospitalId())
                    .koName(insertRoleReq.getKoName())
                    .build();
            roleMapper.insertRole(insert);

            Long roleId = insert.getId();
            Long roleCategoryId = roleMapper.findRoleCategoryId(roleId);
            List<Long> depth3Ids = new ArrayList<>();

            if (roleCategoryId == 1) { //의사
                depth3Ids.addAll(LongStream.rangeClosed(1L, 42L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(59L, 70L)
                        .boxed()
                        .toList());
            }
            else if(roleCategoryId == 2) { //간호사
                depth3Ids.addAll(LongStream.rangeClosed(1L, 7L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(9L, 42L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(59L, 70L)
                        .boxed()
                        .toList());
            }
            else if (roleCategoryId == 3) { //대면상담사
                depth3Ids.addAll(LongStream.rangeClosed(1L, 5L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(13L, 42L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(60L, 70L)
                        .boxed()
                        .toList());
            }
            else if (roleCategoryId == 4) { //전화상담사
                depth3Ids.addAll(LongStream.rangeClosed(1L, 5L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(13L, 42L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(60L, 70L)
                        .boxed()
                        .toList());
            }
            else if (roleCategoryId == 5) { //원무과
                depth3Ids.addAll(LongStream.rangeClosed(1L, 5L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(13L, 42L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(60L, 70L)
                        .boxed()
                        .toList());
            }
            else if (roleCategoryId == 6) { //경영지원
                depth3Ids.addAll(LongStream.rangeClosed(1L, 4L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(13L, 42L)
                        .boxed()
                        .toList());
                depth3Ids.addAll(LongStream.rangeClosed(60L, 70L)
                        .boxed()
                        .toList());
            }
            else if (roleCategoryId == 7) { //외부업체
                depth3Ids.add(17L);
            }
            else if (roleCategoryId == 11) { //외부세무노무사
                depth3Ids = LongStream.rangeClosed(43L, 44L)
                        .boxed()
                        .toList();
            }
            for (Long depth3Id : depth3Ids) {
                roleMapper.insertDepth3(roleId, depth3Id);
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("등록되었습니다.")
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
    public ResultResponse<?> deleteRole(Long id) {
        if(myDepth3Id().contains(roleId)) {

            int i = roleMapper.countUser(id);
            if (i != 0) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("해당 직군에 계정이 있는 경우 삭제가 불가능합니다.")
                        .build();
            }
            roleMapper.deleteRole(id);
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
    @Override
    public ResultResponse<?> updateRole(UpdateRoleReq updateRoleReq) {
        if(myDepth3Id().contains(roleId)) {
            if (updateRoleReq.getRoleCategoryId() == 12) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("병원장은 변경 불가능합니다.")
                        .build();
            }
            roleMapper.updateRole(updateRoleReq);
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
