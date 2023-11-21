package yezak.api.api.management_support.attendance.vacation.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class DecreaseLeaveReq {
    private String end;
    private String start;
    private Long userId;
    private Integer year;
}
