package yezak.api.api.management_support.approval.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateApprovalForm {
    @Hidden
    private Long hospitalId;
    private String name;
    private int useAmount;
    private int useDate;
    private int useRemark;
    private int useAttachments;
    private int firstConfirmUser;
    private int secondConfirmUser;
}
