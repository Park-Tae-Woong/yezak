package yezak.api.api.management_support.meeting.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingDetailConclusion {
    private Long conclusionId;
    private String conclusionContent;
    private String dueDate;
    private String dueTime;
    private String manager;
    private String statusName;
}
