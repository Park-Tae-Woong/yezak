package yezak.api.api.management_support.approval.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalDetailRes {
    private String askedAt;
    private String roleName;
    private String userName;
    private String title;
    private String content;
    private int amount;
    private String date;
    private String remark;
    private String fileName;
    private String firstStatus;
    private String firstName;
    private String secondStatus;
    private String secondName;
    private String rejectReason;
}
