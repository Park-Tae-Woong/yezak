package yezak.api.api.management_support.approval.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckConfirmUserRes {
    private Long firstConfirmUser;
    private Long secondConfirmUser;
}
