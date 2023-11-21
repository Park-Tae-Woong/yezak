package yezak.api.api.admin.permission.role.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleMulti {
    private List<InsertRoleReq> insertRoleReqs;
    private List<Long> deleteRole;
    private List<UpdateRoleReq> updateRoleReqs;
}
