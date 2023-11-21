package yezak.api.api.management_support.approval.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalListReq {
    private Long askFormId;
    private String start;
    private String end;
    private Long totalConfirmStatuses;
    @Hidden
    private Long hospitalId;
    @Hidden
    private Long myUserId;
}
