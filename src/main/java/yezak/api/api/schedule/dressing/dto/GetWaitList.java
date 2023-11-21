package yezak.api.api.schedule.dressing.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWaitList {
    private String chartNumber;
    private String name;
    private String age;
    private String sex;
    private String reservatedAt;
    private String roomName;
    private String status;
}
