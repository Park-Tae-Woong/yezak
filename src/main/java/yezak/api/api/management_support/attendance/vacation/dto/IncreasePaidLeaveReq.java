package yezak.api.api.management_support.attendance.vacation.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class IncreasePaidLeaveReq {
    private double paidLeave;
    private Long userId;
    private int year;
}
