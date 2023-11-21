package yezak.api.api.management_support.approval.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateApprovalReq {
    @Hidden
    private Long id;
    private String askedAt;
    private String title;
    private String content;
    private Integer amount;
    private String date;
    private String remark;
    @Hidden
    private Long askUserId;
    private Long askFormId;
    @Hidden
    private Long hospitalId;
}
