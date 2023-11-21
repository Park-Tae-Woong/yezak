package yezak.api.api.management_support.approval.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UseAndConfirmUserRes {
    private int useAmount;
    private int useDate;
    private int useRemark;
    private int useAttachments;
    private String firstName;
    private String secondName;
}
