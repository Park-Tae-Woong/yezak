package yezak.api.api.management_support.attendance.vacation_manage.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class DecreasePaidLeaveReq {
    private double paidLeave;
    private Long userId;
    private int year;
}
