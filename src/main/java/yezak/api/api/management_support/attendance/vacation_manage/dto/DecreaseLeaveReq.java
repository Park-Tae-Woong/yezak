package yezak.api.api.management_support.attendance.vacation_manage.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class DecreaseLeaveReq {
    private int type;
    private double leave;
    private int year;
    private Long userId;
}
