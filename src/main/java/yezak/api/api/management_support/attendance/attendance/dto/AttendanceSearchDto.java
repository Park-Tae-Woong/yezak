package yezak.api.api.management_support.attendance.attendance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSearchDto {
    private String name;
    private String start;
    private String end;
    private Long hospitalId;
}
