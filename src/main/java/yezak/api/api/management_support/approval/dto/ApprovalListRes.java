package yezak.api.api.management_support.approval.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalListRes {
    private Long id;
    private String formName;
    private String userName;
    private String askedAt;
    private String title;
    private String confirmName;
}
