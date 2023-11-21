package yezak.api.api.management_support.approval.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmApprovalReq {
    private Long changeConfirmStatuses;
    private String remark;
    private Long id;
}
