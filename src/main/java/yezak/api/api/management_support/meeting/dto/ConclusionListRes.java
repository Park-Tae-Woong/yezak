package yezak.api.api.management_support.meeting.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConclusionListRes {
    private Long conclusionId;
    private String conclusionContent;
    private String dueDate;
    private String dueTime;
    private Long managerId;
    private String manager;
    private String statusName;
}
