package yezak.api.api.management_support.attendance.attendance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAttendanceDto {
    private String name;
    private String date;
    private String commutingTime;
    private String leavingTime;
    private Long hospitalId;
}
